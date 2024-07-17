package com.example.online_housing_show.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
	    private int id;
	    private String owner_user_name;
	    private String owner_name;
	    private String owner_email;
	    private String password;
	    private Timestamp created_date;
	    private Timestamp updated_date;
	   
}
