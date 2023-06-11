package br.com.up.invitecore.services;

import java.util.List;
import java.util.stream.Collectors;

import br.com.up.invitecore.domains.InviteContact;
import br.com.up.invitecore.domains.id.InviteContactId;
import br.com.up.invitecore.enumeration.StatusInvite;
import br.com.up.invitecore.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.up.invitecore.domains.Contact;
import br.com.up.invitecore.domains.Invite;
import br.com.up.invitecore.dto.InviteDTO;
import br.com.up.invitecore.enumeration.TypeInvite;
import br.com.up.invitecore.repositories.InviteRepository;

@Service
public class InviteService {

	@Autowired
	private InviteRepository inviteRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private EventService eventService;

	public Invite create(InviteDTO invite) {
		var user = userService.find(invite.getIdUser());
		var event = eventService.find(invite.getEvent().getId());
		var typeInvite = invite.getType() == 0 ? TypeInvite.FREE : TypeInvite.PAY;

		var inviteEntity = invite.toEntity();
		inviteEntity.setUser(user);
		inviteEntity.setEvent(event);
		inviteEntity.setType(typeInvite);
		
		List<InviteContact> inviteContacts = invite.getContacts().stream()
				.map(contact -> contactService.find(contact.getCelPhone(), user.getId()))
				.map(contact -> InviteContact.builder()
						.statusInvite(StatusInvite.PENDING)
						.id(InviteContactId.builder()
								.invite(inviteEntity)
								.contact(contact)
								.build())
						.build())
				.collect(Collectors.toList());

		inviteEntity.setInviteContact(inviteContacts);

		return inviteRepository.save(inviteEntity);
	}

	public Invite find(Long id) {
		return inviteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("invite not found by id"));
	}

	public Invite update(InviteDTO invite, Long id) {
		var inviteEntity = find(id);
		invite.setId(inviteEntity.getId());

		return inviteRepository.save(invite.toEntity());
	}
}
