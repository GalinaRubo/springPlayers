package com.pro.springPlayers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.springPlayers.models.Rates;
import com.pro.springPlayers.repository.RatesRepository;

@Service
public class RatesServiceImpl implements RatesService {
	
	@Autowired
	private RatesRepository repository;

	@Override
	public List<Rates> getAllRates() {
		return repository.findAll();
	}

	@Override
	public List<Rates> getRatesByTeamId(Long teamId) {		
		return repository.findByTeamId(teamId);
	}

	@Override
	public void saveRates(Rates rates) {
		repository.save(rates);
		
	}

	@Override
	public Rates getRatesById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void updateRates(Long id, Rates rates) {
		Rates _rates = repository.findById(id).orElse(null);
		if(_rates != null) {

			_rates.setTeam(rates.getTeam());				 
			_rates.setRival(rates.getRival());		
			repository.save(_rates);
		}
		
	}

	@Override
	public void deleteRatesById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public List<Rates> getRatesByTournamentId(Long tournamentId) {
		return repository.findByTournamentId(tournamentId);
	}
}
