package com.web.demo.config;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Autowired
	CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
	@Autowired
	CustomizeLogoutSuccessHandler customizeLogout;
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
	}
	@Override
 	public void configure(WebSecurity web) throws Exception {
 		web.ignoring()
 		// Spring Security should completely ignore URLs starting with /resources/
 				.antMatchers("/resources/**");}
 		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN') or hasRole('EMPLOYEE')");
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.authorizeRequests().and()
				.formLogin().successHandler(customizeAuthenticationSuccessHandler)
				.loginProcessingUrl("/login")
				
				.loginPage("/shop").usernameParameter("usernameUsers").passwordParameter("passwordUsers")
				.failureUrl("/shop?message=error")
				.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/shop");
		http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
	}
}
