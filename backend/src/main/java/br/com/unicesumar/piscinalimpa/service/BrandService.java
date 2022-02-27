package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.entity.Brand;
import br.com.unicesumar.piscinalimpa.repository.BrandRepository;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand findById(Long id) {
        return this.brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível encontrar marca com id: " + id));
    }
}
