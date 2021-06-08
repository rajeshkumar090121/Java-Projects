package com.cab.kafkaDAOImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cab.Repository.CabKafkaRepository;
import com.cab.kafkaDAO.persistCabDetailsDAO;
import com.cab.kafkaEntity.CabDetailsEntity;
import com.cab.kafkaServiceImpl.persistCabDetailsServiceImpl;
import com.cab.pojo.CabDetails;

public class persistCabDetailsDAOImpl implements persistCabDetailsDAO {
	@Autowired
	CabKafkaRepository cabkafkaRepository;
	private static final Logger logger = Logger.getLogger(persistCabDetailsDAOImpl.class);
	@Override
	public void persistCabDetails(CabDetails cabDetails) {
		// TODO Auto-generated method stub
		 try {
			 CabDetailsEntity cabDetailsEntity = new CabDetailsEntity();
			 cabDetailsEntity.setCabId(cabDetails.getPayload().getCabId());
			 cabDetailsEntity.setDriverId(cabDetails.getPayload().getDriverId());
			 cabDetailsEntity.setEventID(cabDetails.getHeader().getEventId());
			 cabDetailsEntity.setEventName(cabDetails.getHeader().getEventName());
			 cabDetailsEntity.setLatitude(cabDetails.getPayload().getGeoLocation().getLatitude());
			 cabDetailsEntity.setLongitude(cabDetails.getPayload().getGeoLocation().getLongitude());
			 cabDetailsEntity.setTimestamp(cabDetails.getHeader().getTimestamp());
			 
			 cabkafkaRepository.save(cabDetailsEntity);
			 
             }
             catch(Exception e) {
             	logger.error(e.getMessage());
             }
	}

}
