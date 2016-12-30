package com.cairone.appexample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cairone.appexample.entities.PaisEntity;
import com.cairone.appexample.repositories.PaisRepository;

@Service
public class PaisService {

	@Autowired private PaisRepository paisRepository = null;
	
	public PaisEntity buscarPorID(Integer id) {
		
		return paisRepository.findOne(id);
	}
}
