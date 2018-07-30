package com.springrest.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springrest.exceptionhandling.UserNotFoundException;
import com.springrest.jparepository.UserRepository;
import com.springrest.model.Users;

@Service
public class UserServiceImpl implements UserService {

	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Users> listUsers() {
		return userRepository.findAll();
	}

	@Override
	public Users findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public Users addUser(Users user)   {
		//user.setCreatedDate(new Date());
		user.setStatus(true);
		logger.debug("Encypted Password : " + passwordEncoder.encode(user.getPassword()));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			userRepository.save(user);
		}catch(Exception ex){	
		
		}
		return userRepository.save(user);
	}

	@Override
	public Users findById(Integer userId) throws UserNotFoundException {
		try {
			userRepository.findById(userId).get();
		} catch (NoSuchElementException ex) {
			throw new UserNotFoundException("User Not Found");
		}
		return userRepository.findById(userId).get();

	}

	@Override
	public boolean deleteUser(Users user) {
		userRepository.delete(user);
		return true;

	}

	@Override
	public Users updateUser(Users user) {
		//user.setUpdatedDate(new Date());
		return userRepository.save(user);
	}

}
