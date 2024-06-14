package com.pro.springPlayers.repository;

import java.util.List;

import com.pro.springPlayers.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>  {
	List<Player> findByTeamId(Long teamId);
}

