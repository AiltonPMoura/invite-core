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

	public Contact save(ContactDTO contact, Long idUser) {
		var user = userService.findById(idUser);
		
		var id = UserContactId.builder()
				.celPhone(contact.getCelPhone())
				.idUser(user.getId())
				.build();
		
		return contactRepository.save(contact.toEntity(id, user));
	}

	public Contact findById(String celPhone, Long idUser) {
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

	public Contact update(ContactDTO contact, String celPhone, Long idUser) {
		var contactEntity = findById(celPhone, idUser);
		
		var user = userService.findById(idUser);
		
		var id = UserContactId.builder()
				.celPhone(contact.getCelPhone())
				.idUser(user.getId())
				.build();
		
		contactEntity.setName(contact.getName());
		contactEntity.setUriImage(contact.getUriImage());
		contactEntity.setId(id);
		
		return contactEntity;
	}

}
