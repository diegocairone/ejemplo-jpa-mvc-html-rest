package com.cairone.appexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cairone.appexample.entities.PermisoEntity;

public interface PermisoRepository extends JpaRepository<PermisoEntity, String>, QueryDslPredicateExecutor<PermisoEntity> {

}
