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
public class Housing {
    private int id;
    private String housing_name;
    private String address;
    private int number_of_floors;
    private int number_of_master_room;
    private int number_of_single_room;
    private Double amount;
    private int owner_id;
    private Timestamp created_date;
    private Timestamp updated_date;
    private Owner owner;
}
