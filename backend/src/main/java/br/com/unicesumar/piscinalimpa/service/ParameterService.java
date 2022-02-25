package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.dto.ParameterDTO;
import br.com.unicesumar.piscinalimpa.entity.Parameter;
import br.com.unicesumar.piscinalimpa.repository.ParameterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParameterService {

    private final ParameterRepository parameterRepository;
    private final ModelMapper mapper;

    public ParameterService(ParameterRepository parameterRepository, ModelMapper mapper) {
        this.parameterRepository = parameterRepository;
        this.mapper = mapper;
    }

    public List<ParameterDTO> findAll() {
        var allParameters = parameterRepository.findAll();
        return allParameters.stream().map(parameter -> mapper.map(parameter, ParameterDTO.class))
                .collect(Collectors.toList());
    }
}
