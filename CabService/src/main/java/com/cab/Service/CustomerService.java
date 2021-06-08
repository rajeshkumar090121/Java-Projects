package com.cab.Service;

import java.util.List;

import com.cab.pojo.User;

public interface CustomerService {

	public String getandPersistCustomerDetails(User userDetails);

	public List<String> getNearByCabs(String location, String cabs);

}
