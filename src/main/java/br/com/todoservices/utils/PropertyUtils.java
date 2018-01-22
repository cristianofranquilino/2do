package br.com.todoservices.utils;

import java.util.ResourceBundle;

public class PropertyUtils {

	public static ResourceBundle getResource(String resourceName) {
		
		ResourceBundle bundle = ResourceBundle.getBundle(resourceName);
		return bundle;		
	}
	
}
