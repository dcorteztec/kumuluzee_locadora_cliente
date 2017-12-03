package br.com.marteleto.framework.core.util;


import java.io.Serializable;
import java.security.MessageDigest;

public class SecurityUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String convertStringToMd5(String valor) throws Exception {
    	MessageDigest mDigest = MessageDigest.getInstance("MD5");
    	byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
    	StringBuffer sb = new StringBuffer();
    	for (byte b : valorMD5){
    		sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
    	}
    	return sb.toString();
	}
	
	public static String convertStringToSHA256(String valor) throws Exception {
    	MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
    	byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
    	StringBuffer sb = new StringBuffer();
    	for (byte b : valorMD5){
    		sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
    	}
    	return sb.toString();
	}
	
	public static String convertStringToSHA512(String valor) throws Exception {
    	MessageDigest mDigest = MessageDigest.getInstance("SHA-512");
    	byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
    	StringBuffer sb = new StringBuffer();
    	for (byte b : valorMD5){
    		sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
    	}
    	return sb.toString();
	}
}
