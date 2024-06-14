package com.pro.springPlayers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.springPlayers.models.ResultBet;
import com.pro.springPlayers.repository.ResultBetRepository;

@Service

public class ResultBetServiceImpl implements ResultBetService {
	
	@Autowired
	private ResultBetRepository repository;


	@Override
	public List<ResultBet> getAllResultBets() {		
		return repository.findAll();
	}

	@Override
	public ResultBet getResultBetByUserLogin(String login) {
		return repository.getResultBetByUserLogin(login);
	}

	@Override
	public void saveResultBet(ResultBet resultBet) {
		repository.save(resultBet);
		
	}

	@Override
	public ResultBet getResultBetById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void updateResultBet(Long id, ResultBet resultBet) {
		ResultBet rb = repository.findById(id).orElse(null);
		if(rb != null) {
			rb.setTournament(resultBet.getTournament());				 
			rb.setUserLogin(resultBet.getUserLogin());
			rb.setResultBet(resultBet.getResultBet());
			repository.save(rb);
		}
		
	}

	@Override
	public void deleteResultBetById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public List<ResultBet> getAllResultBetByTournamentId(Long tournamentId) {		
		return repository.getAllResultBetByTournamentId(tournamentId);
	}

}
