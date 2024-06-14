package com.pro.springPlayers.service;

import java.util.List;

import com.pro.springPlayers.models.UserBalance;

public interface UserBalanceService {

	List<UserBalance> getAllUsersBalances();
	UserBalance getUserBalanceByUserId(Long userId);
	void saveUserBalance(UserBalance userBalance);
	UserBalance getUserBalanceById(Long id);
	void updateUserBalance(Long id, UserBalance userBalance);
	void deleteUserBalanceById(Long id);
	UserBalance getUserBalanceByUserName(String name);
}
