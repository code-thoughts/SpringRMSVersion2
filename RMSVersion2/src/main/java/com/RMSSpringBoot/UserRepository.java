package com.RMSSpringBoot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.email = ?1")
//	User findList<User> findByEmail(String email);
	User  findByEmail(String email);

}
