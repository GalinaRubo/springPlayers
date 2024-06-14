package com.pro.springPlayers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pro.springPlayers.models.Ratio;
import com.pro.springPlayers.models.User;
import com.pro.springPlayers.models.UserBalance;
import com.pro.springPlayers.service.RatioService;
import com.pro.springPlayers.service.UserBalanceService;
import com.pro.springPlayers.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;


@Controller
@RequestMapping("/balances")
@AllArgsConstructor

public class BalanceController {
	@Autowired
	private UserBalanceService userBalanceService;
	@Autowired
	private UserService userService;
	@Autowired
	private RatioService ratioService;

	
	//начисление баллов для ставок пользователей
	
	@GetMapping("/add/{id}")
	public String AddBalanceForm(@PathVariable Long id, Model model, HttpSession session) {
		List<UserBalance> usersBalances = userBalanceService.getAllUsersBalances();
		List<User> users = userService.getAllUsers();
		if (usersBalances.isEmpty()) {

			for (User user : users) {
				UserBalance userBalance = new UserBalance(500, user);
				userBalanceService.saveUserBalance(userBalance);
			}
		}
		return "redirect:/tournaments";
	}

	//очистка таблицы с баллами для ставок пользователей
	
	@GetMapping("/clean_balances")
	public String DelBalanceForm(HttpSession session) {
		List<UserBalance> usersBalances = userBalanceService.getAllUsersBalances();
		for (UserBalance ub : usersBalances) {
			userBalanceService.deleteUserBalanceById(ub.getId());
		}
		session.removeAttribute("user_balance");

	//очистка таблицы с коэффициентами для ставок пользователей
		
		List<Ratio> ratios = ratioService.getAllRatios();
		for (Ratio rt : ratios)
		{
			ratioService.deleteRatioById(rt.getId());
		}
	//очистка таблицы с игровыми парами команд
		
	//	List<Rates> rates = ratesService.getAllRates();
	//	for (Rates rat : rates)
	//	{
	//		ratesService.deleteRatesById(rat.getId());
	//	}
		return "redirect:/tournaments";
	}
}
