package com.sishuok.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {
	public static String object2str(Object obj){
		String retStr = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			retStr=mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return retStr;
	}
	
	public static <T> T str2Object(String str,Class<T> clazz){
		T t = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			t=mapper.readValue(str, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
