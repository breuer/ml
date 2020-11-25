package com.exercise.mercadolibre.service.currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyService {
	
	private static final Logger log = LoggerFactory.getLogger(CurrencyService.class);
	
	@Value("${api.currency.url}")
	private String url;

	public CurrencyRate getInfoCurrency()
	{
		log.debug("Request currency rates");
	    RestTemplate restTemplate = new RestTemplate();
	    CurrencyRate result = restTemplate.getForObject(url, CurrencyRate.class);
	    return result; 
	}
	

}
