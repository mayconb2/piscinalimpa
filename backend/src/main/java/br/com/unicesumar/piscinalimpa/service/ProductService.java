package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.dto.ProductDTO;
import br.com.unicesumar.piscinalimpa.entity.Product;
import br.com.unicesumar.piscinalimpa.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ParameterService parameterService;
    private final BrandService brandService;
    private final ModelMapper mapper;

    public ProductService(ProductRepository productRepository, ParameterService parameterService, BrandService brandService, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.parameterService = parameterService;
        this.brandService = brandService;
        this.mapper = mapper;
    }

    public List<ProductDTO> findAll() {
        var listProductsEntitie = this.productRepository.findAll();
        return listProductsEntitie.stream().map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO create(ProductDTO dto) {
        var product = mapper.map(dto, Product.class);
        var savedProduct = this.productRepository.save(product);
        return mapper.map(savedProduct, ProductDTO.class);
    }

    public ProductDTO update(ProductDTO dto) {
        var product = this.productRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Não foi possível localizar produto com id: " + dto.getId()));

        if (!dto.getAffectedParameterId().equals(product.getAffectedParameter().getId())) {
            var parameter = parameterService.findById(dto.getAffectedParameterId());
            product.setAffectedParameter(parameter);
        }

        if (dto.getBrandId().equals(product.getBrand().getId())) {
            var brand = brandService.findById(dto.getBrandId());
            product.setBrand(brand);
        }

        if (!dto.getName().equalsIgnoreCase(product.getName())) {
            product.setName(dto.getName());
        }

        var updatedProduct = productRepository.save(product);
        return mapper.map(updatedProduct, ProductDTO.class);
    }
}
