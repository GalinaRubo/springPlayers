package com.pro.springPlayers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.springPlayers.models.ResultBet;

@Repository
public interface ResultBetRepository extends JpaRepository<ResultBet, Long>{
	ResultBet getResultBetByUserLogin(String login);
	List<ResultBet> getAllResultBetByTournamentId(Long tournamentId);
}
