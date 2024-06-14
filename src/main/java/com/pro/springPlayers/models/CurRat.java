package com.pro.springPlayers.models;

import java.util.List;

public class CurRat {
	
	private List<CurrentRatio> statItems;
	
	public CurRat () {}

	public CurRat(List<CurrentRatio> statItems) {
		super();
		this.statItems = statItems;
	}
	

	public void addCurRat(CurrentRatio currentRatio) {
		this.statItems.add(currentRatio);
	}

	public List<CurrentRatio> getStatItems() {
		return statItems;
	}

	public void setStatItems(List<CurrentRatio> statItems) {
		this.statItems = statItems;
	}
	
	
	
	
}
