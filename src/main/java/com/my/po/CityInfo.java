package com.my.po;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_city")
public class CityInfo {
	private int id;
	private String cityName;
	private Set<TownInfo> towns;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="city_name")
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@OneToMany(mappedBy="city",fetch=FetchType.EAGER)
	public Set<TownInfo> getTowns() {
		return towns;
	}
	public void setTowns(Set<TownInfo> towns) {
		this.towns = towns;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj){
			return true;
		}else if(obj instanceof CityInfo){
			CityInfo c=(CityInfo)obj;
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
