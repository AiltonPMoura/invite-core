package br.com.up.invitecore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.up.invitecore.domains.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	List<Event> findAllByUserId(Long idUser);

}
