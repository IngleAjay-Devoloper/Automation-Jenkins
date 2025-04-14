package com.amazon.ObjectRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class OR {

	public static String readPropertiesFile(String PropertyKey) throws IOException {
		Properties props =null;
		FileInputStream fin = null;
		String PropertyValue = null;
		
		try {
			fin = new FileInputStream(System.getProperty("user.dir")+"/TestData/environment.properties");
			props = new Properties();
			props.load(fin);
			PropertyValue = props.getProperty(PropertyKey);
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			fin.close();
		}
		return PropertyValue;
	}
}
