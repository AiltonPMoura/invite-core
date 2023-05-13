package br.com.up.invitecore.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
}
