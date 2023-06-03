package br.com.up.invitecore.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.up.invitecore.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
	
	public UserDTO toDTO() {
		return new ModelMapper().map(this, UserDTO.class);
	}
}
