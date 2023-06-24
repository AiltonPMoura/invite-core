package br.com.up.invitecore.services;

import br.com.up.invitecore.domains.Contact;
import br.com.up.invitecore.exceptions.NotFoundException;
import br.com.up.invitecore.repositories.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.com.up.invitecore.mocks.ContactMockBuilder.getContactDTO;
import static br.com.up.invitecore.mocks.ContactMockBuilder.getContactEntity;
import static br.com.up.invitecore.mocks.UserMockBuilder.getUserEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
		contactDTO.setIdUser(99L);
		
		when(userService.find(99L)).thenThrow(NotFoundException.class);
		
		assertThrows(NotFoundException.class, () -> contactService.create(contactDTO));
	}

	@Test
	void findById_WithValidData_ReturnContact() {
		var contactDTO = getContactDTO();
		var contactEntity = getContactEntity();
		var userEntity = getUserEntity();
		userEntity.setId(1L);

		when(userService.find(1L)).thenReturn(userEntity);
		when(contactRepository.findById(contactEntity.getId())).thenReturn(Optional.of(contactEntity));

		var contact = contactService.find(contactDTO);

		assertEquals(contactEntity, contact);
	}

	@Test
	public void findById_WithInvalidData_ReturnThorwsWxception() {
		var contactDTO = getContactDTO();
		var contactEntity = getContactEntity();
		var userEntity = getUserEntity();
		userEntity.setId(1L);

		when(userService.find(1L)).thenReturn(userEntity);
		when(contactRepository.findById(contactEntity.getId())).thenThrow(NotFoundException.class);

		assertThrows(NotFoundException.class, () -> contactService.find(contactDTO));
	}

	@Test
	void FindAllByUserId_WithValidData_ReturnNotNull() {
		var contactEntity = getContactEntity();
		var userEntity = getUserEntity();
		userEntity.setId(1L);

		when(userService.find(1L)).thenReturn(userEntity);
		when(contactRepository.findAllByIdUserId(1L)).thenReturn(List.of(contactEntity));
		
		var contacts = contactService.findAllByUserId(1L);
		
		assertNotNull(contacts);
	}
	
	@Test
	void FindAllByUserId_WithInvalidUser_ThenReturnEmptyList() {
		var userEntity = getUserEntity();
		userEntity.setId(99L);

		when(userService.find(99L)).thenThrow(RuntimeException.class);

		assertThrows(RuntimeException.class, () -> contactService.findAllByUserId(99L));
	}

	@Test
	void FindAllByUserId_WithValidUserAndNotValidContact_ThenReturnEmptyList() {
		var userEntity = getUserEntity();
		userEntity.setId(1L);

		when(userService.find(1L)).thenReturn(userEntity);
		when(contactRepository.findAllByIdUserId(1L)).thenReturn(List.of());

		var contacts = contactService.findAllByUserId(1L);

		assertEquals(List.of(), contacts);
	}

	@Test
	void updateContact_WithValidData_ThenReturnContact() {
		var contactDTO = getContactDTO();

		var contactEntity = getContactEntity();
		contactDTO.setName("Teste 2");

		var userEntity = getUserEntity();
		userEntity.setId(1L);

		when(userService.find(1L)).thenReturn(userEntity);

		when(contactRepository.findById(contactEntity.getId())).thenReturn(Optional.of(contactEntity));
		when(contactRepository.save(contactEntity)).thenReturn(contactEntity);
		
		var contact = contactService.update(contactDTO);
		
		assertEquals(contactEntity, contact);
	}

	@Test
	public void updateContact_WithInvalidaData_ThenThrows() {
		var contactDTO = getContactDTO();
		contactDTO.setCelPhone("+5511999999999");

		var id = getContactEntity().getId();
		id.setCelPhone("+5511999999999");

		var userEntity = getUserEntity();
		userEntity.setId(1L);

		when(userService.find(1L)).thenReturn(userEntity);
		when(contactRepository.findById(id)).thenThrow(NotFoundException.class);
		
		assertThrows(NotFoundException.class, () -> contactService.update(contactDTO));
	}

}
