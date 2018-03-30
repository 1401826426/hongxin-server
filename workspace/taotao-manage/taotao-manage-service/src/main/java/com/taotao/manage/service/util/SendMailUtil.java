package com.taotao.manage.service.util;

import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sun.mail.util.MailSSLSocketFactory;
import com.taotao.common.bean.Mail;


@Component
public class SendMailUtil implements InitializingBean , DisposableBean{
	
	private static Logger logger = LoggerFactory.getLogger(SendMailUtil.class) ; 
	
	private static Properties properties = new Properties();
	
	private volatile ExecutorService excutorService ; 
	
	@Value("${mail.from}")
	private String mailFrom ; 
	
	@Value("${mail.smtp.host}")
	private String mailSmtpHost ; 
	
	@Value("${mail.smtp.auth}")
	private String mailSmtpAuth ; 
	
	@Value("${mail.smtp.ssl.enable}")
	private String mailSmtpSslEnable ; 
	
	@Value("${mail.from.password}")
	private String mailFromPassord ; 
	
	
//	static {
//		InputStream is = ClassLoader.getSystemResourceAsStream("mail.properties");
//		try {
//			properties.load(is);
//			MailSSLSocketFactory sf = new MailSSLSocketFactory();
//			sf.setTrustAllHosts(true);
//			properties.put("mail.smtp.ssl.enable", "true");
//			properties.put("mail.smtp.ssl.socketFactory", sf);
//		} catch (IOException e) {
//			logger.warn("载入mail.properties出错", e);
//			e.printStackTrace();
//		} catch (GeneralSecurityException e) {
//			logger.warn("载入mail.properties出错", e);
//			e.printStackTrace();
//		}
//	}
	public boolean sendMail(Mail mail) {
		for (String to : mail.getTo()) {
			try {
				Session session = Session.getDefaultInstance(properties, new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(properties.getProperty("mail.from"),
								properties.getProperty("mail.from.password")); // 发件人邮件用户名、密码
					}
				});
				MimeMessage message = new MimeMessage(session);
				String from = properties.getProperty("mail.from" ) ; 
				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject(mail.getSubject());
				message.setText(mail.getContent());
				Transport.send(message);
				logger.info("发送邮件成功" + from + "  " + to) ;
				System.out.println("发送邮件成功" + from + "  " + to) ;
			} catch (Exception e) {
				logger.warn("发送邮件失败" + to , e);
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public void sendMailAync(Mail mail){
		if(excutorService == null){
			synchronized (this) {
				if(excutorService == null){
					excutorService = Executors.newFixedThreadPool(5) ;
				}
			}
		}
		SendMail sendMail = new SendMail(mail) ; 
		excutorService.execute(sendMail);
	}
	
	public void stop(){
		if(excutorService != null){
			this.excutorService.shutdown(); 
		}
	}
	
	
	class SendMail implements Runnable{
		
		Mail mail ; 
		
		public SendMail(Mail mail){
			this.mail = mail ; 
		}
		
		@Override
		public void run() {
			sendMail(mail) ; 
		}
		
	}
	
	
	public static void main(String[] args) throws GeneralSecurityException {
//		for(int i = 0;i < 100;i++){
//			Mail mail = new Mail() ; 
//			mail.setContent("test mail");
//			mail.setSubject("test mail");
//			List<String> to = new ArrayList<String>() ; 
////			to.add("13248235863@163.com") ;
////			to.add("1401826426@qq.com") ; 
//			to.add(""+i) ; 
//			mail.setTo(to);
//			SendMailUtil.getInstance().sendMailAync(mail) ; 
//		}
//		SendMailUtil.getInstance().stop();
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);
		properties.put("mail.from",mailFrom) ; 
		properties.put("mail.smtp.host",mailSmtpHost) ;
		properties.put("mail.smtp.auth",mailSmtpAuth) ;
		properties.put("mail.smtp.ssl.enable",mailSmtpSslEnable) ;
		properties.put("mail.from.password",mailFromPassord) ;
	}

	@Override
	public void destroy() throws Exception {
		stop() ; 
	}
	

	
}























