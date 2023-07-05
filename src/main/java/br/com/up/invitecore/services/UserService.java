package br.com.up.invitecore.services;

import br.com.up.invitecore.domains.User;
import br.com.up.invitecore.domains.dto.request.UserRequest;
import br.com.up.invitecore.exceptions.NotFoundException;
import br.com.up.invitecore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User save(UserRequest userRequest) {
		return userRepository.save(userRequest.toEntity());
	}

	public User find(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found by id"));
	}

	public User update(Long id, UserRequest userRequest) {
		var userEntity = find(id);
		userEntity.setName(userRequest.getName());
		userEntity.setUriImage(userRequest.getUriImage());
		return userRepository.save(userEntity);
	}

}
