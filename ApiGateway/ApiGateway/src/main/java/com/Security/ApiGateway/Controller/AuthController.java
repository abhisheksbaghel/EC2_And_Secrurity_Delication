package com.Security.ApiGateway.Controller;

import com.Security.ApiGateway.Config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    //@Autowired
   // private PassAuthManager passAuthManager;
//    @PostMapping("/login")
//    public String register(@RequestBody RegUsers regUsers)
//    {
//        System.out.println(regUsers.getName()+"   "+regUsers.getPass());
//        try {
//            Authentication authentication =passAuthManager.authenticate(new UsernamePasswordAuthenticationToken(regUsers.getName(), regUsers.getPass()));
//            if (authentication != null)
//                return authentication.toString();
//            else
//                return "Access Denied";
//        }
//        catch (Exception e)
//        {
//            return "Access Denied";
//        }
//    }

    @GetMapping("/dashboard")
    public String showDashboard(){
        System.out.println("Dashboard me aaya");
        return "Dashboard Content";
    }


    @Autowired
    private AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request){
        System.out.println("Register me aaya");
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request){

        return ResponseEntity.ok(service.authenticate(request));
    }
}
