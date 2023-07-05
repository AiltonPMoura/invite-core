package br.com.up.invitecore.domains;

import br.com.up.invitecore.domains.id.UserContactId;
import br.com.up.invitecore.domains.dto.response.ContactResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CONTACT")
public class Contact {
	
	@EmbeddedId
	private UserContactId id;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "URI_IMAGE")
	private String uriImage;

	public ContactResponse toResponse() {
		var contact = new ModelMapper().map(this, ContactResponse.class);
		contact.setCelPhone(this.getId().getCelPhone());
		contact.setIdUser(this.getId().getUser().getId());
		return contact;
	}
	
	public static List<ContactResponse> toListResponse(List<Contact> contacts) {
		if (contacts != null)
			return contacts.stream()
					.map(Contact::toResponse)
					.collect(Collectors.toList());
		else return null;
	}
	
}
