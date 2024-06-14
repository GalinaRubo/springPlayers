package com.pro.springPlayers.service;

import com.pro.springPlayers.models.PlayerInfo;

public interface PlayerInfoService {
	
	void savePlayerInfo(PlayerInfo playerinfo);
	void updatePlayerInfo(Long id, PlayerInfo playerinfo);
	void deletePlayerInfoById(Long id);
	PlayerInfo getPlayerInfoById(Long id);
}
