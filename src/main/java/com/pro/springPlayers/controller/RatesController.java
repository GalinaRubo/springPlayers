package com.pro.springPlayers.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pro.springPlayers.models.CurrentRatio;
import com.pro.springPlayers.models.Rates;
import com.pro.springPlayers.models.Ratio;
import com.pro.springPlayers.models.ResultBet;
import com.pro.springPlayers.models.Statistics;
import com.pro.springPlayers.models.Tournament;
import com.pro.springPlayers.models.TournamentInfo;
import com.pro.springPlayers.models.TrueBet;
import com.pro.springPlayers.models.TypeBet;
import com.pro.springPlayers.models.User;
import com.pro.springPlayers.models.UserBalance;
import com.pro.springPlayers.service.CurrentRatioService;
import com.pro.springPlayers.service.RatesService;
import com.pro.springPlayers.service.RatioService;
import com.pro.springPlayers.service.ResultBetService;
import com.pro.springPlayers.service.StatisticsService;
import com.pro.springPlayers.service.TournamentInfoService;
import com.pro.springPlayers.service.TournamentService;
import com.pro.springPlayers.service.TypeBetService;
import com.pro.springPlayers.service.UserBalanceService;
import com.pro.springPlayers.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/rates")
@AllArgsConstructor
public class RatesController {

	@Autowired
	private TournamentService tournamentService;
	@Autowired
	private UserBalanceService userBalanceService;
	@Autowired
	private UserService userService;
	@Autowired
	private TournamentInfoService tournamentInfoService;
	@Autowired
	private RatesService ratesService;
	@Autowired
	private CurrentRatioService currentRatioService;
	@Autowired
	private TypeBetService typeBetService;
	@Autowired
	private RatioService ratioService;
	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private ResultBetService resultBetService;

	//демонстрация сформированной таблицы команд по группам выбранного чемпионата
	
	@GetMapping("/info/{id}")

	public String addRDetails(@PathVariable Long id, Model model, HttpSession session) {
		if (id == 0)
			return "rates-no-active";
		String[] groups = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		Tournament tournament = tournamentService.getTournamentById(id);
		List<TournamentInfo> tournamentsInfo = tournamentInfoService.getAllTournamentInfoByName(tournament.getName());
		model.addAttribute("groups", groups);
		model.addAttribute("tournament", tournament);
		model.addAttribute("tournamentsinfo", tournamentsInfo);
		model.addAttribute("mess", session.getAttribute("mess"));
		return "team-rates";
	}
	
	//формирование таблицы команд  по группам выбранного чемпионата

	@GetMapping("/add/{id}")

	public String RatesDetails(@PathVariable Long id, Model model, HttpSession session) {

		Tournament tournament = tournamentService.getTournamentById(id);
		List<Rates> _rates = ratesService.getRatesByTournamentId(id);
		List<TypeBet> typesBets = typeBetService.getAllTypesBets();
		if (_rates.isEmpty()) {
			List<TournamentInfo> tournamentsInfo = tournamentInfoService
					.getAllTournamentInfoByName(tournament.getName());
			ArrayList<TournamentInfo> tournamentsInfoCopyOne = new ArrayList<TournamentInfo>();
			tournamentsInfoCopyOne.addAll(0, tournamentsInfo);
			ArrayList<TournamentInfo> tournamentsInfoCopyTwo = new ArrayList<TournamentInfo>();
			tournamentsInfoCopyTwo.addAll(0, tournamentsInfo);
			System.out.printf("size=" + tournamentsInfoCopyOne.size() + "   ");
			for (TournamentInfo one : tournamentsInfoCopyOne) {
				tournamentsInfoCopyTwo.remove(one);
				for (TournamentInfo two : tournamentsInfoCopyTwo) {
					if (one.getTeam().getLiga().equals(two.getTeam().getLiga())
							&& one.getSubgroup().equals(two.getSubgroup())) {
						Rates item = new Rates();
						item.setTournament(tournament);
						item.setTeam(one.getTeam());
						item.setRival(two.getTeam());
						ratesService.saveRates(item);
					}
				}
				tournamentsInfoCopyTwo.remove(one);
			}

			List<Rates> AllRatesItems = ratesService.getAllRates();
			for (Rates ri : AllRatesItems) {
				for (TypeBet tb : typesBets) {
					ratioService.saveRatio(new Ratio(ri.getId(), tb, 1.0));
				}
			}
		}
		List<Rates> AllRatesItems = ratesService.getAllRates();
		List<Ratio> AllRatioItems = ratioService.getAllRatios();
		CurrentRatio currantRatio = new CurrentRatio();

		for (TypeBet tb : typesBets) {
			System.out.printf("bet-name = " + tb.getName() + "\n");
		}

		model.addAttribute("ListRates", AllRatesItems);
		model.addAttribute("ListRatios", AllRatioItems);
		model.addAttribute("typesbets", typesBets);
		model.addAttribute("tournament", tournament);
		model.addAttribute("currat", currantRatio);
		return "table-rates";
	}

