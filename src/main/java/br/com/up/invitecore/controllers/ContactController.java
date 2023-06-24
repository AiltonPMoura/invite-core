package br.com.up.invitecore.controllers;

import br.com.up.invitecore.dto.ContactDTO;
import br.com.up.invitecore.services.ContactService;
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

import static br.com.up.invitecore.domains.Contact.toListDTO;

@RestController
@RequestMapping("/contact")
@Tag(name = "Contact", description = "Endpoint for managing Contact")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@Operation(summary = "Create", description = "Create Contact",
			tags = {"Contact"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "201",
						content = @Content(schema = @Schema(implementation = ContactDTO.class))),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<ContactDTO> create(@RequestBody ContactDTO contact) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(contactService.create(contact).toDTO());
	}

	@Operation(summary = "Find", description = "Find Contact By CelPhone And UserId",
			tags = {"Contact"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(schema = @Schema(implementation = ContactDTO.class))),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "No content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@GetMapping(value = "/find",
			consumes = "application/json", produces = "application/json")
	public ResponseEntity<ContactDTO> find(@RequestBody ContactDTO contact) {
		return ResponseEntity.ok(contactService.find(contact).toDTO());
	}

	@Operation(summary = "Find All", description = "Find All Contacts By UserId",
			tags = {"Contact"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(
							mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = ContactDTO.class)))
					),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "No content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@GetMapping(value = "/{idUser}", produces = "application/json")
	public ResponseEntity<List<ContactDTO>> findAll(@PathVariable Long idUser) {
		return ResponseEntity.ok(toListDTO(contactService.findAllByUserId(idUser)));
	}

	@Operation(summary = "Update", description = "Update Contact",
			tags = {"Contact"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(schema = @Schema(implementation = ContactDTO.class))),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@PutMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<ContactDTO> update(@RequestBody ContactDTO contact) {
		return ResponseEntity.ok(contactService.update(contact).toDTO());
	}
	
}
