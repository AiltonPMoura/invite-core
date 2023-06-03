package br.com.up.invitecore.mocks;

import static br.com.up.invitecore.mocks.UserMockBuilder.getUserEntity;

import br.com.up.invitecore.domains.Contact;
import br.com.up.invitecore.dto.ContactDTO;

public class ContactMockBuilder {
	
	private ContactMockBuilder() {};
		
	public static Contact getContactEntity() {
		var user = getUserEntity();
		user.setId(1L);
		
		return ContactDTO.builder()
				.name("Test")
				.celPhone("+5511922223333")
				.uriImage("http://teste.png")
				.build()
				.toEntity(user);
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
