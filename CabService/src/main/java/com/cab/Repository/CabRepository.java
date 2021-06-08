package com.cab.Repository;

import org.springframework.data.repository.CrudRepository;

import com.cab.Entity.UserDetails;

public interface CabRepository extends CrudRepository<UserDetails, Integer> {

	UserDetails findById(String location);

}
