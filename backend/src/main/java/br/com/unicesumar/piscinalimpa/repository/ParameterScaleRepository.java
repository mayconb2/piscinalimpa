package br.com.unicesumar.piscinalimpa.repository;

import br.com.unicesumar.piscinalimpa.entity.ParameterScale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParameterScaleRepository extends JpaRepository<ParameterScale, Long> {

    Optional<ParameterScale> findByParameterIdAndValue(Long parameterId,Integer value);
}
