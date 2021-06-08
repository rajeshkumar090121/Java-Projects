package com.cab.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cab.Service.CustomerService;
import com.cab.pojo.User;;

@RequestMapping("/send-notification")
@ResponseBody
public class CabController {
	
	@Autowired
	CustomerService customerService;

	 @RequestMapping("/CustomerDetails")
	    @ResponseBody
	    public String getandPersistCustomerDetails(@RequestBody User userDetails) {
	       // return firebaseService.sendNotification(note, topic);
		 try {
	        return customerService.getandPersistCustomerDetails(userDetails);
		 }
		 catch(Exception e)
		 {
			 return e.toString();
		 }
	    }
	 
	 @RequestMapping("/getNearByCabs")
	    @ResponseBody
	    public List<String> getNearByCabs( @RequestParam(name = "location") String location, @RequestParam(name="cabs") String cabs) {
	       // return firebaseService.sendNotification(note, topic);
		 List<String> result = new ArrayList<String>();
		 try {
	        return customerService.getNearByCabs(location,cabs);
		 }
		 catch(Exception e)
		 {
			 result.add("Failure");
			 return result;
		 }
	    }
}
