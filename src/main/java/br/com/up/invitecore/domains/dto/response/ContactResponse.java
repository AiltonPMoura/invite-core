package br.com.up.invitecore.domains.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponse {

	private String name;
	private String celPhone;
	private String uriImage;
	private Integer statusInvite;
	private Long idUser;
	
}
