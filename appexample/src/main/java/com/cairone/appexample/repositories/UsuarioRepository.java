package com.cairone.appexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cairone.appexample.entities.UsuarioEntity;
import com.cairone.appexample.entities.UsuarioPKEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UsuarioPKEntity>, QueryDslPredicateExecutor<UsuarioEntity> {

}
