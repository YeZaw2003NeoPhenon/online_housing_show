package com.example.online_housing_show.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.online_housing_show.model.Housing;

@Mapper
public interface housingRepository {
	public abstract int saveHousing(Housing housing);
	public abstract int editHousing(Housing housing);
	
//	 List<Housing> getAllHousings();
    List<Housing> getAllHousings(@Param("offset") int offset, @Param("size") int size,
            @Param("housingName") String housingName, @Param("floors") Integer floors,
            @Param("masterRoom") Integer masterRoom, @Param("singleRoom") Integer singleRoom,
            @Param("amount") Double amount, @Param("postedDate") String postedDate);

}
