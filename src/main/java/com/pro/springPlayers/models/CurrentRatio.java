package com.pro.springPlayers.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "currentratios")

public class CurrentRatio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	@JoinColumn(name = "rates_id")
	private Rates rates;
	@JoinColumn(name = "user_login")
	private String userLogin;
	@ManyToOne
	@JoinColumn(name = "bet_id")
	private TypeBet typeBet;
	@JoinColumn(name = "ratio_bet")
	private Double ratioBet;
	@JoinColumn(name = "size_bet")
	private Integer sizeBet;
	@JoinColumn(name = "yes_no")
	private Boolean yesNo;

	public CurrentRatio() {}

	public CurrentRatio(Rates rates, String userLogin, TypeBet typeBet, Double ratioBet, Integer sizeBet,
			Boolean yesNo) {
		super();
		this.rates = rates;
		this.userLogin = userLogin;
		this.typeBet = typeBet;
		this.ratioBet = ratioBet;
		this.sizeBet = sizeBet;
		this.yesNo = yesNo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Rates getRates() {
		return rates;
	}

	public void setRates(Rates rates) {
		this.rates = rates;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public TypeBet getTypeBet() {
		return typeBet;
	}

	public void setTypeBet(TypeBet typeBet) {
		this.typeBet = typeBet;
	}

	public Double getRatioBet() {
		return ratioBet;
	}

	public void setRatioBet(Double ratioBet) {
		this.ratioBet = ratioBet;
	}

	public Integer getSizeBet() {
		return sizeBet;
	}

	public void setSizeBet(Integer sizeBet) {
		this.sizeBet = sizeBet;
	}

	public Boolean getYesNo() {
		return yesNo;
	}

	public void setYesNo(Boolean yesNo) {
		this.yesNo = yesNo;
	}
}
