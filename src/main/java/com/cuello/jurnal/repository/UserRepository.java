package com.cuello.jurnal.repository;

import com.cuello.jurnal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT * from users WHERE LOWER(username) = LOWER(:username)", nativeQuery = true)
    User getUserByUsername(@Param("username") String username);
}
