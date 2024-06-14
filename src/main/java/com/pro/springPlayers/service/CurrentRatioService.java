package com.pro.springPlayers.service;

import java.util.List;

import com.pro.springPlayers.models.CurrentRatio;

public interface CurrentRatioService {
	List<CurrentRatio> getAllCurrentRatio();
	void saveCurrentRatio(CurrentRatio currentRatio);
	CurrentRatio getCurrentRatioById(Long id);
	void updateCurrentRatio(Long id, CurrentRatio currentRatio);
	void deleteCurrentRatioById(Long id);
	List<CurrentRatio> getAllCurrentRatioByUserLogin(String userLogin);
	List<CurrentRatio> getAllCurrentRatioByRatesId(Long id);
}
