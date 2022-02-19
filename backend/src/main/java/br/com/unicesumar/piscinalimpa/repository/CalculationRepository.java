package br.com.unicesumar.piscinalimpa.repository;

import br.com.unicesumar.piscinalimpa.entity.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {

    Optional<Calculation> findByProductIdAndInterventionLevelId(Long productId, Long intervantionLevel);
}
