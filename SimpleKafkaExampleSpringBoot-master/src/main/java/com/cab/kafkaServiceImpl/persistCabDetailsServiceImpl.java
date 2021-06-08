package com.cab.kafkaServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cab.kafkaConsumer.CabKafkaConsumer;
import com.cab.kafkaDAO.persistCabDetailsDAO;
import com.cab.kafkaService.persistCabDetailsService;
import com.cab.pojo.CabDetails;

public class persistCabDetailsServiceImpl implements persistCabDetailsService {
	private static final Logger logger = Logger.getLogger(persistCabDetailsServiceImpl.class);
	@Autowired
	persistCabDetailsDAO persistCabDetailDAO;
	@Override
	public void persistCabDetails(CabDetails cabDetails) {
		// TODO Auto-generated method stub
		 try {
			 persistCabDetailDAO.persistCabDetails(cabDetails);
             }
             catch(Exception e) {
             	logger.error(e.getMessage());
             }
		
	}

}
