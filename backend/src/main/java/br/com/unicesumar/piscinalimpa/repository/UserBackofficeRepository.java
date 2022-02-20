package br.com.unicesumar.piscinalimpa.repository;

import br.com.unicesumar.piscinalimpa.entity.UserBackoffice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBackofficeRepository extends JpaRepository<UserBackoffice, Long> {

    Optional<UserBackoffice> findByLogin(String login);
}
