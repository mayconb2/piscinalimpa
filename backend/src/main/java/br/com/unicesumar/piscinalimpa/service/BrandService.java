package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.dto.BrandDTO;
import br.com.unicesumar.piscinalimpa.entity.Brand;
import br.com.unicesumar.piscinalimpa.exception.NotFoundException;
import br.com.unicesumar.piscinalimpa.repository.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper mapper;

    public BrandService(BrandRepository brandRepository, ModelMapper mapper) {
        this.brandRepository = brandRepository;
        this.mapper = mapper;
    }

    public Brand findById(Long id) {
        return this.brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar marca com id: " + id));
    }

    public List<BrandDTO> findAll() {
         return this.brandRepository.findAll().stream()
                 .map(brand -> mapper.map(brand, BrandDTO.class))
                 .collect(Collectors.toList());
    }
}
