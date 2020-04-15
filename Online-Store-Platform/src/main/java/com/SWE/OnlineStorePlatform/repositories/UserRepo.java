package com.SWE.OnlineStorePlatform.repositories;

import java.util.List;
import java.util.Optional;

import com.SWE.OnlineStorePlatform.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Long> {// CrudRepository<User, Long>{//
	// @Query("SELECT u FROM user u WHERE u.email = ?1 or u.username = ?1")
	// List<User> findByEmailOrUsername(String mail_username);

	// public Optional<User> findByEmailOrUserName(String email);

	// @Query("SELECT t FROM User t where t.email = ?1 OR t.username = ?1")
	// public Optional<User> findByEmailOrUserName(String email);

	// @Query(value = "SELECT * FROM User t where t.email = ?0 OR t.username = ?0",
	// nativeQuery=true
	// )
	// public User findByEmailOrUserName(String email);
	//

	@Query("SELECT p FROM User p WHERE p.username = :email_username OR p.email = :email_username")
	public User findByEmailOrUserName(@Param("email_username") String email_username);
	//
}
