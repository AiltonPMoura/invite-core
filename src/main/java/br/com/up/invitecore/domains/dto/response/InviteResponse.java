package br.com.up.invitecore.domains.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InviteResponse {

	private Long id;
	private String location;
	private Integer type;
	private Long idUser;
	private EventResponse eventResponse;
	private List<ContactResponse> contacts;

}
