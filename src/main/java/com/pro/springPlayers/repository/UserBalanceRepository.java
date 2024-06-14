package com.pro.springPlayers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pro.springPlayers.models.UserBalance;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance, Long>{
	UserBalance getUserBalanceByUserId(Long userId);
	UserBalance getUserBalanceByUserName(String name);
}
