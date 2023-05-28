package br.com.up.invitecore.domains;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.up.invitecore.dto.EventDTO;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_EVENT")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EVENT")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "URI_IMAGE")
	private String uriImage;
	
	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private User user;
	
	public EventDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		
		var eventDTO = mapper.map(this, EventDTO.class);
		eventDTO.setIdUser(this.user.getId());
		
		return eventDTO;
	}
	
	public static List<EventDTO> toListDTO(List<Event> events) {
		if (events != null)
			return events.stream()
					.map(Event::toDTO)
					.collect(Collectors.toList());
		else 
			return null;
	}
	
}
