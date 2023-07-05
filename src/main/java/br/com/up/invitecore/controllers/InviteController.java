package br.com.up.invitecore.controllers;

import br.com.up.invitecore.domains.dto.request.InviteRequest;
import br.com.up.invitecore.domains.dto.response.InviteResponse;
import br.com.up.invitecore.services.InviteService;
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
@RequestMapping("/invite")
@Tag(name = "Invite", description = "EndPoint For Managing Invite")
public class InviteController {

	@Autowired
	private InviteService inviteService;

	@Operation(summary = "Create", description = "Create Invite",
			tags = {"Invite"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "201",
						content = @Content(schema = @Schema(implementation = InviteResponse.class))),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<InviteResponse> create(@RequestBody InviteRequest inviteRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(inviteService.create(inviteRequest).toResponse());
	}

	@Operation(summary = "Find", description = "Find Invite By Id",
			tags = {"Invite"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(schema = @Schema(implementation = InviteResponse.class))),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<InviteResponse> find(@PathVariable Long id) {
		return ResponseEntity.ok(inviteService.find(id).toResponse());
	}

	@Operation(summary = "Update", description = "Update Invite By Id",
			tags = {"Invite"},
			responses = {
				@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(schema = @Schema(implementation = InviteResponse.class))),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@PutMapping(value = "/{id}",
			consumes = "application/json", produces = "application/json")
	public InviteResponse update(@PathVariable Long id, @RequestBody InviteRequest inviteRequest) {
		return inviteService.update(id, inviteRequest).toResponse();
	}
	
}
