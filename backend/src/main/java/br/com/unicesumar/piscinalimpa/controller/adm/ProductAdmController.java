package br.com.unicesumar.piscinalimpa.controller.adm;

import br.com.unicesumar.piscinalimpa.dto.ProductDTO;
import br.com.unicesumar.piscinalimpa.dto.ProductEagerDTO;
import br.com.unicesumar.piscinalimpa.dto.UserBackofficeDTO;
import br.com.unicesumar.piscinalimpa.dto.UserBackofficeForm;
import br.com.unicesumar.piscinalimpa.entity.Product;
import br.com.unicesumar.piscinalimpa.entity.UserBackoffice;
import br.com.unicesumar.piscinalimpa.exception.NotFoundException;
import br.com.unicesumar.piscinalimpa.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adm/v1/product")
@Slf4j
public class ProductAdmController {

    private final ProductService productService;
    private final ModelMapper mapper;

    public ProductAdmController(ProductService productService, ModelMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @PostMapping
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) {
        try {
            var product = this.productService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (Exception e) {
            log.error("Erro ao criar produto: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

   /* @PutMapping
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
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
    }*/

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
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

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<ProductEagerDTO> findById(@PathVariable Long id) {
        try {
            Product productEntity = productService.findById(id);
            return  ResponseEntity.ok(this.mapper.map(productEntity, ProductEagerDTO.class));
        } catch (Exception e) {
            log.error("Erro ao consultar usuário {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {
        try {
            Product productEntity = this.productService.update(id, dto);
            return ResponseEntity.ok(this.mapper.map(productEntity, ProductDTO.class));
        } catch (Exception e) {
            log.error("Erro ao atualizar usuário: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
