package com.example.online_housing_show.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_housing_show.model.Owner;
import com.example.online_housing_show.service.ownerServiceImp;

@RestController
@RequestMapping("/api/owners")
public class ownerController {

	@Autowired
	private ownerServiceImp ownerServiceImp;
	
	// exultant notes graciously marked down 
	// we can't pick out @RequestBody , as document is patronyzingly asking us to come up with FormUrlEncoded 
	// we have to use ModelAttribute Or RequestParam // noted
	// for --application/x-www-form-urlencoded--
	// @RequestBody is only for 
	// application/json or application/xml
	
	@RequestMapping( value = "/create", method = RequestMethod.POST , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> createOwner(@RequestParam String owner_user_name, 
											 @RequestParam String owner_name,
											 @RequestParam String owner_email,
											 @RequestParam String password){
		Owner owner = new Owner();
		owner.setOwner_user_name(owner_user_name);
		owner.setOwner_name(owner_name);
		owner.setOwner_email(owner_email);
		owner.setPassword(password);
		owner.setCreated_date(new Timestamp(System.currentTimeMillis()));
		owner.setUpdated_date(new Timestamp(System.currentTimeMillis()));

		int result_count = ownerServiceImp.saveOwnerAccount(owner);
		if( result_count > 0 ) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Owner Createdly truimphantly");
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something can be WrappedUp. Try again!");
		}
	}
	
	
}
