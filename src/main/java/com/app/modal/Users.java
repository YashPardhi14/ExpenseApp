package com.app.modal;

import java.util.ArrayList;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String userName;

	@ManyToOne
	@JoinColumn(name = "group_id")
	
	private Groups group;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Expenses> expenses = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return userName;
	}

	public void setName(String userName) {
		this.userName = userName;
	}

	public Groups getGroup() {
		return group;
	}

	public void setGroup(Groups group) {
		this.group = group;
	}

	public List<Expenses> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expenses> expenses) {
		this.expenses = expenses;
	}
}
