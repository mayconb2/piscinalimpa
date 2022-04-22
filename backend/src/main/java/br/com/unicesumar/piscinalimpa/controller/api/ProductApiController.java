package br.com.unicesumar.piscinalimpa.controller.api;

import br.com.unicesumar.piscinalimpa.dto.ProductEagerDTO;
import br.com.unicesumar.piscinalimpa.entity.Product;
import br.com.unicesumar.piscinalimpa.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductApiController {

    private final ProductService productService;
    private final ModelMapper mapper;

    public ProductApiController(ProductService productService, ModelMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductEagerDTO>> findAll() {
        var listProducts = productService.findAll().stream()
                .map(product -> mapper.map(product, ProductEagerDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listProducts);
    }

}
