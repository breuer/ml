package com.exercise.mercadolibre.service.country;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {

	private String code;
	private String name;
	private String euroRate;
	
	public Currency() {}
	
	public Currency(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEuroRate() {
		return euroRate;
	}

	public void setEuroRate(String euroRate) {
		this.euroRate = euroRate;
	}
	
}
