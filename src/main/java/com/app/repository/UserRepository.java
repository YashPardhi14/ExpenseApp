package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.modal.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
	
	
	

}
