package com.cab.pojo;

public class Payload {
	public String CabId;
	public String driverId;
	public GeoLocation geoLocation;
	public String getCabId() {
		return CabId;
	}
	public void setCabId(String cabId) {
		CabId = cabId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public GeoLocation getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}
	public Payload(String cabId, String driverId, GeoLocation geoLocation) {
		super();
		CabId = cabId;
		this.driverId = driverId;
		this.geoLocation = geoLocation;
	}
	public Payload() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
