package com.cab.pojo;

public class GeoLocation {

	private String geoLocationId;
	private String Lang;
	private String Lati;
	public String getGeoLocationId() {
		return geoLocationId;
	}
	public void setGeoLocationId(String geoLocationId) {
		this.geoLocationId = geoLocationId;
	}
	public String getLang() {
		return Lang;
	}
	public void setLang(String lang) {
		Lang = lang;
	}
	public String getLati() {
		return Lati;
	}
	public void setLati(String lati) {
		Lati = lati;
	}
	public GeoLocation(String geoLocationId, String lang, String lati) {
		super();
		this.geoLocationId = geoLocationId;
		Lang = lang;
		Lati = lati;
	}
	public GeoLocation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
