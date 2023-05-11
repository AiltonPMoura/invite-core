package br.com.up.invitecore.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InviteType {
	
	FREE(0),
	PAY(1);
	
	private int type;

}
