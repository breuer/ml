package com.exercise.mercadolibre.controller;

import javax.validation.constraints.NotEmpty;


public class Ip {
	
	@NotEmpty(message = "Please provide a ip")
	private String ip;

	public Ip() {}
	public Ip(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
