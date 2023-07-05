package br.com.up.invitecore.domains.dto.request;

import br.com.up.invitecore.domains.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	
	private String name;
	private String celPhone;
	private String uriImage;
	
	public User toEntity() {
		return new ModelMapper().map(this, User.class);
	}

}
