package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.dto.ProductDTO;
import br.com.unicesumar.piscinalimpa.entity.Product;
import br.com.unicesumar.piscinalimpa.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public ProductService(ProductRepository productRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public List<ProductDTO> findAll() {
        var listProductsEntitie = this.productRepository.findAll();
        return listProductsEntitie.stream().map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
