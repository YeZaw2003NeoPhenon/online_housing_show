package com.example.online_housing_show.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_housing_show.model.Housing;
import com.example.online_housing_show.repository.housingRepository;

@Service
public class housingServiceImp implements housingService{
	
	@Autowired
	private housingRepository housingRepository;
	
	@Override
	public int saveHousing(Housing housing) {
		housing.setCreated_date(new Timestamp(System.currentTimeMillis()));
		housing.setUpdated_date(new Timestamp(System.currentTimeMillis()));
		return housingRepository.saveHousing(housing);
	}
	
	@Override
	public int editHousing(Housing housing) {
		housing.setUpdated_date(new Timestamp(System.currentTimeMillis()));
		return housingRepository.editHousing(housing);
	}
	
//	@Override
//	public List<Housing> getAllHousings() {
//		return housingRepository.getAllHousings();
//	}
//	
	  public List<Housing> getAllHousings(int page, int size, String housingName, Integer floors, Integer masterRoom, Integer singleRoom, Double amount, String postedDate) {
	    		  int offset = (page - 1) * size;
		  System.out.println("Offset: " + offset + ", Size: " + size);
	        return housingRepository.getAllHousings(offset, size, housingName, floors, masterRoom, singleRoom, amount, postedDate);
	    }
}
