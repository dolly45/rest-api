package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.User;
import com.test.repository.UserRepository;

@RestController
@RequestMapping("test")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	@GetMapping("/getUser")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	@PutMapping("/user/{id}")
	public User updateUser(@RequestBody User user,@PathVariable("id") int id) {
		//go to repo and fetch existing user based on id
		User u = userRepository.getOne(id);//existing User
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		//save u in repo
		return userRepository.save(u);
		
	}
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		//go to repo and delete based on id
		userRepository.deleteById(id);
	}
	
}
