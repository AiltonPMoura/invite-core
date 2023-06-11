package br.com.up.invitecore.domains;

import br.com.up.invitecore.domains.id.InviteContactId;
import br.com.up.invitecore.enumeration.StatusInvite;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name = "TB_INVITE_CONTACT")
public class InviteContact {
	
	@EmbeddedId
	private InviteContactId id;

	@Column(name = "STATUS_INVITE")
	private StatusInvite statusInvite;

}
