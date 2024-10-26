package com.adac.projectBasicAuth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.adac.projectBasicAuth.util.Roles;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				authorizateRequest -> authorizateRequest
				.requestMatchers(HttpMethod.GET, "/user/**").permitAll() //All Endpoints GET allow
				.requestMatchers("/user/new").hasAnyRole(Roles.CREATE_USER_ROLE, Roles.ADMIN_USER_ROLE)
				.requestMatchers(HttpMethod.DELETE, "/user/**").hasAnyRole(Roles.DELETE_USER_ROLE, Roles.ADMIN_USER_ROLE))
				.httpBasic(Customizer.withDefaults()).csrf((csrf) -> csrf.disable());
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails userAdmin = User.builder().username("admin").password(encoder.encode("adminpass"))
				.roles(Roles.ADMIN_USER_ROLE).build();
		UserDetails user1 = User.builder().username("luis").password(encoder.encode("luispass"))
				.roles(Roles.CREATE_USER_ROLE).build();
		UserDetails user2 = User.builder().username("karla").password(encoder.encode("karlapass"))
				.roles(Roles.DELETE_USER_ROLE).build();
		return new InMemoryUserDetailsManager(userAdmin, user1, user2);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
