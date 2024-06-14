package com.pro.springPlayers.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "resultbets")
public class ResultBet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "tournament_id")
	private Tournament tournament;
	
	@JoinColumn(name = "user_login")
	private String userLogin;
	
	@JoinColumn(name = "result_bet")
	private float resultBet;

	
	public ResultBet() {}


	public ResultBet(Tournament tournament, String userLogin, float resultBet) {
		super();
		this.tournament = tournament;
		this.userLogin = userLogin;
		this.resultBet = resultBet;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Tournament getTournament() {
		return tournament;
	}


	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}


	public String getUserLogin() {
		return userLogin;
	}


	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}


	public float getResultBet() {
		return resultBet;
	}


	public void setResultBet(float resultBet) {
		this.resultBet = resultBet;
	}

}
