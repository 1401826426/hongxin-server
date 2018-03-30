package com.taotao.common.util;

import java.util.Random;
import java.util.UUID;

public class IdGenerator {
	
	public static long generateId(){
		long result = System.currentTimeMillis()*1000 + new Random(1000).nextInt();
		return result ; 
	}
	
	public static String generateStringId(){
		return UUID.randomUUID().toString() ; 
	}
	
	
}
