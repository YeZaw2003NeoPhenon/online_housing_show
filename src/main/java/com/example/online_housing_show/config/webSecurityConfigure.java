package com.example.online_housing_show.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.online_housing_show.service.userDetailServiceImp;

@EnableWebSecurity
@Configuration
public class webSecurityConfigure {
	
	@Autowired
	private userDetailServiceImp userDetailServiceImp;
	
	@Autowired
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailServiceImp);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain( HttpSecurity httpSecurity ) throws Exception {
	
			 httpSecurity
						.csrf().disable()
						.authorizeRequests()
						.requestMatchers("/api/authenticate").permitAll()
		                .requestMatchers("/swagger-ui/**", "/swagger-resources/**", "/webjars/**", "/v3/api-docs/**", "/v3/api-docs.yaml", "/v2/api-docs").permitAll()
						.requestMatchers("/css/**" , "/js/**" ,"/img/**","/file/**","/uploads/**").permitAll()
						.requestMatchers("/api/housings/**").permitAll()
						.requestMatchers("/api/owners/**").permitAll()
						.requestMatchers("/users/**").hasRole("users")
						.requestMatchers("/admin/**").hasRole("admin")
						.anyRequest().authenticated()
						.and()
						.httpBasic()
						.and()
						.formLogin()
						.defaultSuccessUrl("/")
						.failureForwardUrl("/login?errorPoppedUp=true")
						.and()
						
						.logout()
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login?logout=success")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID" , "remember-me")
						
						.and()
						
						.rememberMe()
						.key("unique")
						.alwaysRemember(true)
						
						.and()
						.sessionManagement()
						.maximumSessions(1)
						.expiredUrl("/login?sessionExpired = true");
			 
						return httpSecurity.build();
	}
	
}
