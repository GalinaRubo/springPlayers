package com.pro.springPlayers.service;

import java.util.List;

import com.pro.springPlayers.models.Rates;

public interface RatesService {
	
	List<Rates> getAllRates();
	List<Rates> getRatesByTeamId(Long teamId);
	List<Rates> getRatesByTournamentId(Long tournamentId);
	void saveRates(Rates rates);
	Rates getRatesById(Long id);
	void updateRates(Long id, Rates rates);
	void deleteRatesById(Long id);

}
