package com.BackSecure.JWTService.Controller;

import com.BackSecure.JWTService.Model.Logs;
import com.BackSecure.JWTService.Service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LogsController {

    @Autowired
    LogsService logsService;


    @GetMapping("/loadByName/{username}")
    public Logs getByName(@PathVariable String username)
    {
        return logsService.getByName(username);
    }


    @PostMapping("/addUser")
    public String addUser(@RequestBody Logs logs)
    {
        System.out.println(logs.getName()+"  "+logs.getPass());
        logsService.addUser(logs);
        return "Added successfully!!";
    }
}
