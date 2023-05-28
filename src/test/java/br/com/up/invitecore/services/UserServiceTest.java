package br.com.up.invitecore.services;

import static br.com.up.invitecore.mocks.UserMockBuilder.getUserDTO;
import static br.com.up.invitecore.mocks.UserMockBuilder.getUserEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.up.invitecore.exceptions.NotFoundException;
import br.com.up.invitecore.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;
		
	@Test
	public void createUser_WithValidData_ReturnsUser() {
		var userEntity = getUserEntity();
		var userDTO = getUserDTO();

		when(userRepository.save(userEntity)).thenReturn(userEntity);

		var user = userService.save(userDTO);

		assertEquals(userEntity, user);
	}
	
	@Test
	public void createUser_WithInvalidData_ReturnThrowsException() {
		var userEntity = getUserEntity();
		var userDTO = getUserDTO();
		
		when(userRepository.save(userEntity)).thenThrow(NotFoundException.class);
		
		assertThrows(NotFoundException.class, () -> userService.save(userDTO));
	}

	@Test
	public void findById_WithValidId_ReturnUser() {
		var userEntity = getUserEntity();
		
		when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
		var user = userService.findById(1L);
		
		assertEquals(userEntity, user);
	}
	
	@Test
	public void findById_WithUnexisting_ReturnsThrowsException() {
		when(userRepository.findById(99L)).thenThrow(NotFoundException.class);

		assertThrows(NotFoundException.class, () -> userService.findById(99L));
	}

	@Test
	public void updateUser_WithValidData_ReturnUser() {
		var userEntity = getUserEntity();
		var userDTO = getUserDTO();
		
		when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
		when(userRepository.save(userEntity)).thenReturn(userEntity);
		
		var user = userService.update(userDTO, 1L);
		
		assertEquals(userEntity, user);
	}
	
	@Test
	public void updateUser_WithInvalidData_ReturnThrowsException() {
		var userDTO = getUserDTO();
		
		when(userRepository.findById(99L)).thenThrow(NotFoundException.class);
		
		assertThrows(NotFoundException.class, () -> userService.update(userDTO, 99L));
		
	}

}
