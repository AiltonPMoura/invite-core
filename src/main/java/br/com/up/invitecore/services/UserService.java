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

	public User save(UserDTO user) {
		return userRepository.save(user.toEntity());
	}

	public User find(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found by id"));
	}

	public User update(UserDTO user, Long id) {
		var userEntity = find(id);
		userEntity.setName(user.getName());
		userEntity.setUriImage(user.getUriImage());
		return userRepository.save(userEntity);
	}

}
