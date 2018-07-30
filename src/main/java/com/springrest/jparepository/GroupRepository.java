package com.springrest.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.model.Group;

public interface GroupRepository extends JpaRepository<Group, Integer>{
	
	public Group findByGroupName(String groupName);

}
