package com.cairone.appexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cairone.appexample.entities.PersonaEntity;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer>, QueryDslPredicateExecutor<PersonaEntity> {

}
