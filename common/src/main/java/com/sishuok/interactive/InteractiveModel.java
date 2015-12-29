package com.sishuok.interactive;

import java.util.HashMap;
import java.util.Map;
/**
 * 封装客户端调用传递过来的参数的模型
 * @author sunniwell
 *
 */
public class InteractiveModel {
	private String opeType;
	private Map<String, Object> map = new HashMap<String, Object>();
	public String getOpeType() {
		return opeType;
	}
	public void setOpeType(String opeType) {
		this.opeType = opeType;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
}
