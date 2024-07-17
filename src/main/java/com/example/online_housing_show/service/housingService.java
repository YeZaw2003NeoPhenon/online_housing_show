package com.example.online_housing_show.service;
import java.util.List;

import com.example.online_housing_show.model.Housing;

public interface housingService {
	 public abstract int saveHousing(Housing housing);
	 public abstract int editHousing(Housing housing);
//	 List<Housing> getAllHousings();
	    List<Housing> getAllHousings(int page, int size, String housingName, Integer floors,
                Integer masterRoom, Integer singleRoom, Double amount, String postedDate);

	 
}

