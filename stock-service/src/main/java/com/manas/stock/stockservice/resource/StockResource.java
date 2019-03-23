package com.manas.stock.stockservice.resource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("rest/stock")
public class StockResource {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/{username}")
	public List<Stock> getStock(@PathVariable(name = "username") final String userName) {

		ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://localhost:8300/rest/db/" + userName,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
				});
		List<String> quotes = quoteResponse.getBody();

		return quotes.stream().map(this::getStockPrice).collect(Collectors.toList());
	}

	private Stock getStockPrice(String quote) {
		try {
			return YahooFinance.get(quote);
		} catch (IOException e) {
			return new Stock(quote);
		}
	}
}
