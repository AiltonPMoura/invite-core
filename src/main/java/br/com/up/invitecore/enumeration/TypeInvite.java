package br.com.up.invitecore.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeInvite {
	
	FREE(0),
	PAY(1);
	
	private Integer type;

}
