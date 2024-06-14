package com.pro.springPlayers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pro.springPlayers.models.CurrentRatio;

public interface CurrentRatioRepository extends JpaRepository<CurrentRatio, Long> {
	List<CurrentRatio> findByUserLogin(String userLogin);
	List<CurrentRatio> findByRatesId(Long ratesId);
	

}
