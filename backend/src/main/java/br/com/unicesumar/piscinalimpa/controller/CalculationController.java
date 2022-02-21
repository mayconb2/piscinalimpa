package br.com.unicesumar.piscinalimpa.controller;

import br.com.unicesumar.piscinalimpa.service.CalculationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/api/v1/calculation")
public class CalculationController {

    private final CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("volume/{volume}/product/{product}/interventionLevel/{intervantionLevel}")
    private String performCalculation(@PathVariable("volume") Double volume,
                                      @PathVariable("product") Long product,
                                      @PathVariable("intervantionLevel") Long interventionLevel) {

        BigDecimal quantity = calculationService.performCalculation(volume, product, interventionLevel);

        return "Você deve aplicar " + quantity + " gramas de produtos na sua piscina!";
    }
}
