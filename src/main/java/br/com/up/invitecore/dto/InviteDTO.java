package br.com.up.invitecore.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import br.com.up.invitecore.domains.Event;
import br.com.up.invitecore.domains.Invite;
import br.com.up.invitecore.domains.User;
import br.com.up.invitecore.enumeration.TypeInvite;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InviteDTO {

	private Long id;
	private String location;
	private Integer type;
	private Long idUser;
	private EventDTO event;
	private List<ContactDTO> contacts;

	public Invite toEntity() {
		var invite = new ModelMapper().map(this, Invite.class);
		invite.setType(this.type == 0 ? TypeInvite.FREE : TypeInvite.PAY);

		return invite;
	}

}
