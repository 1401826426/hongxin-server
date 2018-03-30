package com.taotao.manage.manage;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taotao.common.util.IdGenerator;
import com.taotao.manage.bean.UserDto;


public class SessionManager {

	private Logger logger = LoggerFactory.getLogger(SessionManager.class);

	private static SessionManager instance = new SessionManager();

	public static SessionManager getInstance() {
		return instance;
	}

	private Map<String, Session> sessionMap = new ConcurrentHashMap<>();

	private Map<String,Session> userSession = new ConcurrentHashMap<>() ; 
	
	private volatile boolean run = true;

	private volatile long nextCheckTime;

//	private Lock[] locks = new Lock[1024] ; 
	
	
	private SessionManager() {
//		for(int i = 0;i < 1024;i++){
//			locks[i] = new ReentrantLock() ; 
//		}
		CleanThread cleanThread = new CleanThread();
		cleanThread.start();
	}

	class CleanThread extends Thread {
		public void run() {
			while (run) {
				try {
					if (nextCheckTime > System.currentTimeMillis()) {
						sleep(nextCheckTime - System.currentTimeMillis());
					}
					System.err.println(new Date(System.currentTimeMillis()).toString());
					for (Map.Entry<String, Session> entry : sessionMap.entrySet()) {
						Session session = entry.getValue();
						if (session.isInvalidate()) {
							synchronized (session) {
								if(session.isInvalidate()){
									session.destroy(); 
									sessionMap.remove(entry.getKey());
								}
							}
						
						}
						
					}
					System.err.println("========================================" + sessionMap.size());
					for (Map.Entry<String, Session> entry : sessionMap.entrySet()) {
						System.err.println(entry.getKey());
					}
					nextCheckTime = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1);
				} catch (Exception e) {
					logger.error("session删除出错", e);
				}
			}
		}
	}

	//这里主要是对玩家进行锁
	public String addUser(UserDto userDto) {
//		int lockId = getLockId(userDto.getId()) ;
//		Lock lock = locks[lockId] ; 
//		try{
//			 lock.lock();
		 String sessionId = IdGenerator.generateStringId();
		 Session session = new Session(sessionId, userDto);
	     sessionMap.put(sessionId, session);
		 userSession.put(userDto.getId(),session) ; 
		return sessionId; 
//		}finally{
//			lock.unlock();
//		}
		
	}

	
//	private int getLockId(String id) {
//		return Math.abs(id.hashCode()%1024) ; 
//	}


//	public void clearSession(String userId){
//		Session session = userSession.get(userId) ; 
//		session.setInvalid() ;
//	}
	
	public void clearSession(String sessionId){
		Session session = sessionMap.get(sessionId) ; 
		session.setInvalid();  
	}
	
	public Session getSession(String sessionId) {
		return sessionMap.get(sessionId);
	}

	public Session touchSession(String sessionId) {
		Session session = sessionMap.get(sessionId);
		if (session != null) {
			session.touch();
		}
		return session;
	}
	
	public void destroy(){
		this.run = false ; 
	}

//	public static void main(String[] args) {
//		final List<String> list = new ArrayList<String>() ; 
//		for (int i = 0; i < 30; i++) {
//			UserDto userDto = new UserDto("name", "1401826426@qq.com", String.valueOf(i), 1);
//			list.add(SessionManager.getInstance().addUser(userDto));
////			try {
////				Thread.sleep(1000);
////			} catch (InterruptedException e) {
////				e.printStackTrace();
////			}
//		}
//		ExecutorService executors = Executors.newFixedThreadPool(10) ; 
//		final Random random = new Random(list.size()) ; 
//		while (true) {
//			try {
//				executors.execute(new Runnable() {
//					
//					@Override
//					public void run() {
//						
//						String sessionId = list.get(random.nextInt(list.size())) ;
//						SessionManager.getInstance().touchSession(sessionId) ; 
//						System.err.println("touch:" + sessionId+":"+list.size());
//					}
//				});
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	
	public static void main(String[] args) {
		System.err.println("123456".hashCode());
		System.err.println("123456".hashCode());
		System.err.println(Integer.MIN_VALUE%1025);
	}
}
