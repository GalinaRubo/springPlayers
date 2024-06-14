package com.pro.springPlayers.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pro.springPlayers.models.Player;
import com.pro.springPlayers.models.Team;
import com.pro.springPlayers.service.PlayerService;
import com.pro.springPlayers.service.TeamService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/teams")
@AllArgsConstructor
public class TeamController {
	@Autowired
	private TeamService teamService;
	@Autowired
	private PlayerService playerService;
	
	@GetMapping()
	
	//пагинация списка команд
	
	public String showTeams(Model model) {
		return findPaginated(0, "name", "asc", model);
	}

	//добавление новой команды
	
	@GetMapping("/add")

	public String showAddTeamForm(Model model) {		
		model.addAttribute("team", new Team());
		return "add-team";
		}
	
	//сохранение новой команды
	
	@PostMapping("/add")
	 
	public String addTeam(@ModelAttribute("team") Team team)
	{
		teamService.saveTeam(team);
		return "redirect:/teams";
	}

	//демонстрация состава команды
	
	@GetMapping("/{id}")

	public String showTeamDetails(@PathVariable Long id, Model model) {
		String nameTeam = teamService.getTeamById(id).getName();
		List<Player> players = playerService.getPlayersByTeamId(id);
		model.addAttribute("nameteam", nameTeam);
		model.addAttribute("players", players);
		return "players";
		}
	
	//редактирование данных команды

	@GetMapping("/edit/{id}")

	public String showEditTeamForm(@PathVariable Long id, Model model) {
		Team team = teamService.getTeamById(id);
		model.addAttribute("team", team);
		return "edit-team";		
	}

	//обновление данных команды
	
	@PostMapping("/edit/{id}")

	public String editTeam(@PathVariable Long id, @ModelAttribute("team") Team updatedTeam) {
		teamService.updateTeam(id, updatedTeam);
		return "redirect:/teams";
	}
	
	//удаление команды

	@GetMapping("/delete/{id}")

	public String deleteTeam(@PathVariable Long id) {	
		teamService.deleteTeam(id);
		return "redirect:/teams";
		}
	
	//пагинация и сортировка списка команд
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model)
	{
		int pageSize = 5;
		Page<Team> page = teamService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Team> teams = page.getContent();
		int totalPages = page.getTotalPages();
		int begin = pageNo - pageNo % 10;
		int end = begin + 9 < totalPages? begin + 9 : totalPages;
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("begin", begin + 1);
		model.addAttribute("end", end);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc")? "desc" : "asc");
		
		model.addAttribute("teams", teams);
		
		return "teams";
	}
}
