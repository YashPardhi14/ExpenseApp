package com.app.modal;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Groupss")
public class Groups {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String groupName;

	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Users> users = new ArrayList<>();
	
	
	
	
	public double getTotalExpense() {
	    double totalExpense = 0.0;

	    for (Users user : users) {
	        List<Expenses> expenses = user.getExpenses();
	        for (Expenses expense : expenses) {
	            totalExpense += expense.getAmount();
	        }
	    }

	    return totalExpense;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getGroupName() {
		return groupName;
	}




	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}




	public List<Users> getUsers() {
		return users;
	}




	public void setUsers(List<Users> users) {
		this.users = users;
	}
	
	

}
