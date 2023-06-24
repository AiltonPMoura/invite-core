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

	public User save(UserDTO userDTO) {
		return userRepository.save(userDTO.toEntity());
	}

	public User find(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found by id"));
	}

	public User update(UserDTO userDTO) {
		var userEntity = find(userDTO.getId());
		userEntity.setName(userDTO.getName());
		userEntity.setUriImage(userDTO.getUriImage());
		return userRepository.save(userEntity);
	}

}
