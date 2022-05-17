package com.patroclos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class FormLoginConfigurationAdapter extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
			.antMatchers("/welcome*").hasAnyRole("USER")
		.and()
			.formLogin()
			.loginPage("/index.html")
			.defaultSuccessUrl("http://localhost:3000", true)
			.permitAll()
		.and()
			.logout()
			.logoutSuccessUrl("/index.html");
	}

	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password(encoder().encode("password")).roles("USER");
	}
	

	@Bean
	public static PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}