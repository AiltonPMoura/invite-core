package br.com.up.invitecore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User", description = "Endpoint for managing User")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Operation(summary = "Create User", description = "Create User",
		tags = "User",
		responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
				content = @Content(schema = @Schema(implementation = UserDTO.class))
			),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
		}
	)
	@PostMapping("/create")
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO user) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.save(user).toDTO());
	}

	@Operation(summary = "Find User", description = "Find User",
			tags = "User",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200",
							content = @Content(schema = @Schema(implementation = UserDTO.class))
					),
					@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@GetMapping("/find/{id}")
	public ResponseEntity<UserDTO> find(@PathVariable Long id) {
		return ResponseEntity.ok(userService.find(id).toDTO());
	}

	@Operation(summary = "Update User", description = "Update User",
			tags = "User",
			responses = {
					@ApiResponse(description = "Sucess", responseCode = "200",
							content = @Content(schema = @Schema(implementation = UserDTO.class))
					),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO user, @PathVariable Long id) {
		return ResponseEntity.ok(userService.update(user, id).toDTO());
	}

}
