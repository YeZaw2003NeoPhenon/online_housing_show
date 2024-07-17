package com.example.online_housing_show.model;

import lombok.Getter;
import lombok.Setter;

public class userDetailModel {
	
	@Getter
	@Setter
	private String username;
	
	@Getter
	@Setter
	private String password;
	
	@Getter
	@Setter
	private boolean enabled;
	
	@Getter
	@Setter
	private String authority;	
}
