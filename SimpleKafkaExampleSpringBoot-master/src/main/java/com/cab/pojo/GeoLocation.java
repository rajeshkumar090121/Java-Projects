package com.cab.pojo;

public class GeoLocation {
	public String longitude;
	public String latitude;
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public GeoLocation(String longitude, String latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public GeoLocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
