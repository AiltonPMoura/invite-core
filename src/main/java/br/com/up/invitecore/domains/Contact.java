package br.com.up.invitecore.domains;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.up.invitecore.domains.id.UserContactId;
import br.com.up.invitecore.dto.ContactDTO;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_CONTACT")
public class Contact {
	
	@EmbeddedId
	private UserContactId id;

	@Id
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "URI_IMAGE")
	private String uriImage;
	
	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private User user;
	
	public ContactDTO toDTO() {
		var contact = new ModelMapper().map(this, ContactDTO.class);
		contact.setCelPhone(this.getId().getCelPhone());
		return contact;
	}
	
	public static List<ContactDTO> toListDTO(List<Contact> contacts) {
		return contacts.stream()
				.map(Contact::toDTO)
				.collect(Collectors.toList());
	}
	
}
