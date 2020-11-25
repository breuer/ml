package com.exercise.mercadolibre.service.geolocation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IpCountry {

	private String countryCode;
	private String countryCode3;
	private String countryName;
	
	public IpCountry() {}
	
	public IpCountry(String countryCode, String countryCode3, String countryName) {
		this.countryCode = countryCode;
		this.countryCode3 = countryCode3;
		this.countryName = countryName;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryCode3() {
		return countryCode3;
	}
	public void setCountryCode3(String countryCode3) {
		this.countryCode3 = countryCode3;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public boolean isEmpty() {
		return countryCode.isEmpty() && countryCode.isEmpty() && countryName.isEmpty(); 
	}
}
