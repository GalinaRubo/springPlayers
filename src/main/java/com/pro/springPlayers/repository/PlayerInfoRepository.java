package com.pro.springPlayers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pro.springPlayers.models.PlayerInfo;

@Repository
public interface PlayerInfoRepository extends JpaRepository<PlayerInfo, Long> {

}
