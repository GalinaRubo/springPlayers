package com.pro.springPlayers.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pro.springPlayers.models.CurrentRatio;
import com.pro.springPlayers.models.Foo;
import com.pro.springPlayers.models.Statistics;
import com.pro.springPlayers.models.Team;
import com.pro.springPlayers.models.Tournament;
import com.pro.springPlayers.models.TournamentInfo;
import com.pro.springPlayers.service.CurrentRatioService;
import com.pro.springPlayers.service.StatisticsService;
import com.pro.springPlayers.service.TeamService;
import com.pro.springPlayers.service.TournamentInfoService;
import com.pro.springPlayers.service.TournamentService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/tournaments")
@AllArgsConstructor
public class TournamentController {

	@Autowired
	private TournamentService tournamentService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private TournamentInfoService tournamentInfoService;
	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private CurrentRatioService currentRatioService;
	//пагинация списка турниров
	
	@GetMapping()

	public String showTournaments(Model model) {
		return findPaginated(0, "name", "asc", model);
	}
	
	//добавление предстоящего турнира

	@GetMapping("/add")

	public String showAddTournamentForm(Model model) {
		model.addAttribute("tournament", new Tournament());
		return "add-tournament";
	}

	//сохранение данных предстоящего турнира
	@PostMapping("/add")

	public String addTournament(@ModelAttribute("tournament") Tournament tournament, HttpSession session) {
		tournamentService.saveTournament(tournament);
		List<CurrentRatio> currentRatios = currentRatioService.getAllCurrentRatio();
		for (CurrentRatio cr : currentRatios)
		{
			currentRatioService.deleteCurrentRatioById(cr.getId());			
		}
		session.setAttribute("tournament_id", tournament.getId());
		List<Team> teams = teamService.getAllTeams();
		for (Team team : teams) {
			TournamentInfo tournamentInfo = new TournamentInfo();
			tournamentInfo.setTournament(tournament);
			tournamentInfo.setTeam(team);
			tournamentInfo.setSubgroup("1");
			tournamentInfoService.saveTournamentInfo(tournamentInfo);
		}
		return "redirect:/tournaments";
	}
	
	//редактирование данных по выбранному турниру

	@GetMapping("/edit/{id}")

	public String showEditTournamentForm(@PathVariable Long id, Model model) {
		Tournament tournament = tournamentService.getTournamentById(id);
		model.addAttribute("tournament", tournament);
		return "edit-tournament";
	}
	
	//обновление данных по выбранному турниру

	@PostMapping("/edit/{id}")

	public String editTournament(@PathVariable Long id, @ModelAttribute("tournament") Tournament updatedTournament) {
		tournamentService.updateTournament(id, updatedTournament);
		return "redirect:/tournaments";
	}
	
	//формирование таблицы групп команд предстоящего турнира

	@GetMapping("/info/{id}")

	public String addTournamentDetails(@PathVariable Long id, Model model) {
		String[] groups = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		Tournament tournament = tournamentService.getTournamentById(id);
		List<TournamentInfo> tournamentsInfo = tournamentInfoService.getAllTournamentInfoByName(tournament.getName());
		Foo foo = new Foo();
		model.addAttribute("groups", groups);
		model.addAttribute("tournament", tournament);
		model.addAttribute("tournamentsinfo", tournamentsInfo);
		model.addAttribute("foo", foo);
		return "current-tournament";
	}
	
	//деление команд по подгруппам

	@PostMapping("/tournamentsinfo/edit/subgroups/{id}")
	public String getUpdatedTournamentInfo(@PathVariable Long id, @ModelAttribute(value = "foo") Foo foo) {
		List<Long> checkedItems = foo.getCheckedItems();
		for (Long subid : checkedItems) {
			TournamentInfo tournamentinfo = tournamentInfoService.getTournamentInfoById(subid);
			tournamentinfo.setSubgroup("2");
			tournamentInfoService.updateTournamentInfo(subid, tournamentinfo);
		}
		return "redirect:/tournaments";
	}
	
	//демонстрация таблицы команд, поделенных на подгруппы предстоящего турнира

	@GetMapping("/id/{id}")

	public String showTournamentDetails(@PathVariable Long id, Model model) {
		Tournament tournament = tournamentService.getTournamentById(id);
		model.addAttribute("tournament", tournament);
		return "tournament-info";
	}
	
	//удаление выбранного турнира

	@GetMapping("/delete/{id}")

	public String deleteTournament(@PathVariable Long id) {

		List<TournamentInfo> tournamentInfo = tournamentInfoService
				.getAllTournamentInfoByName(tournamentService.getTournamentById(id).getName());
		for (TournamentInfo tInfo : tournamentInfo)
			tournamentInfoService.deleteTournamentInfoById(tInfo.getId());
		List<Statistics> statistics = statisticsService.getStatisticsByTournamentId(id);
		for (Statistics stat : statistics)
			statisticsService.deleteStatisticsById(stat.getId());
		tournamentService.deleteTournamentById(id);
		return "redirect:/tournaments";
	}
	
	//пагинация и сортировка списка турниров

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;
		Page<Tournament> page = tournamentService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Tournament> tournaments = page.getContent();
		int totalPages = page.getTotalPages();
		int begin = pageNo - pageNo % 10;
		int end = begin + 9 < totalPages ? begin + 9 : totalPages;
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("begin", begin + 1);
		model.addAttribute("end", end);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("tournaments", tournaments);
		return "tournaments";
	}

}
