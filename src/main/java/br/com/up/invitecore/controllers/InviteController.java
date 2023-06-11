package br.com.up.invitecore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.up.invitecore.dto.InviteDTO;
import br.com.up.invitecore.services.InviteService;

@RestController
@RequestMapping("/invite")
public class InviteController {

	@Autowired
	private InviteService inviteService;
	
	@PostMapping("/create")
	public ResponseEntity<InviteDTO> create(@RequestBody InviteDTO invite) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(inviteService.create(invite).toDTO());
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<InviteDTO> find(@PathVariable Long id) {
		return ResponseEntity.ok(inviteService.find(id).toDTO());
	}

	@PutMapping("/update/{id}")
	public InviteDTO update(@RequestBody InviteDTO invite, @PathVariable Long id) {
		return inviteService.update(invite, id).toDTO();
	}
	
}
