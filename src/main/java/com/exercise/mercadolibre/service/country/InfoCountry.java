package com.exercise.mercadolibre.service.country;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.exercise.mercadolibre.helper.HaversineFormula;
import com.exercise.mercadolibre.helper.TimeHelper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoCountry {
	
	public InfoCountry() {}
	public InfoCountry(
			List<String> latlng, 
			List<Language> languages,
			List<String> timezones,
			List<Currency> currencies) {
		this.latlng = latlng;
		this.languages = languages;
		this.timezones = timezones;
		this.currencies = currencies;
	}
	
	private List<String> latlng;
	private List<Language> languages;
	private List<String> timezones;
	private List<Currency> currencies;

	public List<String> getTimesFromTimezones() {
    	List<String> times = new ArrayList<>();
    	for(String timezone : getTimezones()) {
    		String time = TimeHelper.getTimeFromTimezone(timezone) + " ("+timezone+")";
    		times.add(time);
    	}
    	return times;
	}
	public List<Currency> getCurrencies() {
		return currencies;
	}
	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}
	public List<Language> getLanguages() {
		return languages;
	}
	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}
	public List<String> getTimezones() {
		return timezones;
	}
	public void setTimezones(List<String> timezones) {
		this.timezones = timezones;
	}
	public List<String> getLatlng() {
		return latlng;
	}
	public void setLatlng(List<String> latlng) {
		this.latlng = latlng;
	}
	
	public int getDistanceBsAsKilometers() {
		if(!getLatlng().isEmpty() && getLatlng().size() == 2) {
			Double lat = new Double(this.getLatlng().get(0));
			Double lon = new Double(this.getLatlng().get(1));
			return HaversineFormula.distanceBetweenBuenosAiresInKilometers(lon, lat);
		}
		return -1;
	}
	
	public List<String> getLanguagesStringList() {
		return getLanguages().stream()
		    .map(Language::getName)
		    .collect(Collectors.toList());
	}
}
