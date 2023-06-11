package br.com.up.invitecore.repositories;

import br.com.up.invitecore.domains.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InviteRepository extends JpaRepository<Invite, Long> {
    List<Invite> findByIdAndUserId(Long id, Long userId);
}
