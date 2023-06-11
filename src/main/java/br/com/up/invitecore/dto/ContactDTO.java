package br.com.up.invitecore.dto;

import org.modelmapper.ModelMapper;

import br.com.up.invitecore.domains.Contact;
import br.com.up.invitecore.domains.User;
import br.com.up.invitecore.domains.id.UserContactId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {

	private String name;
	private String celPhone;
	private String uriImage;
	private Integer statusInvite;
	private Long idUser;
	
	public Contact toEntity() {
		return new ModelMapper().map(this, Contact.class);
	}

}