	//выбор таблицы игр  конкретной команды
	@GetMapping("/{id}")

	public String BettingTeams(@PathVariable Long id, Model model, HttpSession session) {
		TournamentInfo tournamentInfo = tournamentInfoService.getTournamentInfoById(id);
		Tournament tournament = tournamentService.getTournamentById(tournamentInfo.getTournament().getId());
		List<Rates> currentRates = ratesService.getRatesByTournamentId(tournament.getId());
		List<Rates> CurrentRatesItems = new ArrayList<Rates>();

		for (Rates item : currentRates) {
			if (item.getTeam().getName() == tournamentInfo.getTeam().getName()
					|| item.getRival().getName() == tournamentInfo.getTeam().getName())
				CurrentRatesItems.add(item);
		}

		CurrentRatio currantRatio = new CurrentRatio();
		List<TypeBet> typesBets = typeBetService.getAllTypesBets();
		List<Ratio> AllRatioItems = ratioService.getAllRatios();

		model.addAttribute("ListRates", CurrentRatesItems);
		model.addAttribute("ListRatios", AllRatioItems);
		model.addAttribute("typesbets", typesBets);
		model.addAttribute("tournament", tournament);
		model.addAttribute("tournamentInfoId", id);
		model.addAttribute("currat", currantRatio);
		return "table-rates";
	}

	//анализ сделанной ставки и ее запись
	
	@PostMapping("/currentRatio/{id}")

	public String addUserRatio(@PathVariable Long id, HttpSession session, @ModelAttribute CurrentRatio currat,
			Model model) {
		session.setAttribute("mess", "");
		if (currat.getSizeBet() != null) {

			String[] parts = currat.getUserLogin().split("/");
			TypeBet typeBet = typeBetService.getTypeBetById(Long.parseLong(parts[0]));
			currat.setTypeBet(typeBet);
			currat.setRatioBet(Double.parseDouble(parts[1]));
			System.out.printf("Typebet=" + currat.getTypeBet().getName() + "\n");
			System.out.printf("Sizebet=" + currat.getSizeBet() + "\n");

			int user_balance = (int) session.getAttribute("user_balance");
			if (user_balance - currat.getSizeBet() >= 0) {
				user_balance -= currat.getSizeBet();
				session.setAttribute("user_balance", user_balance);
				currat.setUserLogin(session.getAttribute("name").toString());
				UserBalance userBalance = userBalanceService
						.getUserBalanceByUserId((userService.findUserByEmail(currat.getUserLogin()).getId()));
				userBalance.setBalance(user_balance);
				userBalanceService.updateUserBalance(userBalance.getId(), userBalance);
				currentRatioService.saveCurrentRatio(currat);
				dataRetio(currat.getRates().getId(), currat.getTypeBet().getId(), currat.getSizeBet());

				session.setAttribute("mess", "bet done successfully!");
			} else
				session.setAttribute("mess", "done bet more then balance!");
		}

		return addRDetails(id, model, session);
	}
	
