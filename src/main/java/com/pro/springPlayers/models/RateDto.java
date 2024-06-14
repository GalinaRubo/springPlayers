package com.pro.springPlayers.models;

import java.util.List;

public class RateDto {
	
	private List<Rates> ratesItems;

	public RateDto() {
	}

	public RateDto(List<Rates> ratesItems) {
		super();
		this.ratesItems = ratesItems;
	}

	public void addRates(Rates rates) {
		this.ratesItems.add(rates);
	}

	public List<Rates> getRatesItems() {
		return ratesItems;
	}

	public void setRates(List<Rates> ratesItems) {
		this.ratesItems = ratesItems;
	}


}
