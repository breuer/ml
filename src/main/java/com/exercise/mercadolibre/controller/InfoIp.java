package com.exercise.mercadolibre.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoIp {
	
	private String ip;
	private String isoCode;
	private String country;
	private String distanceToBuenosAires;
	private List<String> currencies;
	private List<String> languages;
	private List<String> times;
	
	public InfoIp() {}
	
	@JsonProperty("iso_code")
	public String getIsoCode() {
		return isoCode;
	}
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String name) {
		this.country = name;
	}
	@JsonProperty("distance_to_Buenos_Aires_in_kms")
	public String getDistanceToBuenosAires() {
		return distanceToBuenosAires;
	}
	public void setDistanceToBuenosAires(String distanceToBuenosAires) {
		this.distanceToBuenosAires = distanceToBuenosAires;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public List<String> getTimes() {
		return times;
	}

	public void setTimes(List<String> times) {
		this.times = times;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public List<String> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<String> currencies) {
		this.currencies = currencies;
	}

}
