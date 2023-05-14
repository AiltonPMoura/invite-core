package br.com.up.invitecore.dto;

import org.modelmapper.ModelMapper;

import br.com.up.invitecore.domains.Contact;
import br.com.up.invitecore.domains.User;
import br.com.up.invitecore.domains.id.UserContactId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDTO {

	private String name;
	private String celPhone;
	private String uriImage;
	
	public Contact toEntity(UserContactId id, User user) {
		var contact = new ModelMapper().map(this, Contact.class);
		contact.setId(id);
		contact.setUser(user);
		return contact;
	}

}
