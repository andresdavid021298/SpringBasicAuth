package com.adac.projectBasicAuth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adac.projectBasicAuth.model.User;
import com.adac.projectBasicAuth.request.NewUserRequest;
import com.adac.projectBasicAuth.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody @Valid NewUserRequest request) {
		String messageResponse = userService.addNewUser(request);
		return ResponseEntity.ok(messageResponse);
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<String> deleteUser(@PathVariable(required = true) String name) {
		String messageResponse = userService.deleteUser(name);
		return ResponseEntity.ok(messageResponse);
	}

}
