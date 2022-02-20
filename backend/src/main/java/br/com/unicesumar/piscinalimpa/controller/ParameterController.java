package br.com.unicesumar.piscinalimpa.controller;

import br.com.unicesumar.piscinalimpa.entity.Parameter;
import br.com.unicesumar.piscinalimpa.service.ParameterService;
import com.udojava.evalex.Expression;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/parameter")
public class ParameterController {

    private final ParameterService parameterService;

    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @GetMapping
    public List<Parameter> findAll() {
        return parameterService.findAll();
    }
}
