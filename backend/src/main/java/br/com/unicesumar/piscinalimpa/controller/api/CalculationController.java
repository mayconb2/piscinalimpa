package br.com.unicesumar.piscinalimpa.controller.api;

import br.com.unicesumar.piscinalimpa.dto.ApplicationForm;
import br.com.unicesumar.piscinalimpa.dto.CalculationDTO;
import br.com.unicesumar.piscinalimpa.dto.ProductFormDTO;
import br.com.unicesumar.piscinalimpa.exception.NotFoundException;
import br.com.unicesumar.piscinalimpa.service.CalculationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/calculation")
@Slf4j
public class CalculationController {

    private final CalculationService calculationService;
    private final ModelMapper mapper;

    public CalculationController(CalculationService calculationService, ModelMapper mapper) {
        this.calculationService = calculationService;
        this.mapper = mapper;
    }

    @GetMapping("volume/{volume}/product/{product}/interventionLevel/{intervantionLevel}")
    public ResponseEntity performCalculation(@PathVariable("volume") Double volume,
                                              @PathVariable("product") Long product,
                                              @PathVariable("intervantionLevel") Long interventionLevel) {

        try {
            var bigDecimal = calculationService.performCalculation(volume, product, interventionLevel);
            return ResponseEntity.ok().body(bigDecimal);
        } catch (NotFoundException nfe) {
            log.error("Erro ao consultar cálculo de produto {}", nfe);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(nfe.getMessage());
        } catch (Exception e) {
            log.error("Erro ao consultar cálculo de produto {}", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity performCalculation(@RequestBody ApplicationForm form) {

        CalculationDTO calculationDTO = calculationService.retrieveProductsSuggestion(form);
        return  ResponseEntity.ok(calculationDTO);
    }
}
