package com.Security.ApiGateway.Config;

import com.Security.ApiGateway.Entity.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Logs logs;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {

        var log= Logs.builder()
                .name(request.getName())
                .pass(request.getPass())
                .build();
        restTemplate.postForObject("http://10.0.61.194:8088/addUser",log,String.class);



        var jwtToken= jwtService.generateToken(log);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getName(),
                        request.getPass()
                )
        );

        String url="http://10.0.61.194:8088/loadByName/"+request.getName();

        var log= restTemplate.getForObject(url,Logs.class);
        var jwtToken=jwtService.generateToken(log);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
