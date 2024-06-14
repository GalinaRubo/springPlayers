package com.pro.springPlayers.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rates")
public class Rates {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@ManyToOne
	@JoinColumn(name = "tournament_id")
	private Tournament tournament;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@ManyToOne
	@JoinColumn(name = "rival_id")
	private Team rival;
	
	@OneToMany(mappedBy = "rates", cascade = CascadeType.ALL)
	private List<CurrentRatio> currentRatio;
	
	@OneToMany(mappedBy = "rate", cascade = CascadeType.ALL)
	private List<Statistics> statistics;
	

	
	public Rates(){}

	public Rates(Tournament tournament, Team team, Team rival, List<CurrentRatio> currentRatio,
			List<Statistics> statistics) {
		super();
		this.tournament = tournament;
		this.team = team;
		this.rival = rival;
		this.currentRatio = currentRatio;
		this.statistics = statistics;
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



	public Team getTeam() {
		return team;
	}



	public void setTeam(Team team) {
		this.team = team;
	}



	public Team getRival() {
		return rival;
	}



	public void setRival(Team rival) {
		this.rival = rival;
	}



	public List<CurrentRatio> getCurrentRatio() {
		return currentRatio;
	}



	public void setCurrentRatio(List<CurrentRatio> currentRatio) {
		this.currentRatio = currentRatio;
	}



	public List<Statistics> getStatistics() {
		return statistics;
	}



	public void setStatistics(List<Statistics> statistics) {
		this.statistics = statistics;
	}
}
