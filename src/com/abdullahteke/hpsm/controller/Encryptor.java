package com.abdullahteke.hpsm.controller;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class Encryptor {
	
	private String key;

	private static final Logger logger = (Logger) LogManager.getLogger(Encryptor.class);
    public Encryptor() {
		super();
		this.key = "@^45Aæslk@!#";
		
	}
    
	private static Encryptor instance;


	public static Encryptor getInstance() {
		
		if (instance==null){
			instance=new Encryptor();
		}
		return instance;
	}


	public String encrypt(String toEncrypt,boolean useHash) {
    	String retVal=null;
    	byte[] buffer;
    	byte[] bytes;
    	byte[] keyBytes=null;
    	MessageDigest m=null;
    	
		try {
			bytes = toEncrypt.getBytes("UTF-8");
			
	    	if (useHash){
	    		Security.addProvider(new BouncyCastleProvider());
	    		
	    		byte[] tmpBytes =  getAnahtar().getBytes("UTF-8");
	    		m= MessageDigest.getInstance("MD5");
	    		buffer= m.digest(tmpBytes);
	            keyBytes = Arrays.copyOf(buffer, 24);
	            for (int j = 0, k = 16; j < 8;) {
	                keyBytes[k++] = keyBytes[j++];
	            }
	            
	    	}else{
	    		buffer=getAnahtar().getBytes("UTF-8");
	    	}
	    		
	    	
	    	KeySpec ks= new DESedeKeySpec(keyBytes);
	    	SecretKeyFactory skf= SecretKeyFactory.getInstance("DESEDE");
	    	Cipher cipher=Cipher.getInstance("DESEDE/ECB/PKCS7Padding","BC");
	    	SecretKey key=skf.generateSecret(ks);
	    	cipher.init(Cipher.ENCRYPT_MODE, key);
	    	byte[] encryptedText = cipher.doFinal(bytes);
	    	
	    	//retVal= new String(Base64.encodeBase64(encryptedText));	
	    	retVal= new String(new Base64().encode(encryptedText));
	    	
	    	
		} catch (UnsupportedEncodingException e1) {

			e1.printStackTrace();
			logger.fatal(toEncrypt+" encrpyt edilirken hata alindi."+e1.getMessage());

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
			logger.fatal(toEncrypt+" encrpyt edilirken hata alindi."+e.getMessage());

		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
			logger.fatal(toEncrypt+" encrpyt edilirken hata alindi."+e.getMessage());

		} catch (InvalidKeyException e) {

			e.printStackTrace();
			logger.fatal(toEncrypt+" encrpyt edilirken hata alindi."+e.getMessage());

		} catch (InvalidKeySpecException e) {

			e.printStackTrace();
			logger.fatal(toEncrypt+" encrpyt edilirken hata alindi."+e.getMessage());

		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();
			logger.fatal(toEncrypt+" encrpyt edilirken hata alindi."+e.getMessage());

		} catch (BadPaddingException e) {

			e.printStackTrace();
			logger.fatal(toEncrypt+" encrpyt edilirken hata alindi."+e.getMessage());

		} catch (NoSuchProviderException e) {

			e.printStackTrace();
			logger.fatal(toEncrypt+" encrpyt edilirken hata alindi."+e.getMessage());

		}
    	
    	
    	return retVal;
    }

    public String decrypt(String fromDcrypt, boolean useHash) {
    	
    	String retVal=null;

    	byte[] buffer;
    	byte[] keyBytes=null;
    	MessageDigest m=null;
    	
		try {

			
	    	if (useHash){
	    		Security.addProvider(new BouncyCastleProvider());
	    		
	    		byte[] tmpBytes =  getAnahtar().getBytes("UTF-8");
	    		m= MessageDigest.getInstance("MD5");
	    		buffer= m.digest(tmpBytes);
	            keyBytes = Arrays.copyOf(buffer, 24);
	            for (int j = 0, k = 16; j < 8;) {
	                keyBytes[k++] = keyBytes[j++];
	            }
	            
	    	}else{
	    		buffer=getAnahtar().getBytes("UTF-8");
	    	}
	    		
	    	
	    	KeySpec ks= new DESedeKeySpec(keyBytes);
	    	SecretKeyFactory skf= SecretKeyFactory.getInstance("DESEDE");
	    	Cipher cipher=Cipher.getInstance("DESEDE/ECB/PKCS7Padding","BC");
	    	SecretKey key=skf.generateSecret(ks);
	    	cipher.init(Cipher.DECRYPT_MODE, key);
	    	byte[] encryptedText = Base64.decodeBase64(fromDcrypt);
	    	byte[] decryptedText = cipher.doFinal(encryptedText);
	    	retVal= new String(decryptedText,"UTF-8");	
	    	
		} catch (UnsupportedEncodingException e1) {

			e1.printStackTrace();
			logger.fatal(fromDcrypt+" decrypt edilirken hata alindi."+e1.getMessage());

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
			logger.fatal(fromDcrypt+" decrypt edilirken hata alindi."+e.getMessage());

		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
			logger.fatal(fromDcrypt+" decrypt edilirken hata alindi."+e.getMessage());

		} catch (InvalidKeyException e) {

			e.printStackTrace();
			logger.fatal(fromDcrypt+" decrypt edilirken hata alindi."+e.getMessage());

		} catch (InvalidKeySpecException e) {

			e.printStackTrace();
			logger.fatal(fromDcrypt+" decrypt edilirken hata alindi."+e.getMessage());

		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();
			logger.fatal(fromDcrypt+" decrypt edilirken hata alindi."+e.getMessage());

		} catch (BadPaddingException e) {

			e.printStackTrace();
			logger.fatal(fromDcrypt+" decrypt edilirken hata alindi."+e.getMessage());

		} catch (NoSuchProviderException e) {

			e.printStackTrace();
			logger.fatal(fromDcrypt+" decrypt edilirken hata alindi."+e.getMessage());

		}
    	
    	
    	return retVal;

    }
    
	public String getAnahtar() {
		return this.key;
	}
    
    
}
