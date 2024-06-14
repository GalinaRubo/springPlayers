package com.pro.springPlayers.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ratios")
public class Ratio {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@JoinColumn(name = "rate_id")
	private Long rateId;

	@ManyToOne
	@JoinColumn(name = "type_ratio_id")
	private TypeBet typeBet;
	
	@JoinColumn(name = "size_ratio")
	private Double sizeRatio;

	
	public Ratio(){}


	public Ratio(Long rateId, TypeBet typeBet, Double sizeRatio) {
		super();
		this.rateId = rateId;
		this.typeBet = typeBet;
		this.sizeRatio = sizeRatio;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getRateId() {
		return rateId;
	}


	public void setRateId(Long rateId) {
		this.rateId = rateId;
	}


	public TypeBet getTypeBet() {
		return typeBet;
	}


	public void setTypeBet(TypeBet typeBet) {
		this.typeBet = typeBet;
	}


	public Double getSizeRatio() {
		return sizeRatio;
	}


	public void setSizeRatio(Double sizeRatio) {
		this.sizeRatio = sizeRatio;
	}

}
