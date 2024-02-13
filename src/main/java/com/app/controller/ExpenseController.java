package com.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptions.UserNotFoundException;
import com.app.modal.Expenses;
import com.app.pojo.NewExpenseRequest;
import com.app.service.ExpenseService;

@CrossOrigin
@RestController
@RequestMapping("api/expenses")

public class ExpenseController {

	
	 @Autowired
	  private ExpenseService expenseService;
	 
	 
	 @GetMapping
	 public List<Expenses> getAllExpenses(){
		 
		 return expenseService.getAll();
		 
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<Expenses> getExpenseById(@PathVariable Integer id){
		 
		 Expenses existingExpense = expenseService.getFindById(id);
		 
		 if(existingExpense!=null) {
			 return new ResponseEntity<>(existingExpense,HttpStatus.CREATED);
		 }else {
			 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		 }
	 }
	 
	 
	 @PostMapping()
	    public ResponseEntity<String> addExpense(@RequestBody NewExpenseRequest newExpense) {
	        try {
	            Expenses addedExpense = expenseService.addNewExpense(newExpense);
	            return ResponseEntity.status(HttpStatus.CREATED).body("Expense added successfully to group: " + addedExpense.getUser().getGroup().getGroupName());
	        } catch (UserNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the expense");
	        }
	    }
	 
	 
	 
	 
	
}
