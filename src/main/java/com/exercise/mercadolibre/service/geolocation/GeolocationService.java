package com.exercise.mercadolibre.service.geolocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.exercise.mercadolibre.error.CountryNotFoundException;

@Component
public class GeolocationService {

	private static final Logger log = LoggerFactory.getLogger(GeolocationService.class);
	
	@Value("${api.geolocationip.url}")
	private String url;

	public IpCountry getInfoIp(String ip)
	{
		log.debug("Request ip: "+ip);
		String uri = url + ip;
	    RestTemplate restTemplate = new RestTemplate();
	    IpCountry result = restTemplate.getForObject(uri, IpCountry.class);
	    if(result.isEmpty())
	    	throw new CountryNotFoundException(ip);
	  
	    return result; 
	}
}
