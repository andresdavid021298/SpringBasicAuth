package com.adac.projectBasicAuth.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adac.projectBasicAuth.model.User;
import com.adac.projectBasicAuth.repository.UserCache;
import com.adac.projectBasicAuth.request.NewUserRequest;
import com.adac.projectBasicAuth.service.IUserService;

@Service
public class UserService implements IUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	private UserCache userCache;

	@Override
	public String addNewUser(NewUserRequest request) {
		String name = request.getName().trim();
		Integer age = request.getAge();
		String address = request.getAddress().trim();
		if (!userCache.existUser(name)) {
			User user = new User(name, age, address);
			userCache.addUser(user);
			return "User created";
		} else {
			return String.format("User with name %s already exist", name);
		}
	}

	@Override
	public String deleteUser(String name) {
		LOGGER.info("** DELETING USER **");
		if (userCache.existUser(name)) {
			LOGGER.error("USER {} NOT EXIST", name);
			return String.format("Error, user %s not exist", name);
		} else {
			userCache.deleteUser(name);
			return String.format("User %s deleted successful", name);
		}
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = userCache.getUsers();
		LOGGER.info("** NUMBER OF USERS: {}**", allUsers.size());
		return allUsers;
	}

	@Autowired
	public void setUserCache(UserCache userCache) {
		this.userCache = userCache;
	}

}
