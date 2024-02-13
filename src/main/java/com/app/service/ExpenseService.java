package com.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.GroupNotFoundException;
import com.app.exceptions.UserNotFoundException;
import com.app.modal.Expenses;
import com.app.modal.Groups;
import com.app.modal.Users;
import com.app.pojo.NewExpenseRequest;
import com.app.repository.ExpenseRepository;
import com.app.repository.GroupRepository;
import com.app.repository.UserRepository;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;
	
	
	public List<Expenses> getAll() {
		
		return expenseRepository.findAll();
	}

	
	public Expenses getFindById(Integer id) {
		return expenseRepository.findById(id).orElse(null);
	}
	
	
	public Expenses addNewExpense(NewExpenseRequest newExpense) {
		  Users user = userRepository.findById(newExpense.getUserId())
	                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + newExpense.getUserId()));
    Groups g =groupRepository.findById(newExpense.getGroupId())

.orElseThrow(()-> new GroupNotFoundException("Group not found with Id: "+newExpense.getGroupId()));


	        // Create new expense
	        Expenses expense = new Expenses();
	        
	        expense.setUser(user);
	        expense.setDescription(newExpense.getDescription());
	        expense.setAmount(newExpense.getPaid());
	        expense.setDateTime(LocalDateTime.now()); // Set the current date and time

	        // Update user information
	        // For example, update total expenses of the user
	        user.getExpenses().add(expense);
	        userRepository.save(user);

	        // Save the expense
	        return expenseRepository.save(expense);
	    
	    }
	}

