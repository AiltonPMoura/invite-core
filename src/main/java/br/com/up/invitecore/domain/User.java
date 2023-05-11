package br.com.up.invitecore.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CEL_PHONE")
	private String celPhone;
	
	@Column(name = "URI_IMAGE")
	private String uriImage;
	
	@OneToMany
	@JoinTable(name = "TB_USER_CONTACT",
			joinColumns = @JoinColumn(name = "ID_USER"),
			inverseJoinColumns = @JoinColumn(name = "ID_CONTACT"))
	private List<Contact> contacts;
	
}
