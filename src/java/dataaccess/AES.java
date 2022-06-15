<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	
	public static String encrypt(String data) {
		try {
			String secretKey = "0cFa4w6dA9J5cdBf"; 
    		
			byte ivBytes[] = new byte[16]; 
			Arrays.fill(ivBytes, (byte)0x00); 
        	
			byte textBytes[] = data.getBytes("UTF-8");
        	
			AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
			SecretKeySpec newKey = new SecretKeySpec(secretKey.getBytes("UTF-8"),"AES");
			Cipher cipher = null;
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
        	        	
			Encoder encoder = Base64.getEncoder(); 
			return encoder.encodeToString(cipher.doFinal(textBytes));        	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());    	
		}
		return "";
	}
	
	public static String decrypt(String data) {
		try {
			String secretKey = "0cFa4w6dA9J5cdBf"; 
    		
			byte ivBytes[] = new byte[16];  
			Arrays.fill(ivBytes, (byte)0x00); 
        	
			Decoder decoder = Base64.getDecoder();
			byte textBytes[] = decoder.decode(data);
    		
			AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
			SecretKeySpec newKey = new SecretKeySpec(secretKey.getBytes("UTF-8"),"AES");
			Cipher cipher = null;
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);    
        	
			return new String(cipher.doFinal(textBytes),"UTF-8");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
        
//        public static void main(String[] args){
//            AES aes = new AES();
//            System.out.println(aes.decrypt("SPcWwRsPrKU2oHQ4MWadpyKX5hmSdsFgsFPluTAkYe8="));
//            
//        }

}


=======
package dataaccess;

import java.security.spec.AlgorithmParameterSpec;
import java.util.*;
import java.util.Base64.*;
import javax.crypto.Cipher;
import javax.crypto.spec.*;

public class AES {	
    public static String encrypt(String data) {
	try {
            String secretKey = "0cFa4w6dA9J5cdBf";    		
            byte ivBytes[] = new byte[16]; 
            Arrays.fill(ivBytes, (byte)0x00); 
            byte textBytes[] = data.getBytes("UTF-8");
        	
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            SecretKeySpec newKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
            Cipher cipher = null;
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
        	        	
            Encoder encoder = Base64.getEncoder(); 
            return encoder.encodeToString(cipher.doFinal(textBytes));        	
	} catch(Exception e) {
            System.out.println(e.getMessage());    	
	}
        
        return "";
    }
	
    public static String decrypt(String data) {
	try {
            String secretKey = "0cFa4w6dA9J5cdBf"; 
            byte ivBytes[] = new byte[16];  
            Arrays.fill(ivBytes, (byte)0x00); 
            Decoder decoder = Base64.getDecoder();
            byte textBytes[] = decoder.decode(data);
    		
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            SecretKeySpec newKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
            Cipher cipher = null;
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);    
        	
            return new String(cipher.doFinal(textBytes), "UTF-8");
	} catch (Exception e) {
            System.out.println(e.getMessage());
	}
        
        return "";
    }
}
>>>>>>> 14639c494b6bfba4180a57b67657af21a6cd01a6
