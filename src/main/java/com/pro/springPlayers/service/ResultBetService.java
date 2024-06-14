package com.pro.springPlayers.service;

import java.util.List;

import com.pro.springPlayers.models.ResultBet;

public interface ResultBetService {
	
	List<ResultBet> getAllResultBets();
	List<ResultBet> getAllResultBetByTournamentId(Long tournamentId);
	ResultBet getResultBetByUserLogin(String login);
	void saveResultBet(ResultBet resultBet);
	ResultBet getResultBetById(Long id);
	void updateResultBet(Long id, ResultBet resultBet);
	void deleteResultBetById(Long id);

}
