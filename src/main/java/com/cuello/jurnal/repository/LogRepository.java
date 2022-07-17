package com.cuello.jurnal.repository;

import com.cuello.jurnal.api.logs.LogResource;
import com.cuello.jurnal.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {
    @Query(value = "SELECT text, date FROM log WHERE LOWER(username) = LOWER(:username) " +
            "ORDER BY date DESC LIMIT 3 OFFSET :offset", nativeQuery = true)
    List<LogResource> getLogsByUsername(@Param("username") String username, @Param("offset") int offset);

    @Query(value = "SELECT * FROM log WHERE LOWER(username) = LOWER(:username) AND date = :date", nativeQuery = true)
    Log getLogByUsernameAndDate(@Param("username") String username, @Param("date") LocalDate date);
}
