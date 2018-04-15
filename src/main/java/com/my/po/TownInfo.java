package com.my.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_town")
public class TownInfo {
	private int id;
	private String townName;
	private CityInfo city;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="town_name")
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	@ManyToOne
	@JoinColumn(name="city_id")
	public CityInfo getCity() {
		return city;
	}
	public void setCity(CityInfo city) {
		this.city = city;
	}
	
}
