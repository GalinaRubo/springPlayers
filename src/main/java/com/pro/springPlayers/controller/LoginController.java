package com.pro.springPlayers.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.pro.springPlayers.models.Auth;
import com.pro.springPlayers.models.Tournament;
import com.pro.springPlayers.models.User;
import com.pro.springPlayers.models.UserBalance;
import com.pro.springPlayers.service.TournamentService;
import com.pro.springPlayers.service.UserBalanceService;
import com.pro.springPlayers.service.UserService;

import dto.UserDto;

@Controller

public class LoginController {

	private String name = "";
	private String role = "";
	private Long user_id = null;

	@Autowired
	private UserService userService;
	@Autowired
	private UserBalanceService userBalanceService;
	@Autowired
	private TournamentService tournamentService;

	@GetMapping("/login")
	public String loginForm(User user) {
		return "login";
	}

	//авторизация
	
	@PostMapping("/login")
	public String loginProcess(@Valid @ModelAttribute("auth") Auth auth, HttpSession session) {
		User dbUser = userService.findUserByEmail(auth.getUsername());
		if (dbUser == null || !BCrypt.checkpw(auth.getPassword(), dbUser.getPassword())) {
			return "redirect:/login?error";
		}
		user_id = dbUser.getId();
		name = dbUser.getEmail();
		role = dbUser.getRole();
		
	//восстановление баланса баллов авторизованного пользователя
		
		List<UserBalance> usersBalances = userBalanceService.getAllUsersBalances();
		if (!usersBalances.isEmpty()) {
			session.setAttribute("user_balance", userBalanceService.getUserBalanceByUserId(user_id).getBalance());
		}
	//установка актуального чемпионата
		
		List<Tournament> tournaments = tournamentService.getAllTournaments();
		if (!tournaments.isEmpty()) {
			tournaments.sort(Comparator.comparing(Tournament::getDate));
			session.setAttribute("tournament_id", (tournaments.get(tournaments.size() - 1).getId()));
		}
		session.setAttribute("user_id", user_id);
		session.setAttribute("name", name);
		session.setAttribute("role", role);
		session.setAttribute("mess", "");

	//переход после авторизации к основному меню
		
		return "user";
	}

	//регистрация
	
	@GetMapping("/registration")
	public String registrationForm(Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
		User existingUser = userService.findUserByEmail(userDto.getEmail());

		if (existingUser != null)
			result.rejectValue("email", null, "User already registered !!!");

		if (result.hasErrors()) {
			model.addAttribute("user", userDto);
			return "/registration";
		}
		userService.saveUser(userDto);
		if (!userBalanceService.getAllUsersBalances().isEmpty()) {
			System.out.printf("user_id = " + userDto.getName() + "\n");
			User user = userService.findUserByEmail(userDto.getEmail());
			UserBalance userBalance = new UserBalance(500, user);
			userBalanceService.saveUserBalance(userBalance);
		}

		return "redirect:/registration?success";
	}

	//выход 
	
	@GetMapping("/logout")
	public String exit(HttpSession session) {

		if (session.getAttribute("user_id") != null && session.getAttribute("user_balance") != null) {
			User user = userService.findUserById((Long) session.getAttribute("user_id"));
			UserBalance userBalance = new UserBalance((int) session.getAttribute("user_balance"), user);
			userBalance.setId(userBalanceService.getUserBalanceByUserId(user.getId()).getId());
			userBalanceService.updateUserBalance(userBalanceService.getUserBalanceByUserId(user.getId()).getId(),
					userBalance);

			session.removeAttribute("name");
			session.removeAttribute("role");
			session.removeAttribute("user_id");
			session.removeAttribute("mess");
			session.removeAttribute("user_balance");
		}

		return "index";
	}
}