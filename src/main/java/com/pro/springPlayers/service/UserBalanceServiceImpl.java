package com.pro.springPlayers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pro.springPlayers.models.UserBalance;
import com.pro.springPlayers.repository.UserBalanceRepository;

@Service

public class UserBalanceServiceImpl implements UserBalanceService {

	@Autowired
	private UserBalanceRepository UBrepository;

	@Override
	public List<UserBalance> getAllUsersBalances() {
		return UBrepository.findAll();
	}

	@Override
	public UserBalance getUserBalanceByUserId(Long userId) {
		List<UserBalance> usersBalances = UBrepository.findAll();
		for (UserBalance ub : usersBalances) {
			if (ub.getUser().getId() == userId)
				return ub;
		}
		return null;
	}

	@Override
	public void saveUserBalance(UserBalance userBalance) {
		UBrepository.save(userBalance);

	}

	@Override
	public UserBalance getUserBalanceById(Long id) {
		return UBrepository.findById(id).orElse(null);
	}

	@Override
	public void updateUserBalance(Long id, UserBalance userBalance) {
		UserBalance _userBalance = UBrepository.findById(id).orElse(null);
		if (_userBalance != null) {
			_userBalance.setBalance(userBalance.getBalance());
			_userBalance.setUser(userBalance.getUser());
			UBrepository.save(_userBalance);
		}
	}

	@Override
	public void deleteUserBalanceById(Long id) {
		UBrepository.deleteById(id);
	}

	@Override
	public UserBalance getUserBalanceByUserName(String name) {
		List<UserBalance> usersBalances = UBrepository.findAll();
		for (UserBalance ub : usersBalances) {
			if (ub.getUser().getName() == name)
				return ub;
		}
		return null;
	}
}
