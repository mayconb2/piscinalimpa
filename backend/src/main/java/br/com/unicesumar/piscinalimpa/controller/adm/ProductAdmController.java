package br.com.unicesumar.piscinalimpa.controller.adm;

import br.com.unicesumar.piscinalimpa.dto.ProductDTO;
import br.com.unicesumar.piscinalimpa.exception.NotFoundException;
import br.com.unicesumar.piscinalimpa.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adm/v1/product")
@Slf4j
public class ProductAdmController {

    private final ProductService productService;

    public ProductAdmController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) {
        try {
            var product = this.productService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (Exception e) {
            log.error("Erro ao criar produto: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody ProductDTO dto) {
        try {
            ProductDTO updatedto = this.productService.update(dto);
            return ResponseEntity.ok(updatedto);
        } catch (NotFoundException nfe) {
            log.error("Erro ao atualizar produto: {}", nfe);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nfe.getMessage());
        } catch (Exception e) {
            log.error("Erro ao atualizar produto: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {

        try {
            productService.deleteById(id);
            return ResponseEntity.ok("Produto de id: "+ id + " foi deletado com sucesso");
        } catch (EmptyResultDataAccessException nfe) {
            log.error("Erro ao atualizar produto: {}", nfe);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nfe.getMessage());
        } catch (Exception e) {
            log.error("Erro ao deletar produto: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
