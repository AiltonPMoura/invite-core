package br.com.up.invitecore.domains.dto.request;

import br.com.up.invitecore.domains.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {

	private String name;
	private String celPhone;
	private String uriImage;
	private Integer statusInvite;
	private Long idUser;
	
	public Contact toEntity() {
		return new ModelMapper().map(this, Contact.class);
	}

}
