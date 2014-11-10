package com.jrmds.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jrmds.domain.User;
import com.jrmds.repository.RoleRepository;
import com.jrmds.repository.UserRepository;


@Service
@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public User create(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if(existingUser != null) {
			throw new RuntimeException("Record already exists");
		}
		
		user.getRole().setUser(user);
		return userRepository.save(user);
	}
	
	
	public User read(User user) {
		return user;
	}
	
	public List<User> readAll() {
		List<User> users = new ArrayList<User>();
		
		Result<User> results = userRepository.findAll();
		for(User r: results) {
			users.add(r);
		}
		
		
		return users;
		
	}
	
	public User update(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser == null) {
			return null;
		}
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.getRole().setRole(user.getRole().getRole());
		
		roleRepository.save(existingUser.getRole());
		return userRepository.save(existingUser);
	}
	
	public Boolean delete(User user) {
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if (existingUser == null) {
			return false;
		}
		
		userRepository.delete(existingUser);
		return true;
	}
	
}
