package com.pro.springPlayers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.springPlayers.models.Ratio;
import com.pro.springPlayers.repository.RatioRepository;

@Service
public class RatioServiceImpl implements RatioService {
	
	@Autowired
	private RatioRepository ratioRepository;

	@Override
	public List<Ratio> getAllRatios() {
		return ratioRepository.findAll();
	}

	@Override
	public List<Ratio> findRatiosByRateId(Long rateId) {
		return ratioRepository.findRatiosByRateId(rateId);
	}

	@Override
	public void saveRatio(Ratio ratio) {
		ratioRepository.save(ratio);
		
	}

	@Override
	public Ratio getRatioById(Long id) {
		return ratioRepository.findById(id).orElse(null);
	}

	@Override
	public void updateRatio(Long id, Ratio ratio) {
		Ratio _ratio = ratioRepository.findById(id).orElse(null);
		if(_ratio != null) {

			_ratio.setRateId(ratio.getRateId());				 
			_ratio.setTypeBet(ratio.getTypeBet());
			_ratio.setSizeRatio(ratio.getSizeRatio());	
			ratioRepository.save(_ratio);
		}
		
	}

	@Override
	public void deleteRatioById(Long id) {
		ratioRepository.deleteById(id);
		
	}

}
