package br.com.up.invitecore.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import br.com.up.invitecore.domains.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private Long id;
	private String name;
	private String celPhone;
	private String uriImage;
	
	public User toEntity() {
		return new ModelMapper().map(this, User.class);
	}

}
