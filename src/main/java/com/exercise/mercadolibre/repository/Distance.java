package com.exercise.mercadolibre.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Distance {

    @Id
    @GeneratedValue
    private Long id;
	private int kilometers;
	private String country;
	
	public Distance() {}
	
	public Distance(String name, int distance) {
		this.country = name;
		this.kilometers = distance;
	}
	
	public int getKilometers() {
		return kilometers;
	}
	public void setKilometers(int kilometers) {
		this.kilometers = kilometers;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
