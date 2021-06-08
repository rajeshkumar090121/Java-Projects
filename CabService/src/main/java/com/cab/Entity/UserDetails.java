package com.cab.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cab.pojo.GeoLocation;

public class UserDetails {
	
	@Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Integer id;
	private String customerId;
	private String Lang;
	private String Lati;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	
	
}
