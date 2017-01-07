package com.trytech.tasks.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

	
	public static String getProperty(String strProp) {

		String propValue = "";
		Properties prop = new Properties();
		InputStream input = null;


		try {

			input = new FileInputStream("configs/prop.txt");

			// load a properties file
			prop.load(input);

			// get the value out
			propValue = prop.getProperty(strProp);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return propValue;
	}
}
