package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.modal.Expenses;

@Repository
public interface ExpenseRepository extends JpaRepository<Expenses,Integer>{
	
	
	

}
