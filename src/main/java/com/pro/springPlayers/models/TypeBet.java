package com.pro.springPlayers.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "typesbets")
public class TypeBet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@JoinColumn(name = "name_bet")
	private String name;
	@OneToMany(mappedBy = "typeBet", cascade = CascadeType.ALL)
	private List<Ratio> ratios;
	@OneToMany(mappedBy = "typeBet", cascade = CascadeType.ALL)
	private List<CurrentRatio> currentRatios;

	
	public TypeBet() {}


	public TypeBet(String name, List<Ratio> ratios, List<CurrentRatio> currentRatios) {
		super();
		this.name = name;
		this.ratios = ratios;
		this.currentRatios = currentRatios;
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


	public List<Ratio> getRatios() {
		return ratios;
	}


	public void setRatios(List<Ratio> ratios) {
		this.ratios = ratios;
	}


	public List<CurrentRatio> getCurrentRatios() {
		return currentRatios;
	}


	public void setCurrentRatios(List<CurrentRatio> currentRatios) {
		this.currentRatios = currentRatios;
	}
}
