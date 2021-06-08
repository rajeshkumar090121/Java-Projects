package com.cab.DAO;

import java.util.List;

import com.cab.pojo.User;

public interface CustomerDAO {

	String getandPersistCustomerDetails(User userDetails);

	List<String> getNearByCabs(String location, String cabs);

}
