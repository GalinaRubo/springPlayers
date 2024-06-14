package com.pro.springPlayers.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "teams")
public class Team {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String city;
	private String liga;
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private List<Player> players;
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private List<TournamentInfo> tournamentsinfo;	
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private List<Rates> rates;
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private List<Statistics> statistics;
	
	
	public Team() {}
	
	public Team(String name, String city, String liga) {
		super();
		this.name = name;
		this.city = city;
		this.liga = liga;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public String getLiga() {
		return liga;
	}
	public void setLiga(String liga) {
		this.liga = liga;		
	}

	public List<TournamentInfo> getTournamentsInfo() {
		return tournamentsinfo;
	}

	public void setTournamentsInfo(List<TournamentInfo> tournamentsinfo) {
		this.tournamentsinfo = tournamentsinfo;
	}

	public List<Rates> getRates() {
		return rates;
	}

	public void setRates(List<Rates> rates) {
		this.rates = rates;
	}

	public List<Statistics> getStatistics() {
		return statistics;
	}

	public void setStatistic(List<Statistics> statistics) {
		this.statistics = statistics;
	}
	
	
}

	
