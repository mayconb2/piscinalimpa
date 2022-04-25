package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.dto.BrandDTO;
import br.com.unicesumar.piscinalimpa.dto.BrandForm;
import br.com.unicesumar.piscinalimpa.entity.Brand;
import br.com.unicesumar.piscinalimpa.entity.Formula;
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

    public BrandDTO create(BrandForm brandForm) {
        Brand brandEntity = this.mapper.map(brandForm, Brand.class);
        Brand brandSaved = this.brandRepository.save(brandEntity);
        return this.mapper.map(brandSaved, BrandDTO.class);
    }

    public Brand update(Long id, BrandForm brandForm) {
        Brand brand = this.brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível achar marca com id: " + id));

        brand.setName(brandForm.getName());

        return this.brandRepository.save(brand);
    }

    public void deleteById(Long id) {
        this.brandRepository.deleteById(id);
    }
}
