package com.cab.pojo;

import java.util.Date;

public class Header {
	public String eventID;
	public String eventName;
	public Date timestamp;
	public String getEventId() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Header(String eventID, String eventName, Date timestamp) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.timestamp = timestamp;
	}
	public Header() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
