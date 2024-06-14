package com.pro.springPlayers.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "statistics")
public class Statistics {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "tournament_id")
	private Tournament tournament;
	
	private String team_group;
	
	@ManyToOne
	@JoinColumn(name = "rate_id")
	private Rates rate;
	
	@JoinColumn(name = "score_team")
	private Integer scoreTeam;
		
	@JoinColumn(name = "score_rival")
	private Integer scoreRival;
	
	private Integer game_part;
	
	@ManyToOne
	@JoinColumn(name = "first_goal_team_id")
	private Team team;
		
	public Statistics(){}

	public Statistics(Tournament tournament, String team_group, Rates rate, Integer scoreTeam, Integer scoreRival,
			Integer game_part, Team team) {
		super();
		this.tournament = tournament;
		this.team_group = team_group;
		this.rate = rate;
		this.scoreTeam = scoreTeam;
		this.scoreRival = scoreRival;
		this.game_part = game_part;
		this.team = team;
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

	public String getTeam_group() {
		return team_group;
	}

	public void setTeam_group(String team_group) {
		this.team_group = team_group;
	}

	public Rates getRate() {
		return rate;
	}

	public void setRate(Rates rate) {
		this.rate = rate;
	}

	public Integer getScoreTeam() {
		return scoreTeam;
	}

	public void setScoreTeam(Integer scoreTeam) {
		this.scoreTeam = scoreTeam;
	}

	public Integer getScoreRival() {
		return scoreRival;
	}

	public void setScoreRival(Integer scoreRival) {
		this.scoreRival = scoreRival;
	}

	public Integer getGame_part() {
		return game_part;
	}

	public void setGame_part(Integer game_part) {
		this.game_part = game_part;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}	
	
}
