package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.controller.utils.FormulaReplace;
import br.com.unicesumar.piscinalimpa.dto.ApplicationForm;
import br.com.unicesumar.piscinalimpa.dto.ApplicationSuggestionDTO;
import br.com.unicesumar.piscinalimpa.dto.CalculationDTO;
import br.com.unicesumar.piscinalimpa.dto.ProductDTO;
import br.com.unicesumar.piscinalimpa.entity.Calculation;
import br.com.unicesumar.piscinalimpa.entity.ParameterScale;
import br.com.unicesumar.piscinalimpa.exception.NotFoundException;
import br.com.unicesumar.piscinalimpa.repository.CalculationRepository;
import com.udojava.evalex.Expression;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CalculationService {

    private final CalculationRepository calculationRepository;
    private final ParameterScaleService parameterScaleService;
    private static final Long ACCEPTABLE_LEVEL_INTERVENTION = 4L;

    public CalculationService(CalculationRepository calculationRepository, ParameterScaleService parameterScaleService) {
        this.calculationRepository = calculationRepository;
        this.parameterScaleService = parameterScaleService;
    }

    public BigDecimal performCalculation(final Double volume, final Long productId, final Long intervantionLevel) {
        Calculation calculation = calculationRepository.findByProductIdAndInterventionLevelId(productId, intervantionLevel)
                .orElseThrow(() -> new NotFoundException("Calculation not found"));

        String formula = FormulaReplace.replaceMultiplierAndVolumeIn(calculation.getFormula().getFormula(),
                calculation.getMultiplier(), volume);

        return new Expression(formula).eval();
    }

    public List<ApplicationSuggestionDTO> suggestions(ApplicationForm applicationForms) {

//        List<ApplicationSuggestionDTO> suggestions = new ArrayList<>();
//
//        applicationForms.getProducts().forEach(product -> {
//            Calculation calculation = this.calculationRepository
//                    .findByProductIdAndInterventionLevelId(product.getId(), applicationForms.getInterventionLevel())
//                    .orElse(null);
//
//            String formula = FormulaReplace.replaceMultiplierAndVolumeIn(calculation.getFormula().getFormula(),
//                    calculation.getMultiplier(), applicationForms.getVolume());
//
//            BigDecimal productSuggestion = new Expression(formula).eval();
//
//            suggestions.add(new ApplicationSuggestionDTO(product, productSuggestion));
//        });
//
//        return suggestions;

        return null;
    }

    public CalculationDTO retrieveProductsSuggestion(ApplicationForm form) {
        Map<Long, Long> mapParaInterLvl = new HashMap<>();

        form.getParameters().forEach(parameterValues -> {
            ParameterScale pScale = this.parameterScaleService
                    .findByParameterIdAndValue(parameterValues.getParameterId(), parameterValues.getValue());
            mapParaInterLvl.put(parameterValues.getParameterId(), pScale.getInterventionLevel().getId());
        });

        List<ApplicationSuggestionDTO> suggestions = new ArrayList<>();

        form.getProducts().forEach(product -> {
            var interventationLvlId = mapParaInterLvl.get(product.getAffectedParameterId());

            Calculation calculation = this.calculationRepository
                    .findByProductIdAndInterventionLevelId(product.getId(), interventationLvlId)
                    .orElse(null);

            String formula = FormulaReplace.replaceMultiplierAndVolumeIn(calculation.getFormula().getFormula(),
                    calculation.getMultiplier(), form.getVolume());

            BigDecimal productSuggestion = new Expression(formula).eval().setScale(2, RoundingMode.HALF_EVEN);

            suggestions.add(new ApplicationSuggestionDTO(product, productSuggestion));
        });

        boolean hasMinimumProducts = verifyMinimumProducts(form);

        return new CalculationDTO(hasMinimumProducts, suggestions);
    }

    private boolean verifyMinimumProducts(ApplicationForm applicationForm) {
        List<Long> parametersWithInterventionLevel = new ArrayList<>();

        var parameters = applicationForm.getParameters();

        parameters.forEach(parameter -> {
            var parameterScale = parameterScaleService
                    .findByParameterIdAndValue(parameter.getParameterId(), parameter.getValue());

            if (!Objects.equals(parameterScale.getInterventionLevel().getId(), ACCEPTABLE_LEVEL_INTERVENTION)) {
                parametersWithInterventionLevel.add(parameterScale.getParameter().getId());
            }
        });

        var affectedParams = applicationForm.getProducts()
                .stream()
                .map(ProductDTO::getAffectedParameterId)
                .collect(Collectors.toList());

        //talvez criar um Map de boolean,listparams
        //se for falso, lista vazia. Se for true, contem os params que precisão de intervanção mas n tem produto
        //adicionar cmapo no dto com dtos dos parametros que precisam de intervenção mas n tem produto
        //retornar esse map
        return affectedParams.containsAll(parametersWithInterventionLevel);
    }
}
