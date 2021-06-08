package com.cab.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cab.DAO.CustomerDAO;
import com.cab.Entity.CabDetailsEntity;
import com.cab.Entity.UserDetails;
import com.cab.Repository.CabKafkaRepository;
import com.cab.Repository.CabRepository;
import com.cab.pojo.User;

public class CustomerDAOImpl implements CustomerDAO{

	@Autowired
	CabRepository cabRepository;
	@Autowired
	CabKafkaRepository cabKafkaRepository;
	public String getandPersistCustomerDetails(User userDetails) {
		// TODO Auto-generated method stub
		try {
			 UserDetails userdetailsEntity= new UserDetails();
			 userdetailsEntity.setCustomerId(userDetails.getCustomerId());
			 userdetailsEntity.setLang(userDetails.getGeoLocation().getLang());
			 userdetailsEntity.setLati(userDetails.getGeoLocation().getLati());
			//Persist userDetails to DB
			 cabRepository.save(userdetailsEntity);
			 return "success";
		}
		catch(Exception e) {
			return "failure";
		}
		
	}

	public List<String> getNearByCabs(String location, String cabs) {
		// TODO Auto-generated method stub
		List<String> Cablist = new ArrayList<String>();
		try {
			//get location details from DB based on location id location
			UserDetails userDetails = cabRepository.findById(location);
			Iterable<CabDetailsEntity> cabDetails =cabKafkaRepository.findAll();
			for(CabDetailsEntity cabDetailsEntity:cabDetails) {
				if(distance(Double.parseDouble(userDetails.getLati()),Double.parseDouble(userDetails.getLang()),Double.parseDouble(cabDetailsEntity.getLatitude()),Double.parseDouble(cabDetailsEntity.getLongitude()),'K')<=Integer.parseInt(cabs))
				{
					Cablist.add(cabDetailsEntity.getCabId()); 
				}
			}
			//get location details of available cabs from DB
			//Calculate the distance from user location to cab location
			//return near by cabs
		}
		catch(Exception e) {
			
		}
		return Cablist;
	}
	

private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
  double theta = lon1 - lon2;
  double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
  dist = Math.acos(dist);
  dist = rad2deg(dist);
  dist = dist * 60 * 1.1515;
  if (unit == 'K') {
    dist = dist * 1.609344;
  } else if (unit == 'N') {
  dist = dist * 0.8684;
    }
  return (dist);
}

/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
/*::  This function converts decimal degrees to radians             :*/
/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
private double deg2rad(double deg) {
  return (deg * Math.PI / 180.0);
}

/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
/*::  This function converts radians to decimal degrees             :*/
/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
private double rad2deg(double rad) {
  return (rad * 180.0 / Math.PI);
}


}
