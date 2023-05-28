package br.com.up.invitecore.controllers;

import static br.com.up.invitecore.domains.Contact.toListDTO;

import java.util.List;

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

import br.com.up.invitecore.dto.ContactDTO;
import br.com.up.invitecore.services.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@PostMapping("/save/{idUser}")
	public ResponseEntity<ContactDTO> save(@RequestBody ContactDTO contact, @PathVariable Long idUser) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(contactService.save(contact, idUser).toDTO());
	}
	
	@GetMapping("/findById/{celPhone}/{idUser}")
	public ResponseEntity<ContactDTO> findById(@PathVariable String celPhone, @PathVariable Long idUser) {
		return ResponseEntity.ok(contactService.findById(celPhone, idUser).toDTO());
	}
	
	@GetMapping("/findAllByUserId/{idUser}")
	public ResponseEntity<List<ContactDTO>> findAllByUserId(@PathVariable Long idUser) {
		return ResponseEntity.ok(toListDTO(contactService.findAllByUserId(idUser)));
	}
	
	@PutMapping("/update/{celPhone}/{idUser}")
	public ResponseEntity<ContactDTO> update(@RequestBody ContactDTO contact, 
			@PathVariable String celPhone, @PathVariable Long idUser) {
		return ResponseEntity.ok(contactService.update(contact, celPhone, idUser).toDTO());
	}
	
}
