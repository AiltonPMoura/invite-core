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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import br.com.up.invitecore.domains.dto.response.EventResponse;
import lombok.Data;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
	
	public EventResponse toResponse() {
		ModelMapper mapper = new ModelMapper();
		
		var eventResponse = mapper.map(this, EventResponse.class);
		eventResponse.setIdUser(this.user.getId());
		
		return eventResponse;
	}
	
	public static List<EventResponse> toListResponse(List<Event> events) {
		if (events != null)
			return events.stream()
					.map(Event::toResponse)
					.collect(Collectors.toList());
		else 
			return null;
	}
	
}
