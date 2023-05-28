package br.com.up.invitecore.mocks;

import br.com.up.invitecore.domains.User;
import br.com.up.invitecore.dto.UserDTO;

public class UserMockBuilder {
	
	private UserMockBuilder() {};
	
	public static User getUserEntity() {
		return User.builder()
				.name("Test")
				.celPhone("+5511922223333")
				.uriImage("http://teste.png")
				.build();
	}
	
	public static UserDTO getUserDTO() {
		return UserDTO.builder()
				.name("Test")
				.celPhone("+5511922223333")
				.uriImage("http://teste.png")
				.build();
	}

}
