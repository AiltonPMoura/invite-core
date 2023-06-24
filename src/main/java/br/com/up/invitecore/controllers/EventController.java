package br.com.up.invitecore.controllers;

import br.com.up.invitecore.dto.EventDTO;
import br.com.up.invitecore.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.up.invitecore.domains.Event.toListDTO;

@RestController
@RequestMapping("/event")
@Tag(name = "Event", description = "Endpoint for managing Event")
public class EventController {
	
	@Autowired
	private EventService eventService;

	@Operation(summary = "Create", description = "Create Event",
			tags = {"Event"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "201",
					content = @Content(schema = @Schema(implementation = EventDTO.class))),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<EventDTO> create(@RequestBody EventDTO event) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(eventService.create(event).toDTO());
	}

	@Operation(summary = "Find", description = "Find Event By Id",
			tags = {"Event"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "200",
						content = @Content(schema = @Schema(implementation = EventDTO.class))),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<EventDTO> find(@PathVariable Long id) {
		return ResponseEntity.ok(eventService.find(id).toDTO());
	}

	@Operation(summary = "Find All", description = "Find All By User Id",
			tags = {"Event"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(
							mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = EventDTO.class)))
				),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Bad Reequest", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@GetMapping(value = "/user/{idUser}", produces = "application/json")
	public ResponseEntity<List<EventDTO>> findAll(@PathVariable Long idUser) {
		return ResponseEntity.ok(toListDTO(eventService.findAll(idUser)));
	}

	@Operation(summary = "Update", description = "Update Event By Id",
			tags = {"Event"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(schema = @Schema(implementation = EventDTO.class))),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Bad Reequest", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@PutMapping(value = "/{id}",
			consumes = "application/json", produces = "application/json")
	public ResponseEntity<EventDTO> update(@RequestBody EventDTO event) {
		return ResponseEntity.ok(eventService.update(event).toDTO());
	}

	@Operation(summary = "Delete", description = "Delete Event By Id",
			tags = {"Event"},
			responses = {
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Bad Reequest", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		eventService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
