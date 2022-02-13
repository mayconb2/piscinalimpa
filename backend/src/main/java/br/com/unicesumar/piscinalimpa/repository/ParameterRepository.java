package br.com.unicesumar.piscinalimpa.repository;

import br.com.unicesumar.piscinalimpa.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
}
