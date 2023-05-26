package com.Security.ApiGateway.Auth;

import ch.qos.logback.core.net.SyslogOutputStream;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class AuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Hii I am Filter!!!");
        String username= request.getHeader("username");
        String password=request.getHeader("password");
        if(username==null && password==null)
        {
            return;
        } else if (username==null || password==null) {
            response.setStatus(401);
            return;
        }



    }
}
