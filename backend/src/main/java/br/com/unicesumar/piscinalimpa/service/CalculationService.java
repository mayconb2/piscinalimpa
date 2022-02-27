package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.controller.utils.FormulaReplace;
import br.com.unicesumar.piscinalimpa.dto.ApplicationForm;
import br.com.unicesumar.piscinalimpa.dto.ApplicationSuggestionDTO;
import br.com.unicesumar.piscinalimpa.entity.Calculation;
import br.com.unicesumar.piscinalimpa.exception.NotFoundException;
import br.com.unicesumar.piscinalimpa.repository.CalculationRepository;
import com.udojava.evalex.Expression;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculationService {

    private final CalculationRepository calculationRepository;

    public CalculationService(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    public BigDecimal performCalculation(final Double volume, final Long productId, final Long intervantionLevel) {

        Calculation calculation = calculationRepository.findByProductIdAndInterventionLevelId(productId, intervantionLevel)
                .orElseThrow(() -> new NotFoundException("Calculation not found"));

        String formula = FormulaReplace.replaceMultiplierAndVolumeIn(calculation.getFormula().getFormula(),
                calculation.getMultiplier(), volume);

        return new Expression(formula).eval();

    }

    public List<ApplicationSuggestionDTO> suggestions(ApplicationForm applicationForms) {

        List<ApplicationSuggestionDTO> suggestions = new ArrayList<>();

        applicationForms.getProducts().forEach(product -> {
            Calculation calculation = this.calculationRepository
                    .findByProductIdAndInterventionLevelId(product.getId(), applicationForms.getInterventionLevel())
                    .orElse(null);

            String formula = FormulaReplace.replaceMultiplierAndVolumeIn(calculation.getFormula().getFormula(),
                    calculation.getMultiplier(), applicationForms.getVolume());

            BigDecimal productSuggestion = new Expression(formula).eval();

            suggestions.add(new ApplicationSuggestionDTO(product, productSuggestion));
        });

        return suggestions;
    }
}
