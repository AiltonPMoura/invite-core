package br.com.up.invitecore.controllers;

import static br.com.up.invitecore.domains.Event.toListDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.up.invitecore.dto.EventDTO;
import br.com.up.invitecore.services.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@PostMapping("/create")
	public ResponseEntity<EventDTO> create(@RequestBody EventDTO event) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(eventService.create(event).toDTO());
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<EventDTO> find(@PathVariable Long id) {
		return ResponseEntity.ok(eventService.find(id).toDTO());
	}
	
	@GetMapping("/findAll/{idUser}")
	public ResponseEntity<List<EventDTO>> findAll(@PathVariable Long idUser) {
		return ResponseEntity.ok(toListDTO(eventService.findAll(idUser)));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<EventDTO> update(@RequestBody EventDTO event,
			@PathVariable Long id) {
		return ResponseEntity.ok(eventService.update(event, id).toDTO());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		eventService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
