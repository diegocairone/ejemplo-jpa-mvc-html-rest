package com.cairone.appexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cairone.appexample.entities.LocalidadEntity;
import com.cairone.appexample.entities.LocalidadPKEntity;

public interface LocalidadRepository extends JpaRepository<LocalidadEntity, LocalidadPKEntity>, QueryDslPredicateExecutor<LocalidadEntity> {

}
