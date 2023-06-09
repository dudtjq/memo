package com.toyoucafe.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptService {

	// 암호화 기능
	public static String md5(String message) {
		
		String resultString = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			
			byte[] bytes = message.getBytes();
			md.update(bytes);
			
			byte[] digest = md.digest();
			
			// 16진수 형태의 문자열로 변환
			for(int i = 0; i < digest.length; i++) {
				
				resultString += Integer.toHexString(digest[i] & 0xff);
			}
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		return resultString;
		
	}
	
}
