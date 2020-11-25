package com.exercise.mercadolibre.service.country;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CountryService {
	
	private static final Logger log = LoggerFactory.getLogger(CountryService.class);
	
	@Value("${api.country.name.url}")
	private String url;

	public InfoCountry getInfoCountry(String code)
	{
		log.debug("Request name: "+code);
	    String uri = url + code;
	    RestTemplate restTemplate = new RestTemplate();
	    InfoCountry[] result = restTemplate.getForObject(uri, InfoCountry[].class);
	    return result[0]; 
	}
}
