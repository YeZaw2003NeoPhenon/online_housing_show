package com.example.online_housing_show.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	@Override
	  public Page<Housing> getAllHousings(Pageable pageable , String housingName, Integer floors, Integer masterRoom, Integer singleRoom, Double amount, String postedDate) {
			int offset = pageable.getPageNumber() * pageable.getPageSize();
			int size = pageable.getPageSize();
		  System.out.println("Offset: " + offset + ", Size: " + size);
	        List<Housing>  housings =  housingRepository.getAllHousings(offset , size, housingName, floors, masterRoom, singleRoom, amount, postedDate);
	        
	        long total = getTotalCount(housingName, floors, masterRoom, singleRoom, amount, postedDate);
	        
	        return new PageImpl<Housing>(housings , pageable , total);
	    }
	  
		@Override 
		 public long getTotalCount( String housingName, Integer floors, Integer masterRoom, Integer singleRoom, Double amount, String postedDate
				 ) {
	        // Delegate to the mapper to get the total count of filtered records
	        return housingRepository.getTotalCount(housingName, floors, masterRoom, singleRoom, amount, postedDate);
	    }
		
}
