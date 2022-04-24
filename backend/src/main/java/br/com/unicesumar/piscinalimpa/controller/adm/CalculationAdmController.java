package br.com.unicesumar.piscinalimpa.controller.adm;

import br.com.unicesumar.piscinalimpa.dto.CalculationAdminDTO;
import br.com.unicesumar.piscinalimpa.dto.CalculationForm;
import br.com.unicesumar.piscinalimpa.entity.Calculation;
import br.com.unicesumar.piscinalimpa.service.CalculationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/adm/v1/calculation")
@Slf4j
public class CalculationAdmController {

    private final CalculationService calculationService;
    private final ModelMapper mapper;

    public CalculationAdmController(CalculationService calculationService, ModelMapper mapper) {
        this.calculationService = calculationService;
        this.mapper = mapper;
    }

    @GetMapping
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<List<CalculationAdminDTO>> findAll() {
        try {
            var productsAdmDTO = calculationService.findAll().stream()
                    .map(calculation -> mapper.map(calculation, CalculationAdminDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(productsAdmDTO);
        } catch (Exception e) {
            log.error("Erro ao consultar lista de cálculos {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<CalculationForm> createCalculation(@RequestBody CalculationForm dto) {
        try {
            CalculationForm calculation = this.calculationService.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(calculation);
        } catch (Exception e) {
            log.error("Erro ao criar cálculo: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<CalculationAdminDTO> findById(@PathVariable Long id) {
        try {
            Calculation calculationEntity = calculationService.findById(id);
            return  ResponseEntity.ok(this.mapper.map(calculationEntity, CalculationAdminDTO.class));
        } catch (Exception e) {
            log.error("Erro ao consultar usuário {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<CalculationForm> updateProduct(@PathVariable Long id, @RequestBody CalculationForm dto) {
        try {
            Calculation calculationEntity = this.calculationService.update(id, dto);
            return ResponseEntity.ok(this.mapper.map(calculationEntity, CalculationForm.class));
        } catch (Exception e) {
            log.error("Erro ao atualizar usuário: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
