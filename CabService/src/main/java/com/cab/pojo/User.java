package com.cab.pojo;

public class User {

	private String customerId;
	private GeoLocation geoLocation;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public GeoLocation getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}
	public User(String customerId, GeoLocation geoLocation) {
		super();
		this.customerId = customerId;
		this.geoLocation = geoLocation;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
