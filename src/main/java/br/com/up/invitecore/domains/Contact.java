package br.com.up.invitecore.domains;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.up.invitecore.domains.id.UserContactId;
import br.com.up.invitecore.dto.ContactDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	@ManyToOne
	@JoinColumn(name = "ID_USER", insertable = false, updatable = false)
	private User user;
	
	public ContactDTO toDTO() {
		var contact = new ModelMapper().map(this, ContactDTO.class);
		contact.setCelPhone(this.getId().getCelPhone());
		contact.setIdUser(this.getId().getIdUser());
		return contact;
	}
	
	public static List<ContactDTO> toListDTO(List<Contact> contacts) {
		if (contacts != null)
			return contacts.stream()
					.map(Contact::toDTO)
					.collect(Collectors.toList());
		else return null;
	}
	
}
