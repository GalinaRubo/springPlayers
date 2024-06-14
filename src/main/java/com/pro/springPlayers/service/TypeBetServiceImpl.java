package com.pro.springPlayers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pro.springPlayers.models.TypeBet;
import com.pro.springPlayers.repository.TypeBetRepository;


@Service
public class TypeBetServiceImpl implements TypeBetService {

	@Autowired
	private TypeBetRepository  TBrepository;

	@Override
	public List<TypeBet> getAllTypesBets() {
		return TBrepository.findAll();
	}

	@Override
	public TypeBet getTypeBetById(Long id) {
		return TBrepository.findById(id).orElse(null);
	}

	@Override
	public void saveTypeBet(TypeBet typeBet) {
		TBrepository.save(typeBet);
		
	}

	@Override
	public void updateTypeBet(Long id, TypeBet typeBet) {
		TypeBet _typesBets = TBrepository.findById(id).orElse(null);
		if(_typesBets != null) {
			_typesBets.setName(typeBet.getName());
			TBrepository.save(_typesBets);
		}		
	}

	@Override
	public void deleteTypeBetById(Long id) {
		TBrepository.deleteById(id);
		
	}

	@Override
	public TypeBet getTypeBetByName(String name) {
		return TBrepository.findByName(name);
	}

}
