package net.yun10000.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * 简化json 
 * @author ydy
 * */

public final class JsonMap implements Serializable {



	// 1正确 0 警告 -1 错误 unlogin 未登录 
	private String code="1";
	
	private String message = "";
	private Map<String, Object> map=new HashMap<String, Object>();
	
	public void addObject(String key,Object value){
		map.put(key, value);
	}
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 *  @param code 1正确 0 警告 -1 错误 unlogin 未登录 
	 * */
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}

	
 
}