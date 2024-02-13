package com.app.controller;



import java.util.List;
import java.util.Optional;

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

import com.app.exceptions.DataIntegrityException;
import com.app.exceptions.GroupNotFoundException;
import com.app.modal.Users;
import com.app.pojo.NewUserRequest;
import com.app.service.UserService;
@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping
	public List<Users> getAllParticipants() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public Optional<Users> getParticipantById(@PathVariable int id) {
		return userService.getUserById(id);
	}
	
	
	@PostMapping()
	 public ResponseEntity<Users> addParticipant(@RequestBody NewUserRequest newUser) {
        try {
        	Users createdUser=userService.addNewUser(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (GroupNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (DataIntegrityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
