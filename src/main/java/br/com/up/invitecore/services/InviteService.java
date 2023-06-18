package br.com.up.invitecore.services;

import br.com.up.invitecore.domains.Invite;
import br.com.up.invitecore.domains.InviteContact;
import br.com.up.invitecore.domains.id.InviteContactId;
import br.com.up.invitecore.dto.InviteDTO;
import br.com.up.invitecore.enumeration.StatusInvite;
import br.com.up.invitecore.exceptions.NotFoundException;
import br.com.up.invitecore.repositories.InviteContactRepository;
import br.com.up.invitecore.repositories.InviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InviteService {

	@Autowired
	private InviteRepository inviteRepository;

	@Autowired
	private InviteContactRepository inviteContactRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private EventService eventService;

	public Invite create(InviteDTO inviteDTO) {
		var user = userService.find(inviteDTO.getIdUser());
		var event = eventService.find(inviteDTO.getEventDTO().getId());

		var inviteEntity = inviteDTO.toEntity();
		inviteEntity.setUser(user);
		inviteEntity.setEvent(event);
		inviteEntity.setDateTime(LocalDateTime.now().withSecond(0).withNano(0));

		var invitePersist = inviteRepository.save(inviteEntity);
		createInviteContact(inviteDTO, invitePersist);

		return invitePersist;
	}

	private void createInviteContact(InviteDTO inviteDTO, Invite invitePersist) {
		List<InviteContact> inviteContacts = inviteDTO.getContacts().stream()
				.map(contact -> {
					var contactEntity = contactService.find(contact);
					return InviteContact.builder()
						.statusInvite(StatusInvite.PENDING)
						.id(InviteContactId.builder()
								.invite(invitePersist)
								.contact(contactEntity)
								.build())
						.build();
				})
				.collect(Collectors.toList());

		inviteContactRepository.saveAll(inviteContacts);
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
