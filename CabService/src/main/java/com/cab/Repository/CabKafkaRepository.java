package com.cab.Repository;

import org.springframework.data.repository.CrudRepository;

import com.cab.Entity.CabDetailsEntity;

public interface CabKafkaRepository extends CrudRepository<CabDetailsEntity, Integer> {
	Iterable<CabDetailsEntity> findAll();
}
