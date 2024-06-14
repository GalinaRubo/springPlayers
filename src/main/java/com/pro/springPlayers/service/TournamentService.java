package com.pro.springPlayers.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pro.springPlayers.models.Tournament;

public interface TournamentService {
	
	List<Tournament> getAllTournaments();
	void saveTournament(Tournament tournament);
	Tournament getTournamentById(Long id);
	void updateTournament(Long id, Tournament tournament);
	void deleteTournamentById(Long id);
	Page<Tournament> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
