package com.pro.springPlayers.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pro.springPlayers.models.Rates;
import com.pro.springPlayers.models.Stat;
import com.pro.springPlayers.models.Statistics;
import com.pro.springPlayers.models.Tournament;
import com.pro.springPlayers.models.TournamentInfo;
import com.pro.springPlayers.service.RatesService;
import com.pro.springPlayers.service.StatisticsService;
import com.pro.springPlayers.service.TournamentInfoService;
import com.pro.springPlayers.service.TournamentService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/statistics")
@AllArgsConstructor

public class StatisticsController {

	@Autowired
	private StatisticsService statService;
	@Autowired
	private TournamentService tournamentService;
	@Autowired
	private TournamentInfoService tournamentInfoService;	
	@Autowired
	private RatesService ratesService;

	@GetMapping("/{id}")
	//демонстрация статистических данных выбранного турнира
	
	public String showStatisticssDetails(@PathVariable Long id, Model model) {
		TournamentInfo tournamentInfo = tournamentInfoService.getTournamentInfoById(id);
		List<Statistics> statistics = statService.getStatisticsByTournamentId(tournamentInfo.getTournament().getId());
		if (statistics.isEmpty())
			return "statistics-null";
		else {
			List<Statistics> statisticsCurrentTeam = new ArrayList<Statistics>();
			System.out.printf("statisticsSize=" + statistics.size() + "   \n");
			for (Statistics stat : statistics) {
				if (stat.getRate().getTeam().getName() == tournamentInfo.getTeam().getName()
						|| stat.getRate().getRival().getName() == tournamentInfo.getTeam().getName())
					statisticsCurrentTeam.add(stat);
			}
			model.addAttribute("tournament", tournamentInfo.getTournament());			
			model.addAttribute("statistics", statisticsCurrentTeam);
			return "statistics";
		}
	}
	
	//формирование статистической таблицы грядущего турнира
	
	@GetMapping("/add/{id}")

	public String addStatisticsDetails(@PathVariable Long id, Model model) {

		Tournament tournament = tournamentService.getTournamentById(id);
		List<Statistics> stat = statService.getStatisticsByTournamentId(id);
		if (!stat.isEmpty()) {
			model.addAttribute("tournament", tournament);
			model.addAttribute("statistics", stat);
			return "statistics";
		} else {
			
			List<Rates> rates = ratesService.getAllRates();
			{
				for(Rates rt: rates) {
						for (int i = 1; i < 6; i++) {
							Statistics item = new Statistics();
							item.setTournament(tournament);
							item.setTeam_group(rt.getTeam().getLiga());
							item.setRate(rt);							
							item.setScoreTeam(0);
							item.setScoreRival(0);
							item.setTeam(null);
							item.setGame_part(i);

							statService.saveStatistics(item);
						}
					}
				}
			}

		List<Statistics> statistics = statService.getStatisticsByTournamentId(id);
		model.addAttribute("tournament", tournament);
		model.addAttribute("statistics", statistics);
		return "statistics";
	}
	
	//внесение данных игр (редактирование статистической таблицы актуального турнира)

	@GetMapping("/edit/stat/{id}")

	public String updatedStatisticsDetails(@PathVariable Long id, Model model) {
		TournamentInfo tournamentInfo = tournamentInfoService.getTournamentInfoById(id);
		List<Statistics> statistics = statService.getStatisticsByTournamentId(tournamentInfo.getTournament().getId());
		if (statistics.isEmpty())
			return "statistics-null";
		else {
			List<Statistics> statisticsCurrentTeam = new ArrayList<Statistics>();
			System.out.printf("statisticsSize=" + statistics.size() + "   \n");
			for (Statistics stat : statistics) {
				if (stat.getRate().getTeam().getName() == tournamentInfo.getTeam().getName()
						|| stat.getRate().getRival().getName() == tournamentInfo.getTeam().getName())
					statisticsCurrentTeam.add(stat);
			}
			List<Statistics> statItems = new ArrayList<>();
			statisticsCurrentTeam.iterator().forEachRemaining(statItems::add);
			model.addAttribute("tournament", tournamentInfo.getTournament());
			model.addAttribute("stat", new Stat(statItems));
			return ("edit-statistics");
		}
	}
	
	//обновление данных результатов игры конкретной пары команд
	@PostMapping("/edit/team/{id}")
	public String getUpdatedStatidticsInfo(@PathVariable Long id, @ModelAttribute(value = "stat") Stat stat) {
		List<Statistics> statItems = stat.getStatItems();
		for (Statistics st : statItems) {
			statService.updateStatistics(st.getId(), st);
		}
		return "redirect:/tournaments";
	}
}
