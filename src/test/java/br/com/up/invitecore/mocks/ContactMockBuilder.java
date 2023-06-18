package br.com.up.invitecore.mocks;

import br.com.up.invitecore.domains.Contact;
import br.com.up.invitecore.domains.id.UserContactId;
import br.com.up.invitecore.dto.ContactDTO;

import static br.com.up.invitecore.mocks.UserMockBuilder.getUserEntity;

public class ContactMockBuilder {
	
	private ContactMockBuilder() {}
		
	public static Contact getContactEntity() {
		var userEntity = getUserEntity();
		userEntity.setId(1L);

		return Contact.builder()
				.name("Test")
				.uriImage("http://teste.png")
				.id(UserContactId.builder()
						.user(userEntity)
						.celPhone(getContactDTO().getCelPhone())
						.build())
				.build();
	}
	
	public static ContactDTO getContactDTO() {
		return ContactDTO.builder()
				.idUser(1L)
				.name("Test")
				.celPhone("+5511922223333")
				.uriImage("http://teste.png")
				.build();
	}

}
