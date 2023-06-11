package br.com.up.invitecore.domains;

import br.com.up.invitecore.dto.InviteDTO;
import br.com.up.invitecore.enumeration.TypeInvite;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "TB_INVITE")
public class Invite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INVITE")
	private Long id;

	@Column(name = "DATE_TIME")
	private LocalDateTime dateTime;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "TYPE")
	@Enumerated(EnumType.ORDINAL)
	private TypeInvite type;

	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private User user;

	@ManyToOne
	@JoinColumn(name = "ID_EVENT")
	private Event event;

	@OneToMany(mappedBy = "id.invite")
	private List<InviteContact> inviteContact;

	public InviteDTO toDTO() {
		ModelMapper mapper = new ModelMapper();

		return mapper.map(this, InviteDTO.class);
	}

}
