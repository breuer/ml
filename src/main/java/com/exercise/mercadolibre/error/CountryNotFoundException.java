package com.exercise.mercadolibre.error;

public class CountryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CountryNotFoundException(String ip) {
		super("Country not found for ip: " + ip);
	}
}
