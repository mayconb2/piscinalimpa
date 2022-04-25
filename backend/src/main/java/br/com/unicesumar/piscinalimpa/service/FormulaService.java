package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.dto.FormulaDTO;
import br.com.unicesumar.piscinalimpa.dto.FormulaForm;
import br.com.unicesumar.piscinalimpa.entity.Formula;
import br.com.unicesumar.piscinalimpa.exception.NotFoundException;
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

    public FormulaDTO create(FormulaForm formulaForm) {
        Formula formulaEntity = this.mapper.map(formulaForm, Formula.class);
        Formula formulaSaved = this.formulaRepository.save(formulaEntity);
        return this.mapper.map(formulaSaved, FormulaDTO.class);
    }

    public Formula findById(Long id) {
        return this.formulaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível achar fórmula com id: " + id));
    }

    public Formula update(Long id, FormulaForm dto) {
        Formula formula = this.formulaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível achar fórmula com id: " + id));

        formula.setFormula(dto.getFormula());

        return this.formulaRepository.save(formula);
    }

    public void deleteById(Long id) {
        this.formulaRepository.deleteById(id);
    }
}
