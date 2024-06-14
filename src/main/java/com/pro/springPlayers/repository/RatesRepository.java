package com.pro.springPlayers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.springPlayers.models.Rates;

@Repository
public interface RatesRepository  extends JpaRepository<Rates, Long> {
	List<Rates> findByTeamId(Long teamId);
	List<Rates> findByTournamentId(Long tournamentId);
}
