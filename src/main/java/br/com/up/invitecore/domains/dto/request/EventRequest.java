package br.com.up.invitecore.domains.dto.request;

import br.com.up.invitecore.domains.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

	private String name;
	private String uriImage;
	private Long idUser;
	
	public Event toEntity() {
		return new ModelMapper().map(this, Event.class);
	}

}
