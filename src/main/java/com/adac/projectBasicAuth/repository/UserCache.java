package com.adac.projectBasicAuth.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.adac.projectBasicAuth.model.User;

import jakarta.annotation.PostConstruct;

@Component
public class UserCache {

	private Map<String, User> usersCache = new HashMap<>();

	@PostConstruct
	private void init() {
		User userAndres = new User("Andres", 25, "Calle 1");
		User userCarlos = new User("Carlos", 19, "Avenida 5");
		User userDiana = new User("Diana", 21, "Transversal 8");
		addUser(userAndres);
		addUser(userDiana);
		addUser(userCarlos);
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		for (String name : usersCache.keySet()) {
			users.add(usersCache.get(name));
		}
		return users;
	}

	public void addUser(User user) {
		String name = user.name().trim();
		usersCache.put(name, user);
	}

	public boolean existUser(String name) {
		return usersCache.containsKey(name.trim());
	}

	public void deleteUser(String name) {
		usersCache.remove(name.trim());
	}

}
