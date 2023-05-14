package com.algamoney.api.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

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
    			.withId("0")
    			.clientId("user")
    			.clientSecret(passwordEncoder.encode("user"))
    			.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
    			.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
    			.scope("users:read")
    			.scope("users:write")
    			.tokenSettings(TokenSettings.builder()
    					.accessTokenTimeToLive(Duration.ofMinutes(20))
    					.build())
    			.clientSettings(ClientSettings.builder()
    					.requireAuthorizationConsent(false)
    					.build())
    			.build()
    			;
    	
    	RegisteredClient blogClient = RegisteredClient
    			.withId("1")
    			.clientId("blog")
    			.clientSecret(passwordEncoder.encode("blog"))
    			.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
    			.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
    			.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
    			.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
    			.redirectUri("http://localhost:8080/lancamentos")
    			.redirectUri("https://oidcdebugger.com/debug")
    			.redirectUri("https://oauth.pstmn.io/v1/callback")
    			.scope("myUser:read")
    			.scope("myUser:write")
    			.scope("posts:write")
    			.tokenSettings(TokenSettings.builder()
    					.accessTokenTimeToLive(Duration.ofMinutes(20))
    					.refreshTokenTimeToLive(Duration.ofDays(1))
    					.reuseRefreshTokens(false)
    					.build())
    			.clientSettings(ClientSettings.builder()
    					.requireAuthorizationConsent(true)
    					.build())
    			.build()
    			;
    	
    	return new InMemoryRegisteredClientRepository(Arrays.asList(userClient,blogClient));
    	
    }


    @Bean
    JWKSource<SecurityContext> jwkSource() {
		KeyPair keyPair = generateRsaKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		RSAKey rsaKey = new RSAKey.Builder(publicKey)
				.privateKey(privateKey)
				.keyID(UUID.randomUUID().toString())
				.build();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return new ImmutableJWKSet<>(jwkSet);
	}

	private static KeyPair generateRsaKey() { 
		KeyPair keyPair;
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
		}
		catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
		return keyPair;
	}

    @Bean
    JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}
    
    @Bean
    JwtEncoder jwtEncoder (JWKSource<SecurityContext> jwtSource) {
		return new NimbusJwtEncoder(jwtSource);
    }


    @Bean
    AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder()
				.issuer("http://localhost:8080")
				.build();
    	
    }
    
    

}
