package br.com.up.invitecore.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.up.invitecore.domains.Contact;
import br.com.up.invitecore.domains.Event;
import br.com.up.invitecore.domains.Invite;
import br.com.up.invitecore.domains.User;
import lombok.Data;

@Data
public class UserDTO {
	
	private Long id;
	private String name;
	private String celPhone;
	private String uriImage;
	private List<Event> events;
	private List<Invite> invites;
	private List<Contact> contacts;
	
	public User toEntity() {
		return new ModelMapper().map(this, User.class);
	}

}
