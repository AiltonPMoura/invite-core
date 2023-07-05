package br.com.up.invitecore.services;

import br.com.up.invitecore.domains.Event;
import br.com.up.invitecore.domains.dto.request.EventRequest;
import br.com.up.invitecore.exceptions.NotFoundException;
import br.com.up.invitecore.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private UserService userService;

	public Event create(EventRequest eventRequest) {
		var user = userService.find(eventRequest.getIdUser());

		var eventEntity = eventRequest.toEntity();
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

	public Event update(Long id, EventRequest eventRequest) {
		var eventEntity = find(id);
		
		eventEntity.setName(eventRequest.getName());
		eventEntity.setUriImage(eventRequest.getUriImage());
		
		return eventRepository.save(eventEntity);
	}

	public void delete(Long id) {
		eventRepository.deleteById(id);
	}

}
