package com.BackSecure.JWTService.Service;

import com.BackSecure.JWTService.Model.Logs;

public interface LogsService {

    public Logs getByName(String username);
    public void addUser(Logs logs);
}
