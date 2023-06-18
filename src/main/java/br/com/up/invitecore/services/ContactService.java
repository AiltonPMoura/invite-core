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

	public Contact create(ContactDTO contactDTO) {
		var user = userService.find(contactDTO.getIdUser());
		var id = UserContactId.builder()
				.celPhone(contactDTO.getCelPhone())
				.user(user)
				.build();

		var contactEntity = contactDTO.toEntity();
		contactEntity.setId(id);

		return contactRepository.save(contactEntity);
	}

	public Contact find(ContactDTO contactDTO) {
		var user = userService.find(contactDTO.getIdUser());

		var id = UserContactId.builder()
				.celPhone(contactDTO.getCelPhone())
				.user(user)
				.build();

		return contactRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Contact not found by celPhone and idUser"));
	}

	public List<Contact> findAllByUserId(Long idUser) {
		return contactRepository.findAllByIdUserId(idUser);
	}

	public Contact update(ContactDTO contactDTO) {
		var contactEntity = find(contactDTO);
		
		contactEntity.setName(contactDTO.getName());
		contactEntity.setUriImage(contactDTO.getUriImage());
		
		return contactRepository.save(contactEntity);
	}

}
