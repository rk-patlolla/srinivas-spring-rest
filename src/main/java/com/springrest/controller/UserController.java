package com.springrest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.exceptionhandling.UserNotFoundException;
import com.springrest.model.AuthToken;
import com.springrest.model.StatusType;
import com.springrest.model.Users;
import com.springrest.security.JwtTokenUtil;
import com.springrest.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;
	
	

	@PostMapping(value = "/login")
	public ResponseEntity<AuthToken> register(@RequestBody Users user) throws AuthenticationException {
		logger.info("UserName:" + user.getUserName());
		logger.info("Password:" + user.getPassword());

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final Users users = userService.findByUserName(user.getUserName());
		final String token = jwtTokenUtil.generateToken(users);
		return ResponseEntity.ok(new AuthToken(token));
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	@GetMapping(value = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Users>> getUsers(Model model) {
		List<Users> listUsers = userService.listUsers();
		if (listUsers.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Users>>(listUsers, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/addUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUser(@RequestBody Users user)   {
		logger.info("User Name" + user.getUserName());
		userService.addUser(user);
		return new ResponseEntity<>(new StatusType("User created Successfully"), HttpStatus.OK);

	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/deleteUser/{userId}")
	public ResponseEntity<?> removeUser(@PathVariable Integer userId) throws UserNotFoundException {
		Users user = userService.findById(userId);
		boolean flag = userService.deleteUser(user);
		if (flag)
			return new ResponseEntity<>(new StatusType("User Deleted Successfully"),HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<>(new StatusType("Operation Failed"),HttpStatus.NO_CONTENT);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/updateUser/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody Users user)
			throws UserNotFoundException {
		logger.info("Updating User with userId {}", userId);
		Users currentUser = userService.findById(userId);
		currentUser.setUserName(user.getUserName());
		currentUser.setUserEmail(user.getUserEmail());
		currentUser.setUserMobile(user.getUserMobile());
		userService.updateUser(currentUser);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
}
