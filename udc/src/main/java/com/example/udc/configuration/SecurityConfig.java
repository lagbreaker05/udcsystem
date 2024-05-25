package com.example.udc.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.udc.service.CustomSuccessHandler;
import com.example.udc.service.CustomUserDetailsService;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

	@Autowired
	CustomSuccessHandler customSuccessHandler;
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.csrf(c -> c.disable())
		.authorizeHttpRequests(request ->request.requestMatchers("/admin/**").hasAuthority("ADMIN").requestMatchers("/user/**").permitAll()
				.requestMatchers("/registration", "/css/**") .permitAll().requestMatchers("/registration/**") .permitAll().requestMatchers("/login/**") .permitAll()
				.anyRequest().authenticated())
		
		.formLogin(form ->form.loginPage("/login").loginProcessingUrl("/login")
				.successHandler(customSuccessHandler).permitAll())
		.logout(form -> form.invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout").permitAll());
		
				return http.build();
		
	}
 
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
}