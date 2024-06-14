package com.pro.springPlayers.models;

public class TrueBet {
	
	private Long rateId;
	private Long betId;

	
	public TrueBet() {}

	public TrueBet(Long rateId, Long betId) {
		super();
		this.rateId = rateId;
		this.betId = betId;
	}


	public Long getRateId() {
		return rateId;
	}

	public void setRateId(Long rateId) {
		this.rateId = rateId;
	}

	public Long getBetId() {
		return betId;
	}

	public void setBetId(Long betId) {
		this.betId = betId;
	}

}
