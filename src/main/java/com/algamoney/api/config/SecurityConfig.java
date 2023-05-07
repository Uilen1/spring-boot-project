package com.algamoney.api.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((authz) -> authz.anyRequest().authenticated()).httpBasic();
//		return http.build();
//	}
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/categorias").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic(withDefaults())
                ;
	    return http.build();
	  }

    @Bean
    UserDetailsService userDetailsService() {
	    UserDetails user = User.builder()
	    		.username("admin")
	    		.password(passwordEncoder().encode("admin"))
	    		.roles("ROLE")
	    		.build();
	    return new InMemoryUserDetailsManager(user);
	  }

    @Bean
    PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(8);
	  }

}
