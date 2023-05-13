package br.com.up.invitecore.domains;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.up.invitecore.enumeration.InviteType;
import lombok.Data;

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
	private InviteType type;
	
	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "ID_EVENT")
	private Event event;
	
	@OneToMany
	@JoinTable(name = "TB_INVITE_CONTACT", 
		joinColumns = @JoinColumn(name = "ID_INVITE"),
		inverseJoinColumns = @JoinColumn(name = "ID_CONTACT"))
	private List<Contact> guests;

}
