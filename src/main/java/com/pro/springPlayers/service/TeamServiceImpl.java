package com.pro.springPlayers.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.pro.springPlayers.models.Team;
import com.pro.springPlayers.repository.TeamRepository;

@Service

public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository repository;

	@Override
	public List<Team> getAllTeams() {
		return repository.findAll();
	}

	@Override
	public void saveTeam(Team team) {
		repository.save(team);

	}

	@Override
	public Team getTeamById(Long id) {
		return repository.findById(id).orElse(null);
	}


	@Override
	public void updateTeam(Long id, Team team) {
		Team _team = repository.findById(id).orElse(null);
		if (_team != null) {
			_team.setName(team.getName());
			_team.setCity(team.getCity());
			_team.setLiga(team.getLiga());
			repository.save(_team);
		}
	}

	@Override
	public void deleteTeam(Long id) {
		repository.deleteById(id);
		;

	}

	@Override
	public Page<Team> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Sort.by(sortField).descending();
		PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
		return this.repository.findAll(pageable);
	}

}
