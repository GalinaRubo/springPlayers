package com.pro.springPlayers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.springPlayers.models.TypeBet;


@Repository
public interface TypeBetRepository extends JpaRepository<TypeBet, Long> {
	
	TypeBet findByName(String name);

}
