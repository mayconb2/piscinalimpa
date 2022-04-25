package br.com.unicesumar.piscinalimpa.controller.adm;

import br.com.unicesumar.piscinalimpa.dto.FormulaDTO;
import br.com.unicesumar.piscinalimpa.dto.FormulaForm;
import br.com.unicesumar.piscinalimpa.entity.Formula;
import br.com.unicesumar.piscinalimpa.service.FormulaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adm/v1/formula")
@Slf4j
public class FormulaAdmController {

    private final FormulaService formulaService;
    private final ModelMapper mapper;

    public FormulaAdmController(FormulaService formulaService, ModelMapper mapper) {
        this.formulaService = formulaService;
        this.mapper = mapper;
    }

    @GetMapping
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<List<FormulaDTO>> findAll() {
        try {
            List<FormulaDTO> formulaDTOS = formulaService.findAll();
            return ResponseEntity.ok(formulaDTOS);
        } catch (Exception e) {
            log.error("Erro ao consultar lista de formulas {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<FormulaDTO> createFormula(@RequestBody FormulaForm formulaForm) {
        try {
            FormulaDTO formulaDto = this.formulaService.create(formulaForm);
            return ResponseEntity.status(HttpStatus.CREATED).body(formulaDto);
        } catch (Exception e) {
            log.error("Erro ao criar formula: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<FormulaDTO> findById(@PathVariable Long id) {
        try {
            Formula formulaEntity = formulaService.findById(id);
            return  ResponseEntity.ok(this.mapper.map(formulaEntity, FormulaDTO.class));
        } catch (Exception e) {
            log.error("Erro ao consultar fórmulas {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<FormulaDTO> updateFormula(@PathVariable Long id ,@RequestBody FormulaForm formulaForm) {
        try {
            Formula formulaUpdated = this.formulaService.update(id, formulaForm);
            return ResponseEntity.ok(this.mapper.map(formulaUpdated, FormulaDTO.class));
        } catch (Exception e) {
            log.error("Erro ao atualizar formula: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity deleteProduct(@PathVariable Long id) {

        try {
            this.formulaService.deleteById(id);
            return ResponseEntity.ok(null);
        } catch (EmptyResultDataAccessException nfe) {
            log.error("Erro de violação de constraint ao deletar fórmula: {}", nfe);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Há produto(s) que utiliza(m) essa fórmula!");
        } catch (DataIntegrityViolationException e) {
            log.error("Erro ao deletar fórmula: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            log.error("Erro ao deletar fórmula: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
