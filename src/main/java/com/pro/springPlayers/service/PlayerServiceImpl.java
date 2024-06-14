package com.pro.springPlayers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pro.springPlayers.models.Player;
import com.pro.springPlayers.repository.PlayerRepository;

	@Service

	public class PlayerServiceImpl implements PlayerService {
		
		@Autowired
		private PlayerRepository repository;
		@Override
		public List<Player> getAllPlayers() {
			return repository.findAll() ;
		}		

		@Override
		public List<Player> getPlayersByTeamId(Long teamId) {
			return repository.findByTeamId(teamId);
		}

		@Override
		public void savePlayer(Player player) {			
			repository.save(player);
			
		}

		@Override
		public Player getPlayerById(Long id) {
			return repository.findById(id).orElse(null);
		}

		@Override
		public void updatePlayer(Long id, Player player) {
			Player _player = repository.findById(id).orElse(null);
			if(_player != null) {
				_player.setName(player.getName());				 
				_player.setTeam(player.getTeam());							
				repository.save(_player);
			}
			
		}

		@Override
		public void deletePlayerById(Long id) {
			repository.deleteById(id);
			
		}

		@Override
		public Page<Player> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
			Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
			Sort.by(sortField).descending();
			PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
			return this.repository.findAll(pageable);
		}

}
