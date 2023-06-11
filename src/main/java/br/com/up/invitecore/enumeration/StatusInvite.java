package br.com.up.invitecore.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusInvite {
	
	PENDING(0),
	ACCECT(1),
	REJECT(2);
	
	private Integer status ;

}
