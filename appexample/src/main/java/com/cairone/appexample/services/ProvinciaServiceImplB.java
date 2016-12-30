package com.cairone.appexample.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cairone.appexample.entities.PaisEntity;
import com.cairone.appexample.entities.ProvinciaEntity;
import com.cairone.appexample.entities.QProvinciaEntity;
import com.cairone.appexample.repositories.ProvinciaRepository;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
public class ProvinciaServiceImplB implements ProvinciaService {

	@PersistenceContext 
	private EntityManager em = null;
	
	@Autowired private ProvinciaRepository provinciaRepository = null;
	
	@Override
	public ProvinciaEntity nueva(PaisEntity paisEntity, String nombre) {
		
		JPAQuery query = new JPAQuery(em);
		QProvinciaEntity qProvincia = QProvinciaEntity.provinciaEntity;
		
		Integer provinciaID = query.from(qProvincia).uniqueResult(qProvincia.id.max());
		
		provinciaID = provinciaID == null ? 1 : provinciaID + 1;

		ProvinciaEntity provinciaEntity = new ProvinciaEntity();
		
		provinciaEntity.setId(provinciaID);
		provinciaEntity.setNombre(nombre);
		provinciaEntity.setPais(paisEntity);
		
		provinciaRepository.save(provinciaEntity);
		
		return provinciaEntity;
	}
}
