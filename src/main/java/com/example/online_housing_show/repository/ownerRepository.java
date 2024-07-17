package com.example.online_housing_show.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.online_housing_show.model.Owner;

@Mapper
public interface ownerRepository {
	
	public abstract int saveOwnerAccount(Owner owner);
	
}
