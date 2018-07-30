package com.springrest.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.exceptionhandling.GroupNotFoundException;
import com.springrest.jparepository.GroupRepository;
import com.springrest.model.Group;

@Service
public class GroupServiceImpl implements GroupService {

	public static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

	@Autowired
	private GroupRepository groupRepository;

	@Override
	public Group addGroup(Group group) {

		group.setCreatedDate(new Date());
		group.setStatus(true);
		return groupRepository.save(group);

	}

	@Override
	public List<Group> listGroups() {
		return groupRepository.findAll();
	}

	@Override
	public boolean deleteGroup(Group group) {
		groupRepository.delete(group);
		return true;
	}

	@Override
	public Group findById(Integer groupId) throws GroupNotFoundException {
		try {
			groupRepository.findById(groupId).get();
		} catch (NoSuchElementException ex) {
			throw new GroupNotFoundException("Group Not Found");
		}
		return groupRepository.findById(groupId).get();
	}

	@Override
	public Group updateGroup(Group group) {
		group.setUpdatedDate(new Date());
		return groupRepository.save(group);

	}

	@Override
	public Group isGroupExist(Group group) {
		return groupRepository.findByGroupName(group.getGroupName());
	}

}
