package br.com.unicesumar.piscinalimpa.repository;

import br.com.unicesumar.piscinalimpa.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
