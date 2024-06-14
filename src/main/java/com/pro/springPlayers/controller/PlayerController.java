package com.pro.springPlayers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pro.springPlayers.models.Player;
import com.pro.springPlayers.models.PlayerInfo;
import com.pro.springPlayers.models.Team;
import com.pro.springPlayers.service.PlayerInfoService;
import com.pro.springPlayers.service.PlayerService;
import com.pro.springPlayers.service.TeamService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/players")
@AllArgsConstructor

public class PlayerController {
	@Autowired
	private PlayerService playerService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private PlayerInfoService infoService;
	
	@GetMapping()

	//пагинация списка игроков
	
	public String showPlayers(Model model) {
		return findPaginated(0, "name", "asc", model);
	}


	@GetMapping("/team_players/{id}")

	public String showPlayersDetails(@PathVariable Long id, Model model) {
		List<Player> players = playerService.getPlayersByTeamId(id);
		model.addAttribute("players", players);
		return "players";
	}

	//добавление игрока
	
	@GetMapping("/add")

	public String showAddPlayerForm(Model model) {
		List<Team> teams = teamService.getAllTeams();
		model.addAttribute("teams", teams);
		model.addAttribute("player", new Player());		
		return "add-player";
	}
	//сохранение игрока
	
	@PostMapping("/add")

	public String addPlayer(@ModelAttribute("player") Player player) {
		playerService.savePlayer(player);
		return "redirect:/players";
	}
	
	//редактирование данных по игроку
	
	@GetMapping("/edit/{id}")

	public String showEditPlayerForm(@PathVariable Long id, Model model) {
		List<Team> teams = teamService.getAllTeams();
		model.addAttribute("teams", teams);
		Player player = playerService.getPlayerById(id);
		model.addAttribute("player", player);
		return "edit-player";
	}
	
	//обновление данных по игроку
	
	@PostMapping("/edit/{id}")

	public String editPlayer(@PathVariable Long id, @ModelAttribute("player") Player updatedPlayer) {
		playerService.updatePlayer(id, updatedPlayer);
		return "redirect:/players";
	}
	
	//создание карточки игрока
	
	@GetMapping("/add-player-details/{id}")
	
	public String addDetailsPlayer( @PathVariable Long id, Model model) {
		Player player = playerService.getPlayerById(id);
		model.addAttribute("player", player);
		model.addAttribute("playerinfo", new PlayerInfo());
		return "add-player-info";
	}
	
	//сохранение кариточки игрока
	
	@PostMapping("/add-player-details/{id}")

	public String addDetails(@PathVariable Long id, @ModelAttribute("playerinfo") PlayerInfo playerInfo) {
		infoService.savePlayerInfo(playerInfo);
		return "redirect:/players/id/{id}";
	}
	
	//редактирование карточки игрока
	
	@GetMapping("/edit-player-details/{id}")
	public String editPlayerDetails(@PathVariable Long id, Model model){
		Player player = playerService.getPlayerById(id);		
		PlayerInfo playerInfo = infoService.getPlayerInfoById(id);
		model.addAttribute("playerinfo", playerInfo);
		model.addAttribute("player", player);
		return "edit-player-info";			
	}
	
	//обновление карточки игрока
	
	@PostMapping("/edit-player-details/{id}")

	public String editPlayerInfo(@PathVariable Long id, @ModelAttribute("playerinfo") PlayerInfo updatedPlayerInfo) {
		infoService.updatePlayerInfo(id, updatedPlayerInfo);
		return "redirect:/players/id/{id}";
	}
	
	//просмотр карточки игрока
	
	@GetMapping("/id/{id}")

	public String showPlayerDetails(@PathVariable Long id, Model model) {
		Player player = playerService.getPlayerById(id);		
		PlayerInfo playerInfo = infoService.getPlayerInfoById(id);
		if(playerInfo == null) return "info-null";
		model.addAttribute("playerinfo", playerInfo);
		model.addAttribute("player", player);
		return "player-info";
	}


	//удаление игрока

	@GetMapping("/delete/{id}")

	public String deletePlayer(@PathVariable Long id) {
		playerService.deletePlayerById(id);
		return "redirect:/players";
	}
	
	//пагинация и сортировка списка игроков
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model)
	{
		int pageSize = 5;
		Page<Player> page = playerService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Player> players = page.getContent();
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
		
		model.addAttribute("players", players);
		return "players";
	}

}
