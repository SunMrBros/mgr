package com.my.po;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="t_location")
public class LocationInfo {

	private int id;
	private String locationName;
	private LocationInfo location;
	private Set<LocationInfo> sublocations;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="location_name")
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pid")
	public LocationInfo getLocation() {
		return location;
	}
	public void setLocation(LocationInfo location) {
		this.location = location;
	}
	@OneToMany(mappedBy="location")
	public Set<LocationInfo> getSublocations() {
		return sublocations;
	}
	public void setSublocations(Set<LocationInfo> sublocations) {
		this.sublocations = sublocations;
	}
	
	
}
