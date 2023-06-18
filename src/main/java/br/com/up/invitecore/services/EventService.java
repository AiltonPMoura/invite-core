package br.com.up.invitecore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.up.invitecore.domains.Event;
import br.com.up.invitecore.dto.EventDTO;
import br.com.up.invitecore.exceptions.NotFoundException;
import br.com.up.invitecore.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private UserService userService;

	public Event create(EventDTO eventDTO) {
		var user = userService.find(eventDTO.getIdUser());

		var eventEntity = eventDTO.toEntity();
		eventEntity.setUser(user);

		return eventRepository.save(eventEntity);
	}

	public Event find(Long id) {
		return eventRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Event not found by id"));
	}

	public List<Event> findAll(Long idUser) {
		var user = userService.find(idUser);
		return eventRepository.findAllByUserId(user.getId());
	}

	public Event update(EventDTO eventDTO, Long id) {
		var eventEntity = find(id);
		
		eventEntity.setName(eventDTO.getName());
		eventEntity.setUriImage(eventDTO.getUriImage());
		
		return eventRepository.save(eventEntity);
	}

	public void delete(Long id) {
		eventRepository.deleteById(id);
	}

}
