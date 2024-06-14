package com.pro.springPlayers.models;

import java.util.List;

public class Stat {

	private List<Statistics> statItems;

	public Stat() {
	}

	public Stat(List<Statistics> statItems) {
		super();
		this.statItems = statItems;
	}

	public void addStat(Statistics statistics) {
		this.statItems.add(statistics);
	}

	public List<Statistics> getStatItems() {
		return statItems;
	}

	public void setStatItems(List<Statistics> statItems) {
		this.statItems = statItems;
	}

}
