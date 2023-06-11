package br.com.up.invitecore.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import br.com.up.invitecore.domains.Event;
import br.com.up.invitecore.domains.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

	private Long id;
	private String name;
	private String uriImage;
	private Long idUser;
	
	public Event toEntity() {
		return new ModelMapper().map(this, Event.class);
	}

}
