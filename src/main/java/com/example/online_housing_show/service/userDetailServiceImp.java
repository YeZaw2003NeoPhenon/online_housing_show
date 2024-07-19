package com.example.online_housing_show.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.online_housing_show.model.userDetailModel;
import com.example.online_housing_show.repository.userDetailRepository;


@Service
public class userDetailServiceImp implements UserDetailsService{
	
	@Autowired
	private userDetailRepository userDetailRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	 userDetailModel userDetailModel = userDetailRepository.findUserByName(username);
	 
	 if( userDetailModel == null ) {
		 new UsernameNotFoundException(" User still stuck on tie to be found!");
	 }
	 
	 List<GrantedAuthority> authorities = new ArrayList<>();
	 
	 authorities.add( new SimpleGrantedAuthority("ROLE_" + userDetailModel.getAuthority() ));
	 
		return User.withUsername(userDetailModel.getUsername())
				.password(userDetailModel.getPassword())
				.authorities(authorities).credentialsExpired(false)
				.accountExpired(!userDetailModel.isEnabled())
				.build();
	}
	
	
	public boolean isAuthenticate(String username , String password) {
		
		userDetailModel userDetailModel = userDetailRepository.findUserByName(username);
		
		if( userDetailModel == null ) {
			return false;
		}
		
		return new BCryptPasswordEncoder().matches(password, userDetailModel.getPassword());
	}
	
	public String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if( authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();
			
			if( principal instanceof userDetailModel ) {
				return ((userDetailModel) principal).getUsername();
			}
			else {
				return principal.toString();
			}
		}
		return null;
	}
	
}