	//ставки пользователя

	@GetMapping("/userbets")

	public String showUserBets(Model model, HttpSession session) {
		List<CurrentRatio> userRatios = new ArrayList<CurrentRatio>();
		Tournament tournament = tournamentService.getTournamentById((long) session.getAttribute("tournament_id"));
		String login = session.getAttribute("name").toString();
		userRatios = currentRatioService.getAllCurrentRatioByUserLogin(login);
		model.addAttribute("userratios", userRatios);
		model.addAttribute("login", login);
		model.addAttribute("tournament", tournament);
		return "user-table-rates";
	}
	
	//сопоставление результатов чемпионата и ставок

	@GetMapping("/winbet/{id}")

	public String colculateBets(@PathVariable Long id, Model model) {

		Tournament tournament = tournamentService.getTournamentById(id);
		List<ResultBet> _result = resultBetService.getAllResultBetByTournamentId(id);

		if (_result.isEmpty()) {
			List<Statistics> statistics = statisticsService.getAllStatistics();
			List<TrueBet> winbets = new ArrayList<TrueBet>();
			List<Rates> rates = ratesService.getAllRates();

			Iterator<Statistics> stIterator = statistics.iterator();// создаем итератор
			while (stIterator.hasNext()) {// до тех пор, пока в списке есть элементы

				Statistics nextStatistics = stIterator.next();// получаем следующий элемент
				if (nextStatistics.getScoreTeam().equals(nextStatistics.getScoreRival())) {
					stIterator.remove();// удаляем статистику с нужным именем
				}
				System.out.printf("statistics_size =" + statistics.size() + "\n\n");
			}
			for (Rates rt : rates) {
				int count_game = 0;
				int index_winner = 0;
				boolean flag_end = true;

				ListIterator<Statistics> listIter = statistics.listIterator(); // создаем итератор
				while (listIter.hasNext()) {// до тех пор, пока в списке есть элементы
					Statistics stat = listIter.next();
					if (stat.getRate().getId() == rt.getId()) {
						flag_end = false;
						if (listIter.nextIndex() == 1) {
							TrueBet tb = new TrueBet();
							tb.setRateId(rt.getId());
							if (stat.getTeam() == stat.getRate().getTeam())
								tb.setBetId(typeBetService.getTypeBetByName("team_first_goal").getId());
							else
								tb.setBetId(typeBetService.getTypeBetByName("rival_first_goal").getId());
							winbets.add(tb);
						}
						count_game++;// счетчик игр
						index_winner += stat.getScoreTeam() > stat.getScoreRival() ? 1 : -1;
					} else
						flag_end = true;
				}
				if (flag_end == false) {
					TrueBet tbg = new TrueBet();
					tbg.setRateId(rt.getId());
					if (count_game > 3)
						tbg.setBetId(typeBetService.getTypeBetByName("more_then_three_games").getId());
					else
						tbg.setBetId(typeBetService.getTypeBetByName("three_games").getId());
					winbets.add(tbg);

					TrueBet tbw = new TrueBet();
					tbw.setRateId(rt.getId());
					if (index_winner > 0)
						tbw.setBetId(typeBetService.getTypeBetByName("winner_team").getId());
					else
						tbw.setBetId(typeBetService.getTypeBetByName("winner_rival").getId());
					winbets.add(tbw);					
				}
			}
			
			//отметка выигрышных ставок у пользователей
			
			List<CurrentRatio> currentratios = currentRatioService.getAllCurrentRatio();
			for (TrueBet trb : winbets) {
				for (CurrentRatio cr : currentratios) {

					if (cr.getRates().getId() == trb.getRateId() && cr.getTypeBet().getId() == trb.getBetId())
						cr.setYesNo(true);
					else
						cr.setYesNo(false);
					currentRatioService.updateCurrentRatio(cr.getId(), cr);
				}
			}
			//ссуммирование выигрышных ставок пользователей
			
			List<User> users = userService.getAllUsers();
			for (User user : users) {
				List<CurrentRatio> currentRatio = currentRatioService.getAllCurrentRatioByUserLogin(user.getEmail());
				float user_size_bet = 0;
				for (CurrentRatio currat : currentRatio) {
					if (currat.getYesNo() == true) {
						user_size_bet += currat.getSizeBet() * currat.getRatioBet();
					}
				}
				if (user_size_bet != 0) {
					ResultBet rb = new ResultBet(tournament, user.getEmail(), user_size_bet);
					resultBetService.saveResultBet(rb);
				}
			}
		}
		//сортировка списка пользователей с сыгранными ставками по убыванию
		
		List<ResultBet> result = resultBetService.getAllResultBetByTournamentId(id);
		Comparator<ResultBet> compareByBets = Comparator.comparing(ResultBet::getResultBet);
		ArrayList<ResultBet> sortedBets = result.stream().sorted(compareByBets.reversed())
				.collect(Collectors.toCollection(ArrayList::new));

		List<ResultBet> res = new ArrayList<ResultBet>();
		Iterator<ResultBet> iter = sortedBets.iterator();
		int count = 0;
		while (iter.hasNext()) {
			if (++count < 10)
				res.add(iter.next());
		}
		model.addAttribute("sortedBets", sortedBets);
		model.addAttribute("tournament", tournament);

		return "final";
	}

