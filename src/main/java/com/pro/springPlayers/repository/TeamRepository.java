package com.pro.springPlayers.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pro.springPlayers.models.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}