package com.pro.springPlayers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.springPlayers.models.CurrentRatio;
import com.pro.springPlayers.repository.CurrentRatioRepository;

@Service
public class CurrentRatioServiceImpl implements CurrentRatioService {
	
	@Autowired
	private CurrentRatioRepository repository;
	
	@Override
	public List<CurrentRatio> getAllCurrentRatio() {
		return repository.findAll();
	}

	@Override
	public void saveCurrentRatio(CurrentRatio currentRatio) {
		repository.save(currentRatio);
		
	}

	@Override
	public CurrentRatio getCurrentRatioById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void updateCurrentRatio(Long id, CurrentRatio currentRatio) {
		CurrentRatio _currentRatio = repository.findById(id).orElse(null);
		if(_currentRatio != null) {
			System.out.printf("все ок!!! \n");
			_currentRatio.setUserLogin(currentRatio.getUserLogin());
			_currentRatio.setRates(currentRatio.getRates());
			_currentRatio.setTypeBet(currentRatio.getTypeBet());
			_currentRatio.setRatioBet(currentRatio.getRatioBet());
			_currentRatio.setSizeBet(currentRatio.getSizeBet());
			_currentRatio.setYesNo(currentRatio.getYesNo());
			System.out.printf("rate_id=" + _currentRatio.getRates().getId() + "\n");
			System.out.printf("bet_id=" + _currentRatio.getTypeBet().getId() + "\n");
			System.out.printf("user=" + _currentRatio.getUserLogin() + "   \n");
			System.out.printf("bet_ratio=" + _currentRatio.getRatioBet() + "\n");
			System.out.printf("size_bet=" + _currentRatio.getSizeBet() + "\n");
			System.out.printf("yesno=" + _currentRatio.getYesNo() + "   \n\n");
			repository.save(_currentRatio);
		}		
	}

	@Override
	public void deleteCurrentRatioById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public List<CurrentRatio> getAllCurrentRatioByUserLogin(String userLogin) {
		return repository.findByUserLogin(userLogin);
	}

	@Override
	public List<CurrentRatio> getAllCurrentRatioByRatesId(Long ratesId) {
		return repository.findByRatesId(ratesId);
	}

}
