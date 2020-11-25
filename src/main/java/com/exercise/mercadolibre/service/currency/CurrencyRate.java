package com.exercise.mercadolibre.service.currency;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRate {
	
	private String date;
	private Map<String, String> rates;
	
	public CurrencyRate() {}
	
	public CurrencyRate(Map<String, String> rates) {
		this.rates = rates;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, String> getRates() {
		return rates;
	}

	public void setRates(Map<String, String> rates) {
		this.rates = rates;
	}

}
