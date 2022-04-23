package br.com.unicesumar.piscinalimpa.controller.adm;

import br.com.unicesumar.piscinalimpa.dto.BrandDTO;
import br.com.unicesumar.piscinalimpa.dto.BrandForm;
import br.com.unicesumar.piscinalimpa.service.BrandService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adm/v1/brand")
@Slf4j
public class BrandAdmController {

    private final BrandService brandService;

    public BrandAdmController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<List<BrandDTO>> findAll() {
        try {
            List<BrandDTO> brandDTOS = brandService.findAll();
            return ResponseEntity.ok(brandDTOS);
        } catch (Exception e) {
            log.error("Erro ao consultar lista de marcas {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<BrandDTO> createBrand(@RequestBody BrandForm brandForm) {
        try {
            BrandDTO brandDTO = this.brandService.create(brandForm);
            return ResponseEntity.status(HttpStatus.CREATED).body(brandDTO);
        } catch (Exception e) {
            log.error("Erro ao criar marca: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
