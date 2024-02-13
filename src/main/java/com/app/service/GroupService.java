package com.app.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.modal.Groups;
import com.app.repository.GroupRepository;

@Service
public class GroupService {

	
	@Autowired
	private GroupRepository groupRepository;
	
	
	
	   public List<Groups> getAllGroups() {
	        return groupRepository.findAll();
	    }

	    public Optional<Groups> getGroupById(int groupId) {
	        return groupRepository.findById(groupId);
	    }
	    
	    
	    public Groups addGroup(String groupName) {
	    	Groups checkGroupPresent=groupRepository.findByGroupName(groupName);
	    	
	    	if(checkGroupPresent==null) {
	    		Groups group = new Groups();
		        group.setGroupName(groupName);
		        return groupRepository.save(group);
	    	}else {
	    		return null;
	    	}
	    	
	    }
	
	
}
