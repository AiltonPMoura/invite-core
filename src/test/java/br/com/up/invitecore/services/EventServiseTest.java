package br.com.up.invitecore.services;

import br.com.up.invitecore.exceptions.NotFoundException;
import br.com.up.invitecore.repositories.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.com.up.invitecore.mocks.EventMockBuilder.getEventDTO;
import static br.com.up.invitecore.mocks.EventMockBuilder.getEventEntity;
import static br.com.up.invitecore.mocks.UserMockBuilder.getUserEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiseTest {
    @InjectMocks
    private EventService eventService;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private UserService userService;

    @Test
    public void createEvent_WithValidData_ReturnEvent() {
        var eventEntity = getEventEntity();
        var eventDTO = getEventDTO();
        var userEntity = getUserEntity();

        when(userService.find(1L)).thenReturn(userEntity);
        when(eventRepository.save(eventEntity)).thenReturn(eventEntity);

        var event = eventService.create(eventDTO);

        assertEquals(eventEntity, event);
    }

    @Test
    public void crateEvent_WithInvalidData_ReturnThrow() {
        var eventDTO = getEventDTO();
        eventDTO.setIdUser(99L);

        when(userService.find(99L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> eventService.create(eventDTO));
    }

    @Test
    public void findEvent_WithValidaData_ReturnEvent() {
        var eventEntity = getEventEntity();
        var eventDTO = getEventDTO();

        when(eventRepository.findById(1L)).thenReturn(Optional.of(eventEntity));

        var event = eventService.find(1L);

        assertEquals(eventEntity, event);
    }

    @Test
    public void findByIdEvent_WithInvalidData_ReturnThrow() {
        when(eventRepository.findById(99L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> eventService.find(99L));
    }

    @Test
    public void findAllEvent_WithValidData_ReturnEvents() {
        var eventsEntity = List.of(getEventEntity());
        var userEntity = getUserEntity();
        userEntity.setId(1L);

        when(userService.find(1L)).thenReturn(userEntity);
        when(eventRepository.findAllByUserId(1L)).thenReturn(eventsEntity);

        var events = eventService.findAll(1L);

        assertEquals(eventsEntity, events);
    }

    @Test
    public void findAllEvent_WithInvalidData_ReturnThrows() {
        when(userService.find(99L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> eventService.findAll(99L));
    }

    @Test
    public void updateEvent_WithValidData_ReturnEvent() {
        var eventEntity = getEventEntity();
        eventEntity.setName("teste 2");

        var eventDTO = getEventDTO();
        eventDTO.setName("teste 2");

        when(eventRepository.findById(1L)).thenReturn(Optional.of(eventEntity));
        when(eventRepository.save(eventEntity)).thenReturn(eventEntity);

        var event = eventService.update(eventDTO, 1L);

        assertEquals(eventEntity, event);
    }

    @Test
    public void updateEvent_WithInvalidData_ReturnThrow() {
        var eventEntity = getEventEntity();
        var eventDTO = getEventDTO();

        when(eventRepository.findById(99L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> eventService.update(eventDTO, 99L));
    }

    @Test
    public void deleteEvent_WithExistingId_DoesNotThrow() {
        assertDoesNotThrow(() -> eventService.delete(1L));
    }

    @Test
    public void deleteEvent_WithUnexistingId_DoesNotThrow() {
        doThrow(NotFoundException.class).when(eventRepository).deleteById(99L);

        assertThrows(NotFoundException.class, () -> eventService.delete(99L));
    }

}
