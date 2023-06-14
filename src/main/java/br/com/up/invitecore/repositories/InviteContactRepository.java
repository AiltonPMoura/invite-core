package br.com.up.invitecore.repositories;

import br.com.up.invitecore.domains.InviteContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteContactRepository extends JpaRepository<InviteContact, Long> {
}
