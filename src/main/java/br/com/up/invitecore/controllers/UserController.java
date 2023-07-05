package br.com.up.invitecore.controllers;

import br.com.up.invitecore.domains.dto.request.UserRequest;
import br.com.up.invitecore.domains.dto.response.UserResponse;
import br.com.up.invitecore.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Endpoint for managing User")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Operation(summary = "Create", description = "Create User",
			tags = {"User"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "201",
					content = @Content(schema = @Schema(implementation = UserResponse.class))
				),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.save(userRequest).toResponse());
	}

	@Operation(summary = "Find ", description = "Find User By Id",
			tags = {"User"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "200",
						content = @Content(schema = @Schema(implementation = UserResponse.class))
				),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<UserResponse> find(@PathVariable Long id) {
		return ResponseEntity.ok(userService.find(id).toResponse());
	}

	@Operation(summary = "Update", description = "Update User By Id",
			tags = {"User"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "200",
						content = @Content(schema = @Schema(implementation = UserResponse.class))
				),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@PutMapping(value = "/{id}",
			consumes = "application/json",produces = "application/json")
	public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
		return ResponseEntity.ok(userService.update(id, userRequest).toResponse());
	}

}
