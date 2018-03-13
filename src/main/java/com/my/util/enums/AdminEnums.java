package com.my.util.enums;

public enum AdminEnums {

	Normal("正常",1),Disable("禁用",0),Delete("删除",2);
	
	private String name;
	private int status;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	private AdminEnums(String name,int status){
		this.name=name;
		this.status=status;
	}
}
