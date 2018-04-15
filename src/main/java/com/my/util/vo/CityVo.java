package com.my.util.vo;

public class CityVo {

	private int id;
	private String cityName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Override
	public boolean equals(Object obj) {
		if( obj instanceof CityVo){
			CityVo c=(CityVo)obj;
			if(c.getId()==this.getId()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
}
