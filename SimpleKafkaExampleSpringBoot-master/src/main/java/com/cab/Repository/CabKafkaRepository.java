package com.cab.Repository;

import org.springframework.data.repository.CrudRepository;

import com.cab.kafkaEntity.CabDetailsEntity;

public interface CabKafkaRepository extends CrudRepository<CabDetailsEntity, Integer> {

}
