package com.cairone.appexample.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cairone.appexample.entities.PaisEntity;
import com.cairone.appexample.repositories.PaisRepository;

@Service
public class PaisService {

	@Autowired private PaisRepository paisRepository = null;
	
	@Transactional(readOnly = true)
	public PaisEntity buscarPorID(Integer id) {		
		return paisRepository.findOne(id);
	}
	
	@Transactional(readOnly = true)
	public List<PaisEntity> buscarPaises() {
		return paisRepository.findAll();
	}
}
