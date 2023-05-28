package br.com.up.invitecore.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.up.invitecore.domains.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
	
	private Long id;
	private String name;
	private String celPhone;
	private String uriImage;
	private List<ContactDTO> contacts;
	
	public User toEntity() {
		return new ModelMapper().map(this, User.class);
	}

}
