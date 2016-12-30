package com.cairone.appexample.services;

import com.cairone.appexample.entities.PaisEntity;
import com.cairone.appexample.entities.ProvinciaEntity;

public interface ProvinciaService {

	public ProvinciaEntity nueva(PaisEntity paisEntity, String nombre);
}
