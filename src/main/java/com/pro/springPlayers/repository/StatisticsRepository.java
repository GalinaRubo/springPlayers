package com.pro.springPlayers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.springPlayers.models.Statistics;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
	List<Statistics> findByTournamentId(Long tournamentId);
	List<Statistics> findByRateId(Long rateId);
}
