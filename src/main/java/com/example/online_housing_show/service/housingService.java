package com.example.online_housing_show.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.online_housing_show.model.Housing;

public interface housingService {
	 public abstract int saveHousing(Housing housing);
	 public abstract int editHousing(Housing housing);
//	 List<Housing> getAllHousings();
	 public abstract  Page<Housing> getAllHousings(Pageable pageable, String housingName, Integer floors,
                Integer masterRoom, Integer singleRoom, Double amount, String postedDate);
	 
	 public abstract long getTotalCount(
			 String housingName, Integer floors, Integer masterRoom, 
			 Integer singleRoom, Double amount, String postedDate
			 );
}



