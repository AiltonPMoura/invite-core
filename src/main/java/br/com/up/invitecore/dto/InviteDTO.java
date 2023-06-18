package br.com.up.invitecore.dto;

import br.com.up.invitecore.domains.Invite;
import br.com.up.invitecore.enumeration.TypeInvite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InviteDTO {

	private Long id;
	private String location;
	private Integer type;
	private Long idUser;
	private EventDTO eventDTO;
	private List<ContactDTO> contacts;

	public Invite toEntity() {
		var invite = new ModelMapper().map(this, Invite.class);
		invite.setType(this.type == 0 ? TypeInvite.FREE : TypeInvite.PAY);

		return invite;
	}

}
