package com.pro.springPlayers.service;

import java.util.List;

import com.pro.springPlayers.models.Statistics;

public interface StatisticsService {
	
	List<Statistics> getAllStatistics();
	List<Statistics> getStatisticsByTournamentId(Long tournament);
	List<Statistics> getStatisticsByRateId(Long rateId);
	void saveStatistics(Statistics stat);
	Statistics getStatisticsById(Long id);
	void updateStatistics(Long id, Statistics stat);
	void deleteStatisticsById(Long id);
}
