package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.dto.FormulaDTO;
import br.com.unicesumar.piscinalimpa.repository.FormulaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormulaService {

    private final FormulaRepository formulaRepository;
    private final ModelMapper mapper;

    public FormulaService(FormulaRepository formulaRepository, ModelMapper mapper) {
        this.formulaRepository = formulaRepository;
        this.mapper = mapper;
    }

    public List<FormulaDTO> findAll() {
        return this.formulaRepository.findAll().stream()
                .map(formula -> mapper.map(formula, FormulaDTO.class))
                .collect(Collectors.toList());
    }
}
