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

import com.app.modal.Groups;
import com.app.pojo.NewGroupRequest;
import com.app.service.GroupService;
@CrossOrigin
@RestController
@RequestMapping("/api/groups")
public class GroupController {
	
	
	@Autowired
	private GroupService groupService;

	@PostMapping
	public ResponseEntity<Groups> addGroup(@RequestBody NewGroupRequest newGroup) {
		
		Groups createGroup =groupService.addGroup(newGroup.getGroupName());
		
		if(createGroup!=null) {
			return new ResponseEntity<>(createGroup, HttpStatus.CREATED);
		}else {
	return	new ResponseEntity<>( HttpStatus.BAD_REQUEST);
			
			
		}
		
	}

	@GetMapping
	public List<Groups> getAllGroups() {
		return groupService.getAllGroups();
	}

	@GetMapping("/{groupId}")
	public Optional<Groups> getGroupById(@PathVariable int groupId) {
		return groupService.getGroupById(groupId);
	}

}
