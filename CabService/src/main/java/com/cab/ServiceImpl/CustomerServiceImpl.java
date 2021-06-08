package com.cab.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cab.DAO.CustomerDAO;
import com.cab.Service.CustomerService;
import com.cab.pojo.User;

public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDAO customerDao;

	public String getandPersistCustomerDetails(User userDetails) {
		// TODO Auto-generated method stub
		try {
		return customerDao.getandPersistCustomerDetails(userDetails);
		}
		catch(Exception e)
		{
			return e.toString();
		}
	}

	public List<String> getNearByCabs(String location, String cabs) {
		// TODO Auto-generated method stub
		        return customerDao.getNearByCabs(location,cabs);
			 
	}

}
