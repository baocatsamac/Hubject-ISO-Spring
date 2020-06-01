package com.hubject.com.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ChargingStation {
	
	@Id
	private String UID;
	private double latitude;
	private double longitude;
	private String zipCode;

	public ChargingStation() { 

	}

	public ChargingStation(String UID, double latitude, double longitude, String zipCode) {
		super();
		this.UID = UID;
		this.latitude = latitude;
		this.longitude = longitude;
		this.zipCode = zipCode;
	}
	
	public String getUID() {
		return UID;
	}

	public void setUID(String UID) {
		this.UID = UID;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
