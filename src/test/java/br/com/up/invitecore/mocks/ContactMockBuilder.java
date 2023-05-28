package br.com.up.invitecore.mocks;

import br.com.up.invitecore.domains.Contact;
import br.com.up.invitecore.domains.id.UserContactId;
import br.com.up.invitecore.dto.ContactDTO;
import static br.com.up.invitecore.mocks.UserMockBuilder.getUserEntity;

public class ContactMockBuilder {
	
	private ContactMockBuilder() {};
		
	public static Contact getContactEntity() {
		return ContactDTO.builder()
				.name("Test")
				.uriImage("http://teste.png")
				.build()
				.toEntity(UserContactId.builder()
							.celPhone("+5511922223333")
							.build(),
						getUserEntity()
				);
	}
	
	public static ContactDTO getContactDTO() {
		return ContactDTO.builder()
				.name("Test")
				.celPhone("+5511922223333")
				.uriImage("http://teste.png")
				.build();
	}

}
