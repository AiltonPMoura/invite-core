package br.com.up.invitecore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.up.invitecore.domains.Contact;
import br.com.up.invitecore.domains.id.UserContactId;
import br.com.up.invitecore.dto.ContactDTO;
import br.com.up.invitecore.exceptions.NotFoundException;
import br.com.up.invitecore.repositories.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private UserService userService;

	public Contact create(ContactDTO contact) {
		var user = userService.find(contact.getIdUser());
		var contactEntity = contact.toEntity();
		var id = UserContactId.builder()
				.celPhone(contact.getCelPhone())
				.idUser(user.getId())
				.build();

		contactEntity.setId(id);
		contactEntity.setUser(user);

		return contactRepository.save(contactEntity);
	}

	public Contact find(String celPhone, Long idUser) {
		var id = UserContactId.builder()
				.celPhone(celPhone)
				.idUser(idUser)
				.build();
		
		return contactRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Contact not found by celPhone and idUser"));
	}

	public List<Contact> findAllByUserId(Long idUser) {
		return contactRepository.findAllByUserId(idUser);
	}

	public Contact update(ContactDTO contact) {
		var contactEntity = find(contact.getCelPhone(), contact.getIdUser());
		
		contactEntity.setName(contact.getName());
		contactEntity.setUriImage(contact.getUriImage());
		
		return contactRepository.save(contactEntity);
	}

}
