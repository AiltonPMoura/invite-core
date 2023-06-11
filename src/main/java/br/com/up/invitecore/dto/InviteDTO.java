package br.com.up.invitecore.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.up.invitecore.domains.Event;
import br.com.up.invitecore.domains.Invite;
import br.com.up.invitecore.domains.User;
import br.com.up.invitecore.enumeration.TypeInvite;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InviteDTO {

	private Long id;
	private LocalDateTime dateTime;
	private String location;
	private Integer type;
	private Long idUser;
	private Long idEvent;
	private List<String> celPhoneContacts;

	public Invite toEntity(User user, Event event, TypeInvite type) {
		Invite invite = new ModelMapper().map(this, Invite.class);

		invite.setUser(user);
		invite.setEvent(event);
		invite.setType(type);

		return invite;
	}

}
