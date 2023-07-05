package br.com.up.invitecore.domains.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {

	private Long id;
	private String name;
	private String uriImage;
	private Long idUser;
	
}
