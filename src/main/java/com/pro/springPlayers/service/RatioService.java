package com.pro.springPlayers.service;

import java.util.List;

import com.pro.springPlayers.models.Ratio;

public interface RatioService {
	
	List<Ratio> getAllRatios();
	List<Ratio> findRatiosByRateId(Long rateId);
	void saveRatio(Ratio ratio);
	Ratio getRatioById(Long id);
	void updateRatio(Long id, Ratio ratio);
	void deleteRatioById(Long id);
}
