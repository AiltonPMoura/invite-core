package br.com.up.invitecore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_CONTACT")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CONTACT")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CEL_PHONE")
	private String celPhone;
	
	@Column(name = "URI_IMAGE")
	private String uriImage;
	
}
