package com.exercise.mercadolibre.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.mercadolibre.repository.Distance;
import com.exercise.mercadolibre.repository.DistanceRepository;
import com.exercise.mercadolibre.service.country.CountryService;
import com.exercise.mercadolibre.service.country.Currency;
import com.exercise.mercadolibre.service.country.InfoCountry;
import com.exercise.mercadolibre.service.currency.CurrencyRate;
import com.exercise.mercadolibre.service.currency.CurrencyService;
import com.exercise.mercadolibre.service.geolocation.GeolocationService;
import com.exercise.mercadolibre.service.geolocation.IpCountry;

@RestController
public class Controller {

	private String NOT_AVAILABLE = "not available";
	
	private static final Logger log = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	private DistanceRepository repository;
	@Autowired
	private GeolocationService geolocationService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private CurrencyService currencyService;
	
	private DecimalFormat df = new DecimalFormat("0.0000");
	
	@PostMapping("/trace")
	InfoIp postIp(@Valid @RequestBody Ip ip) {
		
		//Geolocation of IPs
		log.debug("post ip: "+ip);
		IpCountry ipCountry = geolocationService.getInfoIp(ip.getIp());
		InfoIp infoIp = new InfoIp();
		infoIp.setIp(ip.getIp());
		infoIp.setCountry(ipCountry.getCountryName());
		infoIp.setIsoCode(ipCountry.getCountryCode());
		
		//Country information
    	//InfoCountry infoCountry = countryService.getInfoCountry(ipCountry.getCountryName());
		InfoCountry infoCountry = countryService.getInfoCountry(ipCountry.getCountryCode3());
    	infoIp.setTimes(infoCountry.getTimesFromTimezones());
    	infoIp.setLanguages(infoCountry.getLanguagesStringList());
    	
		
    	//Local currency and its current price in euros
    	CurrencyRate currencyRate = currencyService.getInfoCurrency();
    	List<String> currencies = new ArrayList<>();
    	for(Currency currency : infoCountry.getCurrencies()) {
    		if(currencyRate.getRates().get(currency.getCode()) != null) {
    			String rateStr = currencyRate.getRates().get(currency.getCode());
    			Double rate = new Double(rateStr);
    			Double rateInEuro = 1 / rate;
    			//currencies.add(currency.getCode() + " (1 EUR = " + rateInEuro.toString() + " " + currency.getCode() + ")");
    			currencies.add(currency.getCode() + " (1 EUR = " + df.format(rateInEuro) + " " + currency.getCode() + ")");
    		} else {
    			currency.setEuroRate(NOT_AVAILABLE);
    		}
    	}
    	infoIp.setCurrencies(currencies);
    	
    	//distance between Buenos Aires
    	int distance = infoCountry.getDistanceBsAsKilometers();
    	if(distance < 0 )
    		infoIp.setDistanceToBuenosAires(NOT_AVAILABLE);
    	else
    		infoIp.setDistanceToBuenosAires(String.valueOf(distance));
    	
    	//store query
    	log.debug("save distance in repository");
    	repository.save(new Distance(infoIp.getCountry(), distance));
		return infoIp;
	}
	
	@GetMapping("/stats")
	Statistics getStatistics() {
		
		log.debug("get statistics");
		List<Distance> distances = repository.findAll();
		
		log.debug("calculating distances");
		Distance min = distances
				.stream()
				.min(Comparator.comparing(Distance::getKilometers))
				.orElseThrow(NoSuchElementException::new);

		Distance max = distances
				.stream()
				.max(Comparator.comparing(Distance::getKilometers))
				.orElseThrow(NoSuchElementException::new);

		Double average = distances
				.stream()
				.collect(Collectors.averagingInt(d -> d.getKilometers()));
		
		Statistics statistics = new Statistics();
		statistics.setClosestDistance(min.getKilometers());
		statistics.setFarthestDistance(max.getKilometers());
		statistics.setAverageDistance(average);
		
		return statistics;
	}
}

