package com.tweetapp.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.user.entity.LoginUser;
import com.tweetapp.user.entity.User;
import com.tweetapp.user.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1.0/tweets")
public class UsersController {

	@Autowired
	private UserService userService;

	Logger logger = LoggerFactory.getLogger(UsersController.class);

	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody User user) {

		if (userService.register(user)) {
			logger.info("Registration Successful - " + user.getEmail());
			return new ResponseEntity<String>("Sucess", HttpStatus.OK);
		} else {
			logger.info("Registration Failed - " + user.getEmail());
			return new ResponseEntity<String>("Failed", HttpStatus.OK);
		}

	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginUser user) {

		if (userService.login(user.getEmail(), user.getPassword())) {
			logger.info("Login successful - " + user.getEmail());
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			logger.info("Login failed - " + user.getEmail());
			return new ResponseEntity<String>("Failed", HttpStatus.OK);
		}

	}

	@PostMapping("/forgot")
	public ResponseEntity<String> forgotPassword(@RequestBody LoginUser user) {
		User user1 = new User();
		user1.setEmail(user.getEmail());
		user1.setPassword(user.getPassword());

		if (userService.forgotPassword(user.getEmail(), user.getPassword())) {
			logger.info("Password reset successfully - " + user.getEmail());
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			logger.info("Password reset failed - " + user.getEmail());
			return new ResponseEntity<String>("Failed", HttpStatus.OK);
		}

	}

	@GetMapping("/users/all")
	public List<String> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestBody LoginUser user) {

		if (userService.logOut(user.getEmail())) {
			logger.info("Logged out successfully - " + user.getEmail());
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} else {
			logger.info("Logout failed - " + user.getEmail());
			return new ResponseEntity<String>("Failed", HttpStatus.OK);
		}

	}

}
