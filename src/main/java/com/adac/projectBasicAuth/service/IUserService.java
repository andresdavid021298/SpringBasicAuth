package com.adac.projectBasicAuth.service;

import java.util.List;

import com.adac.projectBasicAuth.model.User;
import com.adac.projectBasicAuth.request.NewUserRequest;

public interface IUserService {
	
	String addNewUser(NewUserRequest request);
	String deleteUser(String name);
	List<User> getAllUsers();
	
}
