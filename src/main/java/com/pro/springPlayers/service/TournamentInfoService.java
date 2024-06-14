package com.pro.springPlayers.service;

import java.util.List;

import com.pro.springPlayers.models.TournamentInfo;

public interface TournamentInfoService {
	
	List<TournamentInfo> findAll();
	List<TournamentInfo> getAllTournamentInfoByName(String name);
	TournamentInfo getTournamentInfoById(Long id);
	void updateTournamentInfo(Long id, TournamentInfo tournamentInfo);
	void saveTournamentInfo(TournamentInfo tournamentInfo);
	void deleteTournamentInfoById(Long id);
}
