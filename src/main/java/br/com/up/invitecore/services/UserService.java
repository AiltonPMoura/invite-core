package br.com.up.invitecore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.up.invitecore.domains.User;
import br.com.up.invitecore.dto.UserDTO;
import br.com.up.invitecore.exceptions.NotFoundException;
import br.com.up.invitecore.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public UserDTO save(UserDTO user) {
		return userRepository.save(user.toEntity()).toDTO();
	}

	public UserDTO findById(Long id) {
		return userRepository.findById(id).map(User::toDTO)
				.orElseThrow(() -> new NotFoundException("User not found by id"));
	}
}
