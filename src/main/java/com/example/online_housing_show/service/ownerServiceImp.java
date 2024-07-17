package com.example.online_housing_show.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_housing_show.model.Owner;
import com.example.online_housing_show.repository.ownerRepository;

@Service
public class ownerServiceImp implements ownerService{
	
	@Autowired
	private ownerRepository ownerRepository;
	
	@Override
	public int saveOwnerAccount(Owner owner) {
		return ownerRepository.saveOwnerAccount(owner);
	}
	
}
