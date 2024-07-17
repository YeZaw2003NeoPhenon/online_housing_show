package com.example.online_housing_show.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_housing_show.model.userDetailModel;
import com.example.online_housing_show.service.userDetailServiceImp;

@RestController
public class AuthenticationController {
	
	@Autowired
	private userDetailServiceImp userDetailServiceImp;
	
	  @RequestMapping(value = "/api/authenticate" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String>authenticate( @RequestBody userDetailModel userDetailModel) {   
	        
	        boolean isAuthenticated = userDetailServiceImp.isAuthenticate(userDetailModel.getUsername(), userDetailModel.getPassword());
	        
	        if (isAuthenticated) {
	            return ResponseEntity.ok("Authentication successful");
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
	        }
	    }
}
