package br.com.up.invitecore.mocks;

import br.com.up.invitecore.domains.Invite;
import br.com.up.invitecore.dto.EventDTO;
import br.com.up.invitecore.dto.InviteDTO;
import br.com.up.invitecore.enumeration.TypeInvite;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.util.List;

public class InviteMockBuilder {

    private InviteMockBuilder(){}

    public static Invite getInviteEntity() {
        return Invite.builder()
                .dateTime(LocalDateTime.now().withSecond(0).withNano(0))
                .location("Local Teste")
                .event(EventMockBuilder.getEventEntity())
                .type(TypeInvite.FREE)
                .user(UserMockBuilder.getUserEntity())
                .build();
    }

    public static InviteDTO getInviteDTO() {
        return InviteDTO.builder()
                .idUser(1L)
                .event(EventMockBuilder.getEventDTO())
                .location("Local Teste")
                .type(0)
                .contacts(List.of(ContactMockBuilder.getContactDTO()))
                .build();
    }

}
