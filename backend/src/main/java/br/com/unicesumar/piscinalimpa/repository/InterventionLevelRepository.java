package br.com.unicesumar.piscinalimpa.repository;

import br.com.unicesumar.piscinalimpa.entity.InterventionLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterventionLevelRepository extends JpaRepository<InterventionLevel, Long> {
}
