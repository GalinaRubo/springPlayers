package com.pro.springPlayers.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pro.springPlayers.models.Player;

public interface PlayerService {
	
	List<Player> getAllPlayers();
	List<Player> getPlayersByTeamId(Long teamId);
	void savePlayer(Player player);
	Player getPlayerById(Long id);
	void updatePlayer(Long id, Player player);
	void deletePlayerById(Long id);
	Page<Player> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
