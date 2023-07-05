package br.com.up.invitecore.services;

import br.com.up.invitecore.domains.Invite;
import br.com.up.invitecore.domains.InviteContact;
import br.com.up.invitecore.domains.dto.request.InviteRequest;
import br.com.up.invitecore.domains.id.InviteContactId;
import br.com.up.invitecore.enumeration.StatusInvite;
import br.com.up.invitecore.enumeration.TypeInvite;
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

	public Invite create(InviteRequest inviteRequest) {
		var user = userService.find(inviteRequest.getIdUser());
		var event = eventService.find(inviteRequest.getIdEvent());

		var inviteEntity = inviteRequest.toEntity();
		inviteEntity.setUser(user);
		inviteEntity.setEvent(event);
		inviteEntity.setDateTime(LocalDateTime.now().withSecond(0).withNano(0));

		var invitePersist = inviteRepository.save(inviteEntity);
		createInviteContact(inviteRequest, invitePersist);

		return invitePersist;
	}

	private void createInviteContact(InviteRequest inviteRequest, Invite invitePersist) {
		List<InviteContact> inviteContacts = inviteRequest.getCelPhoneContacts().stream()
				.map(celPhone -> {
					var contactEntity = contactService.find(inviteRequest.getIdUser(), celPhone);
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

	public Invite update(Long id, InviteRequest inviteRequest) {
		var inviteEntity = find(id);
		inviteEntity.setType(inviteRequest.getType() == 0 ? TypeInvite.FREE : TypeInvite.PAY);
		inviteEntity.setLocation(inviteRequest.getLocation());

		return inviteRepository.save(inviteRequest.toEntity());
	}
}
