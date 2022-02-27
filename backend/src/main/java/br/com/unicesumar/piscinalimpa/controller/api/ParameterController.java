package br.com.unicesumar.piscinalimpa.controller.api;

import br.com.unicesumar.piscinalimpa.service.ParameterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/parameter")
public class ParameterController {

    private final ParameterService parameterService;

    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @GetMapping
    public ResponseEntity findAll() {
        var parameters = parameterService.findAll();
        return ResponseEntity.ok(parameters);
    }
}
