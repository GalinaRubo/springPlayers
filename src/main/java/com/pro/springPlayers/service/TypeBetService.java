package com.pro.springPlayers.service;

import java.util.List;

import com.pro.springPlayers.models.TypeBet;


public interface TypeBetService {
	
	List<TypeBet> getAllTypesBets();
	TypeBet getTypeBetById(Long id);
	TypeBet getTypeBetByName(String name);
	void saveTypeBet(TypeBet typeBet);
	void updateTypeBet(Long id, TypeBet typeBet);
	void deleteTypeBetById(Long id);

}
