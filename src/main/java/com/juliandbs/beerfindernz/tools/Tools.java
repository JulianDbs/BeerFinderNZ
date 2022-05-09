package com.juliandbs.beerfindernz.tools;

import java.lang.NullPointerException;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
*	This class is a miscellaneous collection of static methods used in different parts of this application.
*
*	@author JulianDbs
*/
public class Tools {

	/**
	*	This variable represents a valid map example.
	*/
	public static final Map<String, String> validSearchMap = buildValidMap();

	/**
	*	This method returs a 'Map' class instance that contains all the keys needed to be a valid map.
	*
	*	@return a valid 'Map' example.
	*/
	private static Map<String, String> buildValidMap() {
		Map<String, String> validMap = new LinkedHashMap<>();
		validMap.put("brand", "none");
		validMap.put("isle", "none");
		validMap.put("location", "none");
		validMap.put("region", "none");
		return validMap;
	}

	/**
	*	This method compares the map receives as argument with a valid map example, returns true if
	*	the map received as argument contain the same keys that the valid map example, otherwise false.
	*
	*	@param map a 'Map' instance that represents the map to compare.
	*	@return True if the map argument contains the sames keys that the valid map example, otherwise false.
	*/
	public static boolean isValidSearchMap(Map<String, String> map) {
		return map.keySet().containsAll(validSearchMap.keySet());
	}

	/**
	*	This method checks if the 'Map' parameter received as argument contains nulls, empty or none valid values,
	*	if it contains none valid values, returns true, otherwise false.
	*
	*	@param map a 'Map' instance that represents the map in which search for null or none valid values.
	*	@return True if map contains null or none valid values, otherwise false.
	*/
	public static boolean mapContainsNullValues(Map<String, String> map) {
		boolean result = false;
		for(String value : map.values()) {
			if (value.equals("null") || value == null || value.isEmpty() || value.equals("none") || value.equals("empty")) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	*	This method creates a 'Map' with the parameters that may or may not have the HttpRequest body
	*	received as argument.
	*
	*	@param body a String object that represents a HttpRequest body.
	*	@return a 'Map' containig the parameters that may ot may not have the HttpRequest body.
	*	@throws NullPointerException if the 'body' paramter is null.
	*/
	public static Map<String, String> getMapFromBody(String body) throws NullPointerException {
		if (body == null)
			throw new NullPointerException();
		Map<String, String> map = new LinkedHashMap<>();
		if (body.contains( String.valueOf( (char)43 ))) {
			String[] params = body.replace(String.valueOf( (char)38), "," ).split(",");
			for (String param : params) {
				String[] part = param.split("=");
				if (part.length == 2) {
					String namePart = part[0];
					String valuePart = part[1].replace(String.valueOf( (char)43 ), " ");
					if(valuePart.startsWith(" ")) {valuePart = valuePart.substring(1);}
					map.put(namePart, valuePart);
				}
			}
		} else {
			String[] part = body.split("=");
			if (part.length == 2) {
				String namePart = part[0];
				String valuePart = part[1].replace(String.valueOf( (char)43 ), " ");
				if (valuePart.startsWith(" ")) {valuePart = valuePart.substring(1);}
				map.put(namePart, valuePart);
			}
		}

		return map;
	}

	/**
	*	This method creates a 'Map' with the parameters that may or may not have the JSON HttpRequest body
	*	received as argument.
	*
	*	@param body a String object that represents a JSON HttpRequest body.
	*	@return a 'Map' containig the parameters that may ot may not have the JSON HttpRequest body.
	*	@throws NullPointerException if the 'body' paramter is null.
	*/
	public static List<Map<String, String>> getParametersFromJSONBody(String body) throws NullPointerException {
		if (body == null)
			throw new NullPointerException();
		List<Map<String, String>> parameterList = new ArrayList<>();
		if (body.contains(",")) {
			String[] params = body.split(",");
			for (String param : params) {
				Map<String, String> map = processParam(param);
				if (map.keySet().size() > 0)
					parameterList.add(map);
			}
		} else {
			Map<String, String> map = processParam(body);
			if (map.keySet().size() > 0)
				parameterList.add(map);
		}
		return parameterList;
	}

	/**
	*	This method separates the parameter name and the parameter value from a split JSON http request body and
	*	returns it as a 'Map' with the parameter name as a 'Key' and the parameter value as a 'Value' of that 'Key'.
	*
	*	@param param a String object that represents a part of a split HttpRequest body.
	*	@return a 'Map' containing the parameter name as a key and the parameter value as a value of that key.
	*/
	private static Map<String, String> processParam(String param) {
		Map<String, String> map = new HashMap<>();
		if (param.contains(":")) {
			String[] part = param.split(":");
			String namePart = part[0].replace( String.valueOf( (char)34 ), "").replace( String.valueOf( (char)123 ), "");
			String valuePart = part[1].replace( String.valueOf( (char)34 ), "").replace( String.valueOf( (char)125 ), "");
			if (valuePart.startsWith(" "))
				valuePart = valuePart.substring(1, valuePart.length());
			map.put(namePart, valuePart);
		}
		return map;
	}

	/**
	*	This static method tries to find a specified parameter as a key in a specified 'List' of 'Map' instances and returns its value.
	*	If the key is not found returns the "none" value.
	*
	*	@param paramName a String object that represents the key to find.
	*	@param paramList a 'List' of 'Map' instances.
	*	@return a String instance that may or may not represents the value of a key with the same name that the 'paramName' in one
	*	of the map's of the 'paramList' list.
	*	@throws NullPointerException if the 'paramName' or 'paramList' are null.
	*/
	public static String findParamValueInParamList(String paramName, List<Map<String, String>> paramList) throws NullPointerException {
		if (paramName == null || paramList == null)
			throw new NullPointerException();
		String result = "none";
		for (Map<String, String> map : paramList) {
			if (map.containsKey(paramName))
				result = map.get(paramName);
				break;
		}
		return result;
	}

	/**
	*	This static method is used to create a delay between capturing a POST/GET request and sending the response
	*	for the purpose of testing CSS animations.
	*
	*	@param time the amount of milliseconds to wait.
	*/
	public static void waithTime(int time){
		try {
			java.lang.Thread.sleep(time);
		} catch(java.lang.InterruptedException ie) {
			java.lang.Thread.currentThread().interrupt();
		}
	}
}
