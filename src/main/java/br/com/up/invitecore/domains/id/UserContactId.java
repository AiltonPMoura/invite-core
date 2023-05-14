package br.com.up.invitecore.domains.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserContactId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CEL_PHONE")
	private String celPhone;
	
	@Column(name = "ID_USER")
	private Long idUser;
	
}
