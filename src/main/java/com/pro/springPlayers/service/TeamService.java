package com.pro.springPlayers.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pro.springPlayers.models.Team;

public interface TeamService {
	
	List<Team> getAllTeams();
	void saveTeam(Team team);
	Team getTeamById(Long id);
	void updateTeam(Long id,Team team);
	void deleteTeam(Long id);
	Page<Team> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
