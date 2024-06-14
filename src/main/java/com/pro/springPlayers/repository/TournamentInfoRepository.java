package com.pro.springPlayers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.springPlayers.models.TournamentInfo;

@Repository
public interface TournamentInfoRepository extends JpaRepository<TournamentInfo, Long>{
	
}
