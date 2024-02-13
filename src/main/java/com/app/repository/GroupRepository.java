package com.app.repository;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.modal.Groups;

@Repository
public interface GroupRepository extends JpaRepository<Groups,Integer> {
	
	
                  Groups findByGroupName(String groupName);
                  

}
