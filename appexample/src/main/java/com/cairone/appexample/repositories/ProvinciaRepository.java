package com.cairone.appexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cairone.appexample.entities.ProvinciaEntity;
import com.cairone.appexample.entities.ProvinciaPKEntity;

public interface ProvinciaRepository extends JpaRepository<ProvinciaEntity, ProvinciaPKEntity>, QueryDslPredicateExecutor<ProvinciaEntity> {

}
