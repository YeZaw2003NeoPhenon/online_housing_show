package com.example.online_housing_show.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.online_housing_show.service.userDetailServiceImp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
						.successHandler( new AuthenticationSuccessHandler() {
							
							@Override
							public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
									Authentication authentication) throws IOException, ServletException {
								String remember_me = "";
								if( request.getCookies() != null ) {
									for( Cookie cookie : request.getCookies() ) {
										if(cookie.getName().equals("remember-me")) {
											remember_me += cookie.getValue();
										}
									}		
								}
								
								if( remember_me != null ) {
									request.getSession().setAttribute(" Custom-Remember-Me",remember_me);
		
								}
								
								Cookie successCookie = new Cookie("successCookie", "true");
								successCookie.setPath("/");
								successCookie.setHttpOnly(true);
								response.addCookie(successCookie);
								response.setStatus(HttpServletResponse.SC_OK);
								response.sendRedirect("/");
							}
							
						})
						.failureHandler(new AuthenticationFailureHandler() {
							
							@Override
							public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
									AuthenticationException exception) throws IOException, ServletException {
		                        Cookie failureCookie = new Cookie("failureCookie", "true");
		                        failureCookie.setPath("/");
		                        failureCookie.setHttpOnly(true);
		                        response.addCookie(failureCookie);
		                        response.sendRedirect("/api/authenticate?errorPopedUp=true");
								
							}
						})
						.and()
						
						.logout()
						.logoutUrl("/logout")
						.logoutSuccessUrl("/api/authenticate?logout=success")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						
						.and()
						
						.rememberMe()
						.key("uniqueAndSecret")
						.alwaysRemember(true)
						
						.and()
						.sessionManagement()
						.maximumSessions(1)
						.expiredUrl("/api/authenticate?sessionExpired = true");
			 
						return httpSecurity.build();
	}
	
}
