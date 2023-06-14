package br.com.up.invitecore.services;

import br.com.up.invitecore.domains.InviteContact;
import br.com.up.invitecore.domains.id.InviteContactId;
import br.com.up.invitecore.enumeration.TypeInvite;
import br.com.up.invitecore.exceptions.NotFoundException;
import br.com.up.invitecore.mocks.*;
import br.com.up.invitecore.repositories.InviteContactRepository;
import br.com.up.invitecore.repositories.InviteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static br.com.up.invitecore.mocks.ContactMockBuilder.*;
import static br.com.up.invitecore.mocks.EventMockBuilder.*;
import static br.com.up.invitecore.mocks.InviteContactMockBuilder.*;
import static br.com.up.invitecore.mocks.InviteMockBuilder.getInviteDTO;
import static br.com.up.invitecore.mocks.InviteMockBuilder.getInviteEntity;
import static br.com.up.invitecore.mocks.UserMockBuilder.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InviteServiceTest {

    @InjectMocks
    private InviteService inviteService;

    @Mock
    private InviteRepository inviteRepository;

    @Mock
    private InviteContactRepository inviteContactRepository;

    @Mock
    private UserService userService;

    @Mock
    private ContactService contactService;

    @Mock
    private EventService eventService;

    @Test
    public void creteInvite_WithValidData_ReturnInvite() {
        var userEntity = getUserEntity();
        var eventEntity = getEventEntity();
        var inviteDTO = getInviteDTO();
        var inviteEntity = getInviteEntity();
        var contactEntity = getContactEntity();
        var contactDTO = getContactDTO();
        var invateContactEntity = getInviteContactEntity();

        inviteDTO.getEvent().setId(1L);
        userEntity.setId(1L);
        inviteEntity.getUser().setId(1L);
        invateContactEntity.setId(InviteContactId.builder()
                .invite(inviteEntity)
                .contact(contactEntity)
                .build());

        when(userService.find(1L)).thenReturn(userEntity);
        when(eventService.find(1L)).thenReturn(eventEntity);
        when(contactService.find(contactDTO.getCelPhone(), 1L)).thenReturn(contactEntity);
        when(inviteContactRepository.saveAll(List.of(invateContactEntity))).thenReturn(List.of(invateContactEntity));
        when(inviteRepository.save(inviteEntity)).thenReturn(inviteEntity);

        var invite = inviteService.create(inviteDTO);

        assertEquals(inviteEntity, invite);
    }

    @Test
    public void creteInvite_WithInvalidUserData_ReturnThrow() {
        var inviteDTO = getInviteDTO();
        var userEntity = getUserEntity();

        inviteDTO.setIdUser(99L);

        when(userService.find(99L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> inviteService.create(inviteDTO));
    }

    @Test
    public void creteInvite_WithInvalidEventrData_ReturnThrow() {
        var inviteDTO = getInviteDTO();
        var userEntity = getUserEntity();
        inviteDTO.getEvent().setId(99L);

        when(userService.find(1L)).thenReturn(userEntity);
        when(eventService.find(99L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> inviteService.create(inviteDTO));
    }

    @Test
    public void findById_WithValidData_ReturnInvite() {
        var inviteDTO = getInviteDTO();
        var inviteEntity = getInviteEntity();

        when(inviteRepository.findById(1L)).thenReturn(Optional.of(inviteEntity));

        var invite = inviteService.find(1L);

        assertEquals(inviteEntity, invite);
    }

    @Test
    public void findById_WithInvalidData_ReturnThrow() {
        when(inviteRepository.findById(99L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> inviteService.find(99L));
    }

}
