package br.com.up.invitecore.services;

import br.com.up.invitecore.domains.Contact;
import br.com.up.invitecore.domains.dto.request.ContactRequest;
import br.com.up.invitecore.domains.id.UserContactId;
import br.com.up.invitecore.exceptions.NotFoundException;
import br.com.up.invitecore.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private UserService userService;

	public Contact create(ContactRequest contactRequest) {
		var user = userService.find(contactRequest.getIdUser());
		var id = UserContactId.builder()
				.celPhone(contactRequest.getCelPhone())
				.user(user)
				.build();

		var contactEntity = contactRequest.toEntity();
		contactEntity.setId(id);

		return contactRepository.save(contactEntity);
	}

	public Contact find(Long idUser, String contactCelPhone) {
		var user = userService.find(idUser);

		var id = UserContactId.builder()
				.celPhone(contactCelPhone)
				.user(user)
				.build();

		return contactRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Contact not found by celPhone and idUser"));
	}

	public List<Contact> findAllByUserId(Long idUser) {
		var user = userService.find(idUser);
		return contactRepository.findAllByIdUserId(user.getId());
	}

	public Contact update(ContactRequest contactRequest) {
		var contactEntity = find(contactRequest.getIdUser(), contactRequest.getCelPhone());
		
		contactEntity.setName(contactRequest.getName());
		contactEntity.setUriImage(contactRequest.getUriImage());
		
		return contactRepository.save(contactEntity);
	}

}
