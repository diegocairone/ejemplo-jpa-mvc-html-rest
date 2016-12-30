package com.cairone.appexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cairone.appexample.entities.SectorEntity;

public interface SectorRepository extends JpaRepository<SectorEntity, Integer>, QueryDslPredicateExecutor<SectorEntity> {

}
