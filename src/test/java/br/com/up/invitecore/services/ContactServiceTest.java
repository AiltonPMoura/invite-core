package br.com.up.invitecore.services;

import static br.com.up.invitecore.mocks.ContactMockBuilder.getContactDTO;
import static br.com.up.invitecore.mocks.ContactMockBuilder.getContactEntity;
import static br.com.up.invitecore.mocks.UserMockBuilder.getUserEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.up.invitecore.domains.Contact;
import br.com.up.invitecore.domains.id.UserContactId;
import br.com.up.invitecore.exceptions.NotFoundException;
import br.com.up.invitecore.repositories.ContactRepository;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {
	
	@InjectMocks
	private ContactService contactService;

	@Mock
	private ContactRepository contactRepository;

	@Mock
	private UserService userService;
	
	@Test
	void createContact_WithValidData_ReturnContact() {
		var contactDTO = getContactDTO();
		var contactEntity = getContactEntity();
		var userEntity = getUserEntity();
		userEntity.setId(1L);
		
		when(userService.find(1L)).thenReturn(userEntity);
		when(contactRepository.save(contactEntity)).thenReturn(contactEntity);
		
		Contact contact = contactService.create(contactDTO);
		
		assertEquals(contactEntity, contact);
	}
	
	@Test
	public void createContact_WithInvalidData_ReturnThroesException() {		
		var contactDTO = getContactDTO();
		
		when(userService.find(1L)).thenThrow(NotFoundException.class);
		
		assertThrows(NotFoundException.class, () -> contactService.create(contactDTO));
	}

	@Test
	void findById_WithValidData_ThenReturnContact() {
		var contactEntity = getContactEntity();
		
		String celPhone = "+5511922223333";
		Long idUser = 1L;
		
		var id = UserContactId.builder()
				.celPhone(celPhone)
				.idUser(idUser)
				.build();
		
		when(contactRepository.findById(id)).thenReturn(Optional.of(contactEntity));
		
		var contact = contactService.find(celPhone, 1L);
		
		assertEquals(contactEntity, contact);
	}
	
	public void findById_WithInvalidData_ThenReturnThorwsWxception() {
		String celPhone = "+5511922223333";
		Long idUser = 99L;
		
		var id = UserContactId.builder()
				.celPhone(celPhone)
				.idUser(idUser)
				.build();
		
		when(contactRepository.findById(id)).thenThrow(NotFoundException.class);
		
		assertThrows(NotFoundException.class, () -> contactService.find(celPhone, 99L));
	}

	@Test
	void FindAllByUserId_WithValidData_ThenReturnNotNull() {
		var contactEntity = getContactEntity();
		
		when(contactRepository.findAllByUserId(1L)).thenReturn(List.of(contactEntity));
		
		var contacts = contactService.findAllByUserId(1L);
		
		assertNotNull(contacts);
	}
	
	@Test
	void FindAllByUserId_WithInvalidData_ThenReturnEmptyList() {
		when(contactRepository.findAllByUserId(99L)).thenReturn(List.of());
		
		var contacts = contactService.findAllByUserId(99L);
		
		assertEquals(List.of(), contacts);
	}

	@Test
	void updateContact_WithValidData_ThenReturnContact() {
		var contactDTO = getContactDTO();
		var contactEntity = getContactEntity();
		
		var id = UserContactId.builder()
				.celPhone(contactDTO.getCelPhone())
				.idUser(contactDTO.getIdUser())
				.build();
		
		when(contactRepository.findById(id)).thenReturn(Optional.of(contactEntity));
		when(contactRepository.save(contactEntity)).thenReturn(contactEntity);
		
		var contact = contactService.update(contactDTO);
		
		assertEquals(contactEntity, contact);
	}
	
	public void updateContact_WithInvalidaData_ThenThrows() {
		var contactDTO = getContactDTO();
		
		var id = UserContactId.builder()
				.celPhone(contactDTO.getCelPhone())
				.idUser(contactDTO.getIdUser())
				.build();
		
		when(contactRepository.findById(id)).thenThrow(NotFoundException.class);
		
		assertThrows(NotFoundException.class, () -> contactService.update(contactDTO));
	}

}
