package com.testautomation.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
	
public Properties getProperty() throws IOException
{
	FileInputStream inputStream = null;
	Properties properties = new Properties();
	try {
		properties.load(new FileInputStream("resources/browser-config.properties"));
	}catch (Exception e){
		System.out.println("Exception: " + e);
	}
return properties;
}
} 


/*
public static String getProperty(String property) {
	if (System.getProperty(property) != null) {
		return System.getProperty(property);
	}
	File setupPropFile = new File("resources/browser-config.properties");
	if (setupPropFile.exists()) {
		Properties prop = new Properties();
		FileReader reader;
		try {
			reader = new FileReader(setupPropFile);
			prop.load(reader);
			reader.close();
			return prop.getProperty(property);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return null; 
}
} */