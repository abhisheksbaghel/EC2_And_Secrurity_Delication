package com.BackSecure.JWTService.Repository;

import com.BackSecure.JWTService.Model.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsDao extends JpaRepository<Logs,Integer> {

    public Logs findByName(String username);
}
