package com.example.online_housing_show.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.online_housing_show.model.userDetailModel;



@Mapper
public interface userDetailRepository {
	public abstract userDetailModel findUserByName(String username);
	
}