	//демонстрация результатов ставок
	@GetMapping("/resultbets/{id}")

	public String showResultBets(@PathVariable Long id, Model model) {
		Tournament tournament = tournamentService.getTournamentById(id);
		List<ResultBet> result = resultBetService.getAllResultBetByTournamentId(id);
		Comparator<ResultBet> compareByBets = Comparator.comparing(ResultBet::getResultBet);
		ArrayList<ResultBet> sortedBets = result.stream().sorted(compareByBets.reversed())
				.collect(Collectors.toCollection(ArrayList::new));

		List<ResultBet> res = new ArrayList<ResultBet>();
		Iterator<ResultBet> iter = sortedBets.iterator();
		int count = 0;
		while (iter.hasNext()) {
			if (++count < 10)
				res.add(iter.next());
		}
		model.addAttribute("sortedBets", sortedBets);
		model.addAttribute("tournament", tournament);

		return "final";
	}

	//расчет и изменение коэффециентов после очередной ставки
	
	public void dataRetio(Long ratesId, Long betId, Integer sizeBet) {
		List<CurrentRatio> currentRatios = currentRatioService.getAllCurrentRatioByRatesId(ratesId);
		double sum_bets = 0;
		double sum_bets_2 = 0;
		double ratio_new = 0;
		Long betId_2 = betId % 2 == 0 ? betId - 1 : betId + 1;
		for (CurrentRatio cr : currentRatios) {

			if (cr.getTypeBet().getId() == betId)
				sum_bets += cr.getSizeBet();
			if (cr.getTypeBet().getId() == betId_2)
				sum_bets_2 += cr.getSizeBet();
		}
		List<Ratio> ratios = ratioService.findRatiosByRateId(ratesId);
		for (Ratio rat : ratios) {

			if (rat.getTypeBet().getId() == betId) {
				ratio_new = Math.round(((sum_bets + sum_bets_2) / sum_bets) * 100.0) / 100.0;
				rat.setSizeRatio(ratio_new);
				ratioService.updateRatio(rat.getId(), rat);
			}

			if (rat.getTypeBet().getId() == betId_2 && sum_bets_2 != 0) {
				ratio_new = Math.round(((sum_bets + sum_bets_2) / sum_bets_2) * 100.0) / 100.0;
				rat.setSizeRatio(ratio_new);
				ratioService.updateRatio(rat.getId(), rat);
			}

		}
	}
}
