package com.cuello.jurnal.repository;

import com.cuello.jurnal.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    @Query(value = "SELECT * FROM diary WHERE username = :username", nativeQuery = true)
    List<Diary> getDiaries(@Param("username") String username);
}
