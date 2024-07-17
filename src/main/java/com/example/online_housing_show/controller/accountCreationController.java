package com.example.online_housing_show.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_housing_show.model.userDetailModel;
import com.example.online_housing_show.service.accountCreationServiceImp;

@RestController
public class accountCreationController {
	
	@Autowired
	private accountCreationServiceImp accountCreationServiceImp;
	
	@RequestMapping( value = "/api/accountCreationProcess", method = RequestMethod.POST )
	public ResponseEntity<Object> accountCreationProcess( @RequestBody userDetailModel userObjTobeAuthorized ) {
		
		int totalAccountCreationResult = accountCreationServiceImp.accountCreationProcess(userObjTobeAuthorized);
		
		if( totalAccountCreationResult == 2 ) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Account Created Virtuously");
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Account Creation Dropped out!");
		}
	}
}
