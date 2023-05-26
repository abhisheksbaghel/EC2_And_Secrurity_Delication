package com.BackSecure.JWTService.Service;

import com.BackSecure.JWTService.Model.Logs;
import com.BackSecure.JWTService.Repository.LogsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LogsServiceImpl implements LogsService{

    @Bean
    private PasswordEncoder encoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    LogsDao logsDao;

    @Override
    public Logs getByName(String username) {
        return logsDao.findByName(username);
    }

    @Override
    public void addUser(Logs logs) {
        logs.setPass(encoder.encode(logs.getPass()));
        logsDao.save(logs);
    }
}
