package net.yun10000.service;

import org.springframework.stereotype.Service;


public class PropertiesService {

	private String userName;
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
