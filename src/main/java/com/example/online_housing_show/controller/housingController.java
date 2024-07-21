package com.example.online_housing_show.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.online_housing_show.service.housingServiceImp;
import com.example.online_housing_show.service.userDetailServiceImp;
import com.example.online_housing_show.model.Housing;

@RestController
@RequestMapping("/api/housings")
public class housingController {
	
	@Autowired
	private housingServiceImp housingServiceImp;
	
	@Autowired
	private userDetailServiceImp userDetailServiceImp;
	
	   @RequestMapping(value = "/create", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Object> createHousing(@RequestBody Housing housingDTO) {
	        int resultCount = housingServiceImp.saveHousing(housingDTO);
	        if (resultCount > 0) {
	            return ResponseEntity.status(HttpStatus.CREATED).body("Housing created Virtuously");
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Try again!");
	        }
	    }
	   	
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.PUT ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> editHousing( @PathVariable int id , @RequestBody Housing housingDTO) {
    	 housingDTO.setId(id);
        int resultCount = housingServiceImp.editHousing(housingDTO);
        if (resultCount > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("Housing updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Try again!");
        }
    }
    
//    @RequestMapping(value = "/" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
//    public  ResponseEntity<List<Housing>> getAllHousing(){
//    	List<Housing> housings =  housingServiceImp.getAllHousings();
//    	return ResponseEntity.status(HttpStatus.ACCEPTED).body(housings);
//    }
    
    @RequestMapping( value = "/", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Housing>> getAllHousings(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(required = false) String housingName,
                                                        @RequestParam(required = false) Integer floors,
                                                        @RequestParam(required = false) Integer masterRoom,
                                                        @RequestParam(required = false) Integer singleRoom,
                                                        @RequestParam(required = false) Double amount,
                                                        @RequestParam(required = false) String postedDate) {
     String current_username =   userDetailServiceImp.getCurrentUser();
     System.out.println("Current logged in user: " + current_username);
     Pageable pageable = PageRequest.of(page, size);
     
     Page<Housing> housings = housingServiceImp.getAllHousings(pageable, housingName, floors, masterRoom, singleRoom, amount, postedDate);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(housings);
    }
}
