package com.springrest.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	Users findByUserName(String username);
}
