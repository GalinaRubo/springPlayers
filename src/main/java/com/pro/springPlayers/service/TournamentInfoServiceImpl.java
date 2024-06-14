package com.pro.springPlayers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.springPlayers.models.TournamentInfo;
import com.pro.springPlayers.repository.TournamentInfoRepository;

@Service
public class TournamentInfoServiceImpl implements TournamentInfoService {
	@Autowired
	private TournamentInfoRepository repository;

	@Override
	public List<TournamentInfo> findAll() {
		return repository.findAll();
	}

	@Override
	public List<TournamentInfo> getAllTournamentInfoByName(String name) {
		List<TournamentInfo> currentTournamentInfo = new ArrayList<>();
		List<TournamentInfo> alltournamentInfo = findAll();
		for (TournamentInfo tournamentInfo : alltournamentInfo) {
			if (tournamentInfo.getTournament().getName() == name)
				currentTournamentInfo.add(tournamentInfo);
		}
		return currentTournamentInfo;
	}

	@Override
	public void saveTournamentInfo(TournamentInfo tournamentInfo) {
		repository.save(tournamentInfo);

	}

	@Override
	public void deleteTournamentInfoById(Long id) {
		repository.deleteById(id);

	}

	@Override
	public void updateTournamentInfo(Long id, TournamentInfo tournamentInfo) {
		TournamentInfo _tournamentInfo = repository.findById(id).orElse(null);
		if (_tournamentInfo != null) {
//			_tournamentInfo.setTournament(tournamentInfo.getTournament());
//			_tournamentInfo.setTeam(tournamentInfo.getTeam());
			_tournamentInfo.setSubgroup(tournamentInfo.getSubgroup());
			repository.save(_tournamentInfo);

		}

	}

	@Override
	public TournamentInfo getTournamentInfoById(Long id) {
		return repository.findById(id).orElse(null);
	}
}
