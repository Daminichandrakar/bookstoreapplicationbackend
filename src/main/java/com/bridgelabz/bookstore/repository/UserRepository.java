package com.bridgelabz.bookstore.repository;


import com.bridgelabz.bookstore.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	public UserEntity findByEmailId(String email);
	
	public boolean existsByEmailId(String email);
}
