package com.rentalProduct.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentalProduct.Entities.User;
import com.rentalProduct.Services.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("save")
	public ResponseEntity<String> saveUser(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.save(user));
	}
	
	@GetMapping("info")
	public ResponseEntity<Object> saveUser(@RequestParam String name){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getUser(name));
	}
	
}
