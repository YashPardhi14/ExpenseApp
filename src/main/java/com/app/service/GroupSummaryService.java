package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.GroupNotFoundException;
import com.app.modal.Expenses;
import com.app.modal.Groups;
import com.app.modal.Users;
import com.app.pojo.GroupSummaryDTO;
import com.app.pojo.MemberSummaryDTO;
import com.app.repository.GroupRepository;

@Service
public class GroupSummaryService {

	
	
	  @Autowired
	    private GroupRepository groupRepository;
	  
	  public GroupSummaryDTO getGroupSummary(int groupId) {
		  
		  Groups group =groupRepository.findById(groupId)
				  .orElseThrow(()->new GroupNotFoundException("Group with group ID:"+groupId+" doesn't exist..."));
		  
		  GroupSummaryDTO groupSummary = new GroupSummaryDTO();
		  groupSummary.setGroupName(group.getGroupName());
		  
		  groupSummary.setTotalExpenseOfGroup(group.getTotalExpense());
		  
		  List<MemberSummaryDTO> memberSummaries =new ArrayList<>();
		  
		  for(Users user:group.getUsers()) {
			  
			  MemberSummaryDTO memberSummary =calculateMemberSummary(user,group);
			  
			  memberSummaries.add(memberSummary);
		  }
		  
		  groupSummary.setMembers(memberSummaries);
		  return groupSummary;
		  
		  
		  
		  
		  
	  }
	  
	 
	  
	  private MemberSummaryDTO calculateMemberSummary(Users user, Groups group) {
		  
		    MemberSummaryDTO memberSummary = new MemberSummaryDTO();
		    memberSummary.setMemberName(user.getName());
		    

		    double totalAmountPaid = 0.0;
		    double totalAmountOwed = 0.0;
		    double totalAmountToBePaid = 0.0;

		    // Calculate total amount paid by the member
		    for (Expenses expense : user.getExpenses()) {
		        if (expense.getUser().getId() == user.getId()) {
		            totalAmountPaid += expense.getAmount();
		        }
		    }
		    
		    
		    double totalGroupExpense = group.getTotalExpense();
		    
		    double share =totalGroupExpense/group.getUsers().size();
		    
		    
		    double checkNetExpense = share -totalAmountPaid;
		    
		    if(checkNetExpense > 0) {
		    	totalAmountToBePaid= checkNetExpense;
		    }else {
		    	totalAmountOwed = checkNetExpense;
		    }

              
		    memberSummary.setAmountPaid(totalAmountPaid);
		    memberSummary.setAmountOwed(totalAmountOwed);
		    memberSummary.setAmountToBePaid(totalAmountToBePaid);
		    memberSummary.setShare(share);

		    return memberSummary;
		}

	  
	  
	  
	  
//	  private MemberSummaryDTO calculateMemberSummary(Users user,Groups group) {
//		  
//		  MemberSummaryDTO memberSummary = new MemberSummaryDTO();
//		  
//		  memberSummary.setMemberName(user.getName());
//		  
//		  
//		  double totalAmountPaid=0.0;
//		  double totalAmountOwed =0.0;
//		  double totalAmountToBePaid =0.0;
//		  
//		  
//		  //Calculate the total amount to be paid by the member
//		  
//		  for(Expenses expense :user.getExpenses()) {
//			  if(expense.getUser().getId()==user.getId()) {
//				  totalAmountPaid+=expense.getAmount();
//			  }
//		  }
//		  
//		  
//		  //Calculate the total amount owed by the member
//	 for(Users otherUser:group.getUsers()) {
//		 
//		 if(otherUser.getId()!=user.getId()) {
//			 
//			 double sharedExpense =getSharedExpense(user, otherUser, group);
//			 
//			 
//			 totalAmountOwed+=sharedExpense;
//		 }
//	 }
//	  //CAlculate totale amount to be paid by the member
//	 
//	 totalAmountToBePaid= totalAmountOwed - totalAmountPaid;
//	 
//	 memberSummary.setAmountPaid(totalAmountPaid);
//	 memberSummary.setAmountOwed(totalAmountOwed);
//	 memberSummary.setAmountToBePaid(totalAmountToBePaid);
//	 
//	 return memberSummary;
//	  }
	  
//	  private double getSharedExpense(Users user,Users otherUser,Groups group) {
//		  
//		  double sharedExpense=0.0;
//		  
//		  
//		  //Iterate through the expenses of both the users
//		  
//		  for(Expenses expense :user.getExpenses()) {
//			  
//			// Check if the other user is involved in the expense and it belongs to the group
//			  
//			  if(expense.getUser().getId() == otherUser.getId() && expense.getUser().getGroup().getId()==group.getId()) {
//				 sharedExpense+=expense.getAmount()/group.getUsers().size(); 
//			  }
//			  
//		  }
//		  
//		  return sharedExpense;
//		  
//		  
//	  }
//	  
}
