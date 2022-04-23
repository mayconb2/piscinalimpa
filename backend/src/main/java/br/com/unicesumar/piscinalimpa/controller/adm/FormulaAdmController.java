package br.com.unicesumar.piscinalimpa.controller.adm;

import br.com.unicesumar.piscinalimpa.dto.FormulaDTO;
import br.com.unicesumar.piscinalimpa.service.FormulaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adm/v1/formula")
@Slf4j
public class FormulaAdmController {

    private final FormulaService formulaService;

    public FormulaAdmController(FormulaService formulaService) {
        this.formulaService = formulaService;
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
}
