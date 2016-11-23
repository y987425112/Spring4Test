package net.yun10000.dto;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private String name;
	private String guid;
	private String code;
	private String url;
	private String treeCode;

	private int orderId;
	
	private List<Menu> menuList=new ArrayList<Menu>();
	
	
	public String getTreeCode() {
		return treeCode;
	}
	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

	public List<Menu> getMenuList() {
		return menuList;
	}
	public void addMenu(Menu menu){
		menuList.add(menu);
	}
	

	
	

}
