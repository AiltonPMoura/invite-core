package br.com.up.invitecore.dto;

import org.modelmapper.ModelMapper;

import br.com.up.invitecore.domains.Event;
import br.com.up.invitecore.domains.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventDTO {

	private Long id;
	private String name;
	private String uriImage;
	private Long idUser;
	
	public Event toEntity(User user) {
		ModelMapper mapper = new ModelMapper();
		
		var event = mapper.map(this, Event.class);
		event.setUser(user);
		
		return event;
	}

}
