package br.com.up.invitecore.domains.id;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.com.up.invitecore.domains.Contact;
import br.com.up.invitecore.domains.Invite;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Embeddable
public class InviteContactId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "ID_INVITE")
	private Invite invite;
	
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CEL_PHONE"),
			@JoinColumn(name = "ID_USER")
	})
	private Contact contact;

}
