package com.pro.springPlayers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pro.springPlayers.models.Tournament;
import com.pro.springPlayers.repository.TournamentRepository;

@Service

public class TournamentServiceImpl implements TournamentService{
	@Autowired
	private TournamentRepository repository;
	@Override
	public List<Tournament> getAllTournaments() {
		return repository.findAll();
	}

	@Override
	public void saveTournament(Tournament tournament) {
		repository.save(tournament);
		
	}

	@Override
	public Tournament getTournamentById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void updateTournament(Long id, Tournament tournament) {
		Tournament _tournament = repository.findById(id).orElse(null);
		if(_tournament != null) {
			_tournament.setName(tournament.getName());				 
			_tournament.setDate(tournament.getDate());
			_tournament.setCity(tournament.getCity());
			repository.save(_tournament);
		}
		
	}

	@Override
	public void deleteTournamentById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public Page<Tournament> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Sort.by(sortField).descending();
		PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
		return this.repository.findAll(pageable);
	}

}
