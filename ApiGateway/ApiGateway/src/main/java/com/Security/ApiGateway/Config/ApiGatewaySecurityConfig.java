package com.Security.ApiGateway.Config;

import com.Security.ApiGateway.Entity.Logs;
import jakarta.ws.rs.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class ApiGatewaySecurityConfig {
    @Autowired
    private CustomAuthManager customAuthManager;

    @Autowired
    private CustomSecurityContextRepo customSecurityContextRepo;

    @Bean
    protected SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.csrf().disable();
        serverHttpSecurity.formLogin().disable();
        serverHttpSecurity.authenticationManager(this.customAuthManager);
        serverHttpSecurity.securityContextRepository(this.customSecurityContextRepo);
        serverHttpSecurity.authorizeExchange()
                .pathMatchers("/register", "/login", "/authenticate").permitAll()
                .anyExchange().authenticated();

        return serverHttpSecurity.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    AuthenticationManager authenticationManager(){
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }
        };
    }

    @Bean
    Logs logs()
    {
        return new Logs();
    }


}



