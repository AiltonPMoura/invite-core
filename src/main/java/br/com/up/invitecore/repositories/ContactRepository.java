package br.com.up.invitecore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.up.invitecore.domains.Contact;
import br.com.up.invitecore.domains.id.UserContactId;

public interface ContactRepository extends JpaRepository<Contact, UserContactId> {

	List<Contact> findAllByIdUserId(Long idUser);

}
