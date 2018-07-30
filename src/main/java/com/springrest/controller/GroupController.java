package com.springrest.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.exceptionhandling.GeneralException;
import com.springrest.exceptionhandling.GroupNotFoundException;
import com.springrest.model.Group;
import com.springrest.model.StatusType;
import com.springrest.service.GroupService;

@RestController
@RequestMapping("group")
public class GroupController {

	public static final Logger logger = LoggerFactory.getLogger(GroupController.class);

	@Autowired
	private GroupService groupService;

	@Autowired
	private MessageSource msgSource;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/addGroup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createGroup(@RequestBody Group group) throws GeneralException {
		logger.info("Group Name" + group.getGroupName());
		Group checkGroupName = groupService.isGroupExist(group);
		if (checkGroupName != null) {
			logger.error("Unable to create group. A group with name {} already exist", group.getGroupName());
			throw new GeneralException(
					"Unable to create group. A group with name" + group.getGroupName() + " already exist");
		}
		else {
			Group addGroup = groupService.addGroup(group);
			if (addGroup!=null)
				return new ResponseEntity<>(msgSource.getMessage("msg.created", null, Locale.US), HttpStatus.OK);
			else
				return new ResponseEntity<>(msgSource.getMessage("msg.error", null, Locale.US), HttpStatus.CONFLICT);
		}
		
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/getGroups", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getGroups(Group group, Model model) {
		List<Group> listGroups = groupService.listGroups();
		if (listGroups.isEmpty()) {
			return new ResponseEntity<>(new StatusType("No Groups Found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Group>>(listGroups, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/deleteGroup/{groupId}")
	public ResponseEntity<?> removeGroup(@PathVariable Integer groupId) throws GroupNotFoundException {

		Group group = groupService.findById(groupId);
		boolean flag = groupService.deleteGroup(group);
		if (flag)
			return new ResponseEntity<>(msgSource.getMessage("msg.deleted", null, Locale.US), HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<Group>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/updateGroup/{groupId}")
	public ResponseEntity<?> updateUser(@PathVariable int groupId, @RequestBody Group group)
			throws GroupNotFoundException {
		logger.info("Updating User with groupId {}", groupId);
		Group currentGroup = groupService.findById(groupId);
		if (currentGroup == null) {
			logger.error("Unable to update. Group with groupId {} not found.", groupId);
			return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
		}
		currentGroup.setGroupName(group.getGroupName());
		groupService.updateGroup(currentGroup);
		return new ResponseEntity<>(msgSource.getMessage("msg.updated", null, Locale.US), HttpStatus.OK);
	}

}
