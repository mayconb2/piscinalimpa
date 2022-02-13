package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.entity.Parameter;
import br.com.unicesumar.piscinalimpa.repository.ParameterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterService {

    private final ParameterRepository parameterRepository;


    public ParameterService(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    public List<Parameter> findAll() {
        return parameterRepository.findAll();
    }
}
