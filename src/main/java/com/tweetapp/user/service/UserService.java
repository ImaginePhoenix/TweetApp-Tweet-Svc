package com.tweetapp.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.user.entity.User;
import com.tweetapp.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean register(User user) {
		try {
			user.setStatus(1);
			log.info("Saving User");
			userRepository.save(user);
			log.info("User saved successfully");
			return true;
		} catch (Exception e) {
			log.error("Exception occurred: {}", e);
			return false;
		}
	}

	public boolean login(String email, String password) {

		try {
			User user = userRepository.findById(email).get();

			if (userRepository.existsById(email)) {

				if (user.getPassword().equals(password)) {
					user.setStatus(0);
					userRepository.save(user);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public boolean logOut(String email) {


		List<User> users =  StreamSupport.stream(userRepository.findAll().spliterator(), false)
					 					   .filter(u -> u.getEmail().equals(email))
										   .collect(Collectors.toList());
		
		if(users.size() == 1){
			User targetUser = users.get(0);
			targetUser.setStatus(1);
			userRepository.save(targetUser);
			return true;
		}

		return false;
	}

	public boolean forgotPassword(String email, String password) {

		if (userRepository.existsById(email)) {
			User user = userRepository.findById(email).get();
			user.setPassword(password);
			user.setConfirmPassword(password);
			userRepository.save(user);
			return true;
		}
		return false;
	}

	public List<String> getAllUsers() {

		List<String> users = new ArrayList<>();
		for (User user : userRepository.findAll()) {
			users.add(user.getEmail());
		}
		return users;
	}

	public int getStatus(String email) {

		return userRepository.findById(email).get().getStatus();

	}
}
