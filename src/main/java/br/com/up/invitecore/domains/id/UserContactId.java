package br.com.up.invitecore.domains.id;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Embeddable
public class UserContactId {

	@Column(name = "CEL_PHONE")
	private String celPhone;
	
	@Column(name = "ID_USER")
	private Long idUser;
	
}
