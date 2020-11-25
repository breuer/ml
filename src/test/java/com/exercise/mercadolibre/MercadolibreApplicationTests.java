package com.exercise.mercadolibre;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.exercise.mercadolibre.controller.Ip;
import com.exercise.mercadolibre.repository.Distance;
import com.exercise.mercadolibre.repository.DistanceRepository;
import com.exercise.mercadolibre.service.country.CountryService;
import com.exercise.mercadolibre.service.country.Currency;
import com.exercise.mercadolibre.service.country.InfoCountry;
import com.exercise.mercadolibre.service.country.Language;
import com.exercise.mercadolibre.service.currency.CurrencyRate;
import com.exercise.mercadolibre.service.currency.CurrencyService;
import com.exercise.mercadolibre.service.geolocation.GeolocationService;
import com.exercise.mercadolibre.service.geolocation.IpCountry;
import com.fasterxml.jackson.databind.ObjectMapper;

//import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MercadolibreApplicationTests {

	private static final ObjectMapper om = new ObjectMapper();
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
    private DistanceRepository repository;
	@MockBean
	private GeolocationService geolocationService;
	@MockBean
	private CountryService countryService;
	@MockBean
	private CurrencyService currencyService;
		
	@Test
	public void get_statistics_ok() throws Exception {
		List<Distance> distances = Arrays.asList(
		 new Distance("japon", 32000),
		 new Distance("cuba", 12000),
		 new Distance("italia", 16000));
	
		Mockito.when(repository.findAll()).thenReturn(distances);
		
		mockMvc.perform(get("/statistics"))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		    .andExpect(status().isOk())
		    .andExpect(jsonPath("$.farthest_distance_to_Buenos_Aires_in_kms").value(32000.0))
		    .andExpect(jsonPath("$.closest_distance_to_Buenos_Aires_in_kms").value(12000.0))
		    .andExpect(jsonPath("$.average_distance_to_Buenos_Aires_in_kms").value(20000.0))
		    .andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void post_ip_ok() throws Exception {
		
		IpCountry ipCountry = new IpCountry("HK","HKG","Hong Kong"); 
		
		List<String> latlng = Arrays.asList(
				"22.25",
			    "114.16666666");
		
		List<Language> languages = Arrays.asList(
				new Language("English"),
				new Language("Chinese"));
		
		List<String> timezones = Arrays.asList(
				"UTC+08:00");
		
		List<Currency> currencies = Arrays.asList(
				new Currency("HKD","Hong Kong dollar"));
		
		InfoCountry infoCountry = new InfoCountry(latlng, languages, timezones, currencies);
		
		Map<String, String> rates = new HashMap<String, String>();
		rates.put("HKD", "9.22961");
		CurrencyRate currencyRate = new CurrencyRate(rates);
		
		Mockito.when(geolocationService.getInfoIp(Mockito.anyString())).thenReturn(ipCountry);
		Mockito.when(countryService.getInfoCountry(Mockito.anyString())).thenReturn(infoCountry);
		Mockito.when(currencyService.getInfoCurrency()).thenReturn(currencyRate);
		
		Ip ip = new Ip("14.102.240.0");
		mockMvc.perform(post("/ips")
				.content(om.writeValueAsString(ip))
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		    .andExpect(status().isOk())
		    .andExpect(jsonPath("$.iso_code").value("HK"))
		    .andExpect(jsonPath("$.country").value("Hong Kong"))
		    .andExpect(jsonPath("$.distance_to_Buenos_Aires_in_kms").value(18460))
		    .andExpect(jsonPath("$.currencies").isArray())
		    .andExpect(jsonPath("$.currencies", hasSize(1)))
		    .andExpect(jsonPath("$.currencies[0]").value("HKD (1 EUR = 0.10834693990320285 HKD)"))
		    .andExpect(jsonPath("$.languages").isArray())
		    .andExpect(jsonPath("$.languages", hasSize(2)))
		    .andExpect(jsonPath("$.languages[0]").value("English"))
		    .andExpect(jsonPath("$.languages[1]").value("Chinese"))
		    .andExpect(jsonPath("$.times").isArray())
		    .andExpect(jsonPath("$.times", hasSize(1)))
		    .andDo(MockMvcResultHandlers.print());
	}
	
}
