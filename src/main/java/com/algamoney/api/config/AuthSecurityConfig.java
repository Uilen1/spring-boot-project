package com.algamoney.api.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.time.Duration;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthSecurityConfig {

    
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
    	OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
	    return http.formLogin(withDefaults()).build();
	  }
    
    @Bean
    SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
        .requestMatchers("/categorias").permitAll()
        .anyRequest().authenticated()
        ;
	    return http.formLogin(withDefaults()).build();
	  }
    
    @Bean
    RegisteredClientRepository registeredClientRepository (PasswordEncoder passwordEncoder) {
    	
    	RegisteredClient userClient = RegisteredClient
    			.withId("1")
    			.clientId("admin")
    			.clientSecret(passwordEncoder.encode("admin"))
    			.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
    			.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
    			.scope("read")
    			.scope("write")
    			.tokenSettings(TokenSettings.builder()
    					.accessTokenTimeToLive(Duration.ofMinutes(2))
    					.build())
    			.clientSettings(ClientSettings.builder()
    					.requireAuthorizationConsent(false)
    					.build())
    			.build()
    			;
    	
    	return new InMemoryRegisteredClientRepository(Arrays.asList(userClient));
    	
    }

    @Bean
    AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder()
				.issuer("http://localhost:8080")
				.build();
    	
    }
    
    

}
