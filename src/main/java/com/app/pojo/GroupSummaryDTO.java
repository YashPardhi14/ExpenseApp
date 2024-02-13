package com.app.pojo;

import java.util.List;

public class GroupSummaryDTO {

	  private String groupName;
	  
	  
	  public Double getTotalExpenseOfGroup() {
		return totalExpenseOfGroup;
	}
	public void setTotalExpenseOfGroup(Double totalExpenseOfGroup) {
		this.totalExpenseOfGroup = totalExpenseOfGroup;
	}
	private Double totalExpenseOfGroup;
	  
	    private List<MemberSummaryDTO> members;
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public List<MemberSummaryDTO> getMembers() {
			return members;
		}
		public void setMembers(List<MemberSummaryDTO> members) {
			this.members = members;
		}

	    // Getters and setters
	    
	    
	    
	}

	