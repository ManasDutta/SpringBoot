/**
 * 
 */
package com.manas.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manas.stock.dbservice.model.Quote;
import com.manas.stock.dbservice.model.Quotes;
import com.manas.stock.dbservice.repository.QuotesRepository;

/**
 * @author manas
 *
 */
@RestController
@RequestMapping("rest/db")
public class DBServiceResource {

	@Autowired
	private QuotesRepository quotesRepository;

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String userName) {

		return getQuotesByUserName(userName);

	}

	@PostMapping("/add")
	public List<String> add(@RequestBody final Quotes quotes) {

		quotes.getQuotes().stream().forEach(quote -> quotesRepository.save(new Quote(quotes.getUserName(), quote)));
		return getQuotesByUserName(quotes.getUserName());
	}

	@DeleteMapping("/delete/{username}")
	public List<String> delete(@PathVariable("username") final String userName) {
		List<Quote> quotes = quotesRepository.findByUserName(userName);
		quotesRepository.deleteAll(quotes);

		return getQuotesByUserName(userName);
	}

	private List<String> getQuotesByUserName(String userName) {
		return quotesRepository.findByUserName(userName).stream().map(Quote::getQuote).collect(Collectors.toList());
	}
}
