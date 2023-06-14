package br.com.up.invitecore.mocks;

import br.com.up.invitecore.domains.InviteContact;
import br.com.up.invitecore.enumeration.StatusInvite;

public class InviteContactMockBuilder {

    private InviteContactMockBuilder(){}

    public static InviteContact getInviteContactEntity() {
        return InviteContact.builder()
                .statusInvite(StatusInvite.PENDING)
                .build();
    }
}
