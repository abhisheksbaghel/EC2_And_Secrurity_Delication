package com.Security.ApiGateway.Config;

import com.Security.ApiGateway.Entity.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@Component
public class CustomAuthManager implements ReactiveAuthenticationManager {

    @Autowired
    private JwtService jwtService;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) throws AuthenticationException {
        String jwt=authentication.getCredentials().toString();
        String username= jwtService.extractUsername(jwt);

        System.out.println(jwt+"    *    "+username);

        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            System.out.println("1st Line");
            String url="http://10.0.61.194:8088/loadByName/"+username;
            System.out.println("2st Line");
            Logs logs=restTemplate.getForObject(url,Logs.class);
            System.out.println(logs);
            if(jwtService.isTokenValid(jwt,logs)){

                System.out.println("4st Line");
                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                        logs,
                        null,
                        logs.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
                return Mono.just(authToken);
            }
            else {
                System.out.println("token not matching");
                return Mono.just(authentication);
            }
        }
            else {
                return Mono.just(authentication);
        }
    }
}
