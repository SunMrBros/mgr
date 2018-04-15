package com.my.util.enums;

public enum ProvinceEnum {

Hebei("河北省",1),Beijing("北京市",2),Tianjin("天津市",3);
	
	private String name;
	private int status;
	
	private ProvinceEnum(String name,int status){
		this.name=name;
		this.status=status;
	}
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
	
}
