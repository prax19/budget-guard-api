package com.prax19.config;

import com.prax19.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${budget-guard.api-key.key}")
    private String principalRequestHeader;

    @Value("${budget-guard.api-key.value}")
    private String principalRequestValue;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final ApiKayAuthFilter filter = new ApiKayAuthFilter(principalRequestHeader);
        filter.setAuthenticationManager((Authentication authentication) -> {
                final String principal = (String) authentication.getPrincipal();
                if(!principalRequestValue.equals(principal))
                    throw new BadCredentialsException("User did not provide valid API key");
                authentication.setAuthenticated(true);
                return authentication;
            }
        );

        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilter(filter);
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers(toH2Console()).permitAll()
                .anyRequest().authenticated()
            ).httpBasic(withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers((header) -> header.frameOptions((option) -> option.disable()));
        http.cors((cors) -> cors.disable());
        http.authenticationProvider(daoAuthenticationProvider());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        
        return provider;
    }
    
}
