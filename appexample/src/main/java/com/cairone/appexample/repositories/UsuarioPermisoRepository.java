package com.cairone.appexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cairone.appexample.entities.UsuarioPermisoEntity;
import com.cairone.appexample.entities.UsuarioPermisoPKEntity;

public interface UsuarioPermisoRepository extends JpaRepository<UsuarioPermisoEntity, UsuarioPermisoPKEntity>, QueryDslPredicateExecutor<UsuarioPermisoEntity> {

}
