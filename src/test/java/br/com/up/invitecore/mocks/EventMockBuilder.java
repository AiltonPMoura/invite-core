package br.com.up.invitecore.mocks;

import br.com.up.invitecore.domains.Event;
import br.com.up.invitecore.dto.EventDTO;

public class EventMockBuilder {

    private EventMockBuilder(){};

    public static Event getEventEntity() {
        return Event.builder()
                .name("Teste")
                .uriImage("http://teste.com")
                .user(UserMockBuilder.getUserEntity())
                .build();
    }

    public static EventDTO getEventDTO() {
        return EventDTO.builder()
                .name("Teste")
                .uriImage("http://teste.com")
                .idUser(1L)
                .build();
    }

}
