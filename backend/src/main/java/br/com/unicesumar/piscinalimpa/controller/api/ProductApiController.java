package br.com.unicesumar.piscinalimpa.controller.api;

import br.com.unicesumar.piscinalimpa.dto.ProductDTO;
import br.com.unicesumar.piscinalimpa.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductApiController {

    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        var listProducts = productService.findAll();
        return ResponseEntity.ok(listProducts);
    }

}
