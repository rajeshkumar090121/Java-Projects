package com.cab.pojo;

public class CabDetails {
public Header header;
public Payload payload;
public Header getHeader() {
	return header;
}
public void setHeader(Header header) {
	this.header = header;
}
public Payload getPayload() {
	return payload;
}
public void setPayload(Payload payload) {
	this.payload = payload;
}
public CabDetails(Header header, Payload payload) {
	super();
	this.header = header;
	this.payload = payload;
}
public CabDetails() {
	super();
	// TODO Auto-generated constructor stub
}




}
