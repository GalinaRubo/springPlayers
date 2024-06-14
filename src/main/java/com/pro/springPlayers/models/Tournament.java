package com.pro.springPlayers.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tournaments")
public class Tournament {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private Date date;
	private String city;
	@OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
	private List<Rates> rates;
	@OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
	private List<ResultBet> resultbets;
	
	
	public Tournament() {}


	public Tournament(String name, Date date, String city, List<Rates> rates, List<ResultBet> resultbets) {
		super();
		this.name = name;
		this.date = date;
		this.city = city;
		this.rates = rates;
		this.resultbets = resultbets;
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


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public List<Rates> getRates() {
		return rates;
	}


	public void setRates(List<Rates> rates) {
		this.rates = rates;
	}


	public List<ResultBet> getResultbets() {
		return resultbets;
	}


	public void setResultbets(List<ResultBet> resultbets) {
		this.resultbets = resultbets;
	}	
}
