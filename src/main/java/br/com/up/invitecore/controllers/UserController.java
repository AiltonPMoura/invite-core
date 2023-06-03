package br.com.up.invitecore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.up.invitecore.dto.UserDTO;
import br.com.up.invitecore.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO user) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.save(user).toDTO());
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<UserDTO> find(@PathVariable Long id) {
		return ResponseEntity.ok(userService.find(id).toDTO());
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO user, @PathVariable Long id) {
		return ResponseEntity.ok(userService.update(user, id).toDTO());
	}

}
