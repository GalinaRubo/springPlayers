package com.pro.springPlayers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.springPlayers.models.PlayerInfo;
import com.pro.springPlayers.repository.PlayerInfoRepository;

@Service
public class PlayerInfoServiceImpl  implements PlayerInfoService {
	
	@Autowired
	private PlayerInfoRepository repository;

	@Override
	public void savePlayerInfo(PlayerInfo playerinfo) {
		repository.save(playerinfo);
		
	}


	@Override
	public void updatePlayerInfo(Long id, PlayerInfo playerinfo) {
		PlayerInfo _playerinfo = repository.findById(id).orElse(null);
		if(_playerinfo != null) {
			
			_playerinfo.setAge(playerinfo.getAge());
			_playerinfo.setHeight(playerinfo.getHeight());
			_playerinfo.setAwards(playerinfo.getAwards());
			_playerinfo.setExperience(playerinfo.getExperience());
			_playerinfo.setFoto(playerinfo.getFoto());
			repository.save(_playerinfo);
			}		
	}

	@Override
	public void deletePlayerInfoById(Long id) {
		repository.deleteById(id);
		
	}


	@Override
	public PlayerInfo getPlayerInfoById(Long id) {
		return repository.findById(id).orElse(null);
	}

}
