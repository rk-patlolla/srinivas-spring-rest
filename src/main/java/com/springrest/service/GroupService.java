package com.springrest.service;

import java.util.List;

import com.springrest.exceptionhandling.GroupNotFoundException;
import com.springrest.model.Group;

public interface GroupService {
	
	public Group addGroup(Group group);
	public Group isGroupExist(Group group);
	public List<Group> listGroups();
	public boolean deleteGroup(Group group);
	public Group updateGroup (Group groupObj);
	public Group findById(Integer groupId) throws GroupNotFoundException;

}
