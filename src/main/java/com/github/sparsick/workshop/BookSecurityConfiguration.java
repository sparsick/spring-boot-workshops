package com.github.sparsick.workshop;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class BookSecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests((authorize) -> authorize
        .antMatchers(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/swagger-ui/**").permitAll()
                .anyRequest().authenticated())
			.httpBasic(withDefaults())
			.formLogin(withDefaults())
			.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
		var user = User.builder().username("user").password("password").roles("USER").build();
		var admin = User.builder().username("admin").password("admin").roles("USER", "ADMIN").build();
		return new InMemoryUserDetailsManager(user, admin);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
    }

}