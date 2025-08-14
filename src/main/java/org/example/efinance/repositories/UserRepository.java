package org.example.efinance.repositories;

import java.util.Optional;

import org.example.efinance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM USERS WHERE username = :username AND ROWNUM <= 1", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM USERS WHERE email = :email AND ROWNUM <= 1", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM USERS WHERE username = :username", nativeQuery = true)
    int existsByUsernameNative(@Param("username") String username);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM USERS WHERE email = :email", nativeQuery = true)
    int existsByEmailNative(@Param("email") String email);

    default boolean existsByUsername(String username) {
        return existsByUsernameNative(username) > 0;
    }

    default boolean existsByEmail(String email) {
        return existsByEmailNative(email) > 0;
    }
}
