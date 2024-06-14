package com.pro.springPlayers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.springPlayers.models.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long>{

}
