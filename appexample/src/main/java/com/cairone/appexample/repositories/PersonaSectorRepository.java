package com.cairone.appexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cairone.appexample.entities.PersonaSectorEntity;
import com.cairone.appexample.entities.PersonaSectorPKEntity;

public interface PersonaSectorRepository extends JpaRepository<PersonaSectorEntity, PersonaSectorPKEntity>, QueryDslPredicateExecutor<PersonaSectorEntity> {

}
