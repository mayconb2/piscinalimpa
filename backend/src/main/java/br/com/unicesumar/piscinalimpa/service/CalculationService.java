package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.controller.utils.FormulaReplace;
import br.com.unicesumar.piscinalimpa.entity.Calculation;
import br.com.unicesumar.piscinalimpa.repository.CalculationRepository;
import com.udojava.evalex.Expression;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculationService {

    private final CalculationRepository calculationRepository;

    public CalculationService(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    public BigDecimal performCalculation(final Double volume, final Long productId, final Long intervantionLevel) {

        Calculation calculation = calculationRepository.findByProductIdAndInterventionLevelId(productId, intervantionLevel)
                .orElseThrow(() -> new RuntimeException("Calculation not found"));

        String formula = FormulaReplace.replaceMultiplierAndVolumeIn(calculation.getFormula().getFormula(),
                calculation.getMultiplier(), volume);

        return new Expression(formula).eval();

    }
}
