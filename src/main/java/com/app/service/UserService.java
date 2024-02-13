package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.app.exceptions.DataIntegrityException;
import com.app.exceptions.GroupNotFoundException;
import com.app.modal.Groups;
import com.app.modal.Users;
import com.app.pojo.NewUserRequest;
import com.app.repository.GroupRepository;
import com.app.repository.UserRepository;

@Service
public class UserService {

	
	@Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }	
    
    public Optional<Users> getUserById(int participantId) {
        return userRepository.findById(participantId);
    }
    
    public Users addNewUser(NewUserRequest user){
    	
    	
    	  try {
              Integer groupId = user.getGroupId();
              String userName = user.getUserName();

              // Check if the group exists
              Groups group = groupRepository.findById(groupId)
                      .orElseThrow(() -> new GroupNotFoundException("Group not found with ID: " + groupId));

              // Create a new participant
              Users newUser = new Users();
              newUser.setName(userName);
              newUser.setGroup(group);

              // Add the participant to the group and save the changes

              group.getUsers().add(newUser);
              groupRepository.save(group);

              // Save the participant
             return userRepository.save(newUser);
          } catch (DataIntegrityViolationException ex) {
              // Handle database integrity violation exception
              throw new DataIntegrityException("Failed to add participant: " + ex.getMessage());
          }
      }
    	
    	
    }
    
    

