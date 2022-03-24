package com.abdullahteke.hpsm.controller;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ConfigManager {
	
	private static ConfigManager instance;
	private String smWsURL;
	private String smWsUser;
	private String smWsPasswd;


	private static final Logger logger = (Logger) LogManager.getLogger(ConfigManager.class);

	public static ConfigManager getInstance() {
		
		if (instance==null){
			instance=new ConfigManager();
		}
		return instance;
	}

	private ConfigManager(){
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream("config/smConfig.properties");
			prop.load(input);
			input.close();
			
			smWsURL=prop.getProperty("smWsURL");
			smWsUser=prop.getProperty("smWsUser");
			smWsPasswd=Encryptor.getInstance().decrypt(prop.getProperty("smWsPasswd"),true);

		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
			logger.error("Properties dosyası bulunmadı. HATA:"+e1.getMessage());
		} catch (IOException e) {

			e.printStackTrace();
			logger.error("Properties dosyası okunamadı. HATA:"+e.getMessage());
		} 
	}


	public String getSmWsURL() {
		return smWsURL;
	}

	public void setSmWsURL(String smWsURL) {
		this.smWsURL = smWsURL;
	}

	public String getSmWsUser() {
		return smWsUser;
	}

	public void setSmWsUser(String smWsUser) {
		this.smWsUser = smWsUser;
	}

	public String getSmWsPasswd() {
		return smWsPasswd;
	}

	public void setSmWsPasswd(String smWsPassw) {
		this.smWsPasswd = smWsPassw;
	}

	public static Logger getLogger() {
		return logger;
	}


} // end of Manager Class
