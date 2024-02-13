package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojo.GroupSummaryDTO;
import com.app.service.GroupSummaryService;
@CrossOrigin
@RestController
@RequestMapping("/api/group")
public class GroupSummaryController {
	
	
	@Autowired
	private GroupSummaryService groupSummaryService;

	
	@GetMapping("/summary/{groupId}")
	public ResponseEntity<GroupSummaryDTO> getGroupSummary(@PathVariable int groupId){
		
		
		GroupSummaryDTO groupSummary = groupSummaryService.getGroupSummary(groupId);
	
		return new ResponseEntity<>(groupSummary,HttpStatus.OK);
		
		
	}

}
