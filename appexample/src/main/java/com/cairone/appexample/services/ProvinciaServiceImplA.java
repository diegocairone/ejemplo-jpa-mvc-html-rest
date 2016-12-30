package com.cairone.appexample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cairone.appexample.entities.PaisEntity;
import com.cairone.appexample.entities.ProvinciaEntity;
import com.cairone.appexample.entities.QProvinciaEntity;
import com.cairone.appexample.repositories.ProvinciaRepository;
import com.mysema.query.types.expr.BooleanExpression;

@Service
public class ProvinciaServiceImplA implements ProvinciaService {

	@Autowired private ProvinciaRepository provinciaRepository = null;
	
	@Override
	public ProvinciaEntity nueva(PaisEntity paisEntity, String nombre) {
		
		QProvinciaEntity qProvincia = QProvinciaEntity.provinciaEntity;
		BooleanExpression exp = qProvincia.pais.eq(paisEntity);
		
		ProvinciaEntity ultimaProvinciaEntity = null;
		Iterable<ProvinciaEntity> provinciaEntities = provinciaRepository.findAll(exp, new PageRequest(0, 1, Direction.DESC, "id"));
		
		for(ProvinciaEntity provinciaEntity : provinciaEntities) {
			ultimaProvinciaEntity = provinciaEntity;
		}
		
		Integer provinciaID = ultimaProvinciaEntity == null ? 1 : ultimaProvinciaEntity.getId() + 1;
		
		ProvinciaEntity provinciaEntity = new ProvinciaEntity();
		
		provinciaEntity.setId(provinciaID);
		provinciaEntity.setNombre(nombre);
		provinciaEntity.setPais(paisEntity);
		
		provinciaRepository.save(provinciaEntity);
		
		return provinciaEntity;
	}
}
