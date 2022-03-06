package com.jxhh.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1Utils {

	  public static String getSha1(String inputStr){
		  	
		    byte[] input = inputStr.getBytes();
	        MessageDigest mDigest = null;
	        
			try {
				mDigest = MessageDigest.getInstance("SHA1");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("没有这个sha1算法?");
			}
	        byte[] result = mDigest.digest(input);
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < result.length; i++) {
	            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        return sb.toString();
	  }
	  
}
