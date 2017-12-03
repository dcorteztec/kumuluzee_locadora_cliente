package br.com.marteleto.framework.core.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	protected transient static Logger LOG = Logger.getLogger(PropertyUtil.class.getName());
	
	public static final String APPLICATION = "application";
	public static final String VERSION = "version";
	
	private static Properties properties = new Properties();

    public static void addProperty(String propsName) throws Exception {
		InputStream is = null;
		File file = new File(propsName);
		if (file != null && file.exists()) {
			is = new FileInputStream(file);
		} else {
			is = PropertyUtil.class.getResourceAsStream(propsName);
			if (is == null) {
				is = PropertyUtil.class.getClassLoader().getResourceAsStream(propsName);
			}
		}
		if (is == null) {
			throw new Exception("File not found: " + propsName);
		}
		properties.load(is);
		is.close();
		LOG.info("File: " + propsName + " loaded successfully.");
    }
    
    public static String getProperty(String key) {
    	return PropertyUtil.properties.getProperty(key);
    }
    
    public static void setProperty(String key, String value) {
    	PropertyUtil.properties.setProperty(key, value);
	}
    
    public static void removeProperty(String key) {
    	PropertyUtil.properties.remove(key);
	}
    
    public static String getVersion() {
    	return PropertyUtil.getProperty(VERSION);
    }
    
    public static String getApplication() {
    	return PropertyUtil.getProperty(APPLICATION);
    }
}