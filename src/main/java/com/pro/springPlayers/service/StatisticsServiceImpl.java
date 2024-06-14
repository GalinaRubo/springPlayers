package com.pro.springPlayers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.springPlayers.models.Statistics;
import com.pro.springPlayers.repository.StatisticsRepository;

@Service

public class StatisticsServiceImpl implements StatisticsService {
	
	@Autowired
	private StatisticsRepository repositoryStat;


	@Override
	public List<Statistics> getAllStatistics() {
		return repositoryStat.findAll();
	}

	@Override
	public List<Statistics> getStatisticsByTournamentId(Long tournamentId) {
		return repositoryStat.findByTournamentId(tournamentId);
	}
	
	
	@Override
	public void saveStatistics(Statistics stat) {
		repositoryStat.save(stat);
		
	}

	@Override
	public Statistics getStatisticsById(Long id) {		
		return repositoryStat.findById(id).orElse(null);
	}

	@Override
	public void updateStatistics(Long id, Statistics stat) {
		
		Statistics _stat = repositoryStat.findById(id).orElse(null);
		if(_stat != null) {
			_stat.setScoreTeam(stat.getScoreTeam());				 
			_stat.setScoreRival(stat.getScoreRival());
			_stat.setTeam(stat.getTeam());
			repositoryStat.save(_stat);
		}
		
		
	}

	@Override
	public void deleteStatisticsById(Long id) {
		repositoryStat.deleteById(id);
		
	}

	@Override
	public List<Statistics> getStatisticsByRateId(Long rivalId) {
		return repositoryStat.findByRateId(rivalId); 
	}

}
