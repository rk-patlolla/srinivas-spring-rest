package com.springrest.service;

import java.util.List;

import com.springrest.exceptionhandling.UserNotFoundException;
import com.springrest.model.Users;

public interface UserService {

	public List<Users> listUsers();
	public Users findByUserName(String userName);
	public Users addUser(Users user);
	public Users findById(Integer userId) throws UserNotFoundException;
	public boolean deleteUser(Users user);
	public Users updateUser (Users user);

	
}
