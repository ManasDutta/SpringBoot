package com.manas.stock.dbservice.model;

import java.util.List;

public class Quotes {

	private String userName;

	private List<String> quotes;

	public Quotes(String userName, List<String> quotes) {
		super();
		this.userName = userName;
		this.quotes = quotes;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the quotes
	 */
	public List<String> getQuotes() {
		return quotes;
	}

	/**
	 * @param quotes the quotes to set
	 */
	public void setQuotes(List<String> quotes) {
		this.quotes = quotes;
	}

}
