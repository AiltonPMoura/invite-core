package br.com.up.invitecore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.up.invitecore.domains.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
