package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.dto.ApplicationForm;
import br.com.unicesumar.piscinalimpa.dto.BrandDTO;
import br.com.unicesumar.piscinalimpa.dto.CalculationDTO;
import br.com.unicesumar.piscinalimpa.dto.ParameterValue;
import br.com.unicesumar.piscinalimpa.dto.ProductFormDTO;
import br.com.unicesumar.piscinalimpa.entity.Brand;
import br.com.unicesumar.piscinalimpa.entity.Calculation;
import br.com.unicesumar.piscinalimpa.entity.Formula;
import br.com.unicesumar.piscinalimpa.entity.InterventionLevel;
import br.com.unicesumar.piscinalimpa.entity.Parameter;
import br.com.unicesumar.piscinalimpa.entity.ParameterScale;
import br.com.unicesumar.piscinalimpa.entity.Product;
import br.com.unicesumar.piscinalimpa.repository.CalculationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculationServiceTest {

    @Mock
    ParameterScaleService parameterScaleService;

    @Mock
    CalculationRepository calculationRepository;

    @InjectMocks
    CalculationService calculationService;

    @Test
    void when_receice_all_parameters_and_has_products_and_needs_apply_cl_then_should_return_cl_calculation() {

        //given
        ApplicationForm form = getApplicationFormNeedsChlorineAndHas();
        ParameterScale param1 = new ParameterScale(1L, 0, getHighIntervention(), getChlorine());
        ParameterScale param2 = new ParameterScale(2L, 74, getNoIntervention(), getpH());
        ParameterScale param3 = new ParameterScale(3L, 100, getNoIntervention(), getAlca());
        ParameterScale param4 = new ParameterScale(4L, 0, getNoIntervention(), getTurb());

        //when
        when(parameterScaleService.findByParameterIdAndValue(1L, 0)).thenReturn(param1);
        when(parameterScaleService.findByParameterIdAndValue(2L, 74)).thenReturn(param2);
        when(parameterScaleService.findByParameterIdAndValue(3L, 100)).thenReturn(param3);
        when(parameterScaleService.findByParameterIdAndValue(4L, 0)).thenReturn(param4);
        when(calculationRepository.findByProductIdAndInterventionLevelId(1L, 3L)).thenReturn(getCalculationChlornyCalculationHightIntervention());

        CalculationDTO calculationDTO = calculationService.retrieveProductsSuggestion(form);

        //then
        assertEquals(true, calculationDTO.getHasMininumProducts());
        assertEquals(1, calculationDTO.getApplicationSuggestions().size());
        verify(parameterScaleService, times(8)).findByParameterIdAndValue(any(),any());

    }

    @Test
    void when_receice_all_parameters_and_has_products_and_needs_apply_cl_and_ph_then_should_return_cl_calculation_and_ph() {

        //given
        ApplicationForm form = getApplicationFormNeedsChlorineAndPhAndHas();
        ParameterScale param1 = new ParameterScale(1L, 0, getHighIntervention(), getChlorine());
        ParameterScale param2 = new ParameterScale(2L, 70, getMediumervention(), getpH());
        ParameterScale param3 = new ParameterScale(3L, 100, getNoIntervention(), getAlca());
        ParameterScale param4 = new ParameterScale(4L, 0, getNoIntervention(), getTurb());

        //when
        when(parameterScaleService.findByParameterIdAndValue(1L, 0)).thenReturn(param1);
        when(parameterScaleService.findByParameterIdAndValue(2L, 70)).thenReturn(param2);
        when(parameterScaleService.findByParameterIdAndValue(3L, 100)).thenReturn(param3);
        when(parameterScaleService.findByParameterIdAndValue(4L, 0)).thenReturn(param4);
        when(calculationRepository.findByProductIdAndInterventionLevelId(1L, 3L)).thenReturn(getCalculationChlornyCalculationHightIntervention());
        when(calculationRepository.findByProductIdAndInterventionLevelId(2L, 2L)).thenReturn(getCalculationPHCalculationMediumIntervention());

        CalculationDTO calculationDTO = calculationService.retrieveProductsSuggestion(form);

        //then
        assertEquals(true, calculationDTO.getHasMininumProducts());
        assertEquals(2, calculationDTO.getApplicationSuggestions().size());
        verify(parameterScaleService, times(8)).findByParameterIdAndValue(any(),any());

    }

    @Test
    void when_receice_all_parameters_and_dont_hass_all_products_and_needs_apply_cl_and_ph_then_should_return_cl_calculation_warning() {

        //given
        ApplicationForm form = getApplicationFormNeedsChlorineAndPhAndDontHasPh();
        ParameterScale param1 = new ParameterScale(1L, 0, getHighIntervention(), getChlorine());
        ParameterScale param2 = new ParameterScale(2L, 70, getMediumervention(), getpH());
        ParameterScale param3 = new ParameterScale(3L, 100, getNoIntervention(), getAlca());
        ParameterScale param4 = new ParameterScale(4L, 0, getNoIntervention(), getTurb());

        //when
        when(parameterScaleService.findByParameterIdAndValue(1L, 0)).thenReturn(param1);
        when(parameterScaleService.findByParameterIdAndValue(2L, 70)).thenReturn(param2);
        when(parameterScaleService.findByParameterIdAndValue(3L, 100)).thenReturn(param3);
        when(parameterScaleService.findByParameterIdAndValue(4L, 0)).thenReturn(param4);
        when(calculationRepository.findByProductIdAndInterventionLevelId(1L, 3L)).thenReturn(getCalculationChlornyCalculationHightIntervention());
//        when(calculationRepository.findByProductIdAndInterventionLevelId(2L, 2L)).thenReturn(getCalculationPHCalculationMediumIntervention());

        CalculationDTO calculationDTO = calculationService.retrieveProductsSuggestion(form);

        //then
        assertEquals(false, calculationDTO.getHasMininumProducts());
        assertEquals(1, calculationDTO.getApplicationSuggestions().size());
        verify(parameterScaleService, times(8)).findByParameterIdAndValue(any(),any());

    }

    private Parameter getChlorine() {
        return new Parameter(1L, "Cloro");
    }

    private Parameter getpH() {
        return new Parameter(2L, "pH");
    }

    private Parameter getAlca() {
        return new Parameter(3L, "Alcalinidade");
    }

    private Parameter getTurb() {
        return new Parameter(4L, "Tubridez");
    }

    private InterventionLevel getHighIntervention() {
        return new InterventionLevel(3L, "intensa");
    }

    private InterventionLevel getLowIntervention() {
        return new InterventionLevel(1L, "pouca");
    }

    private InterventionLevel getMediumervention() {
        return new InterventionLevel(2L, "moderada");
    }

    private InterventionLevel getNoIntervention() {
        return new InterventionLevel(4L, "nenhuma");
    }

    private Brand getAnyBrand() {
        return new Brand(1L, "Marca qualquer");
    }

    private ApplicationForm getApplicationFormNeedsChlorineAndPhAndHas() {
        
        ApplicationForm form = new ApplicationForm();

        //bad params
        var cl = new ParameterValue(1L, 0);
        var ph = new ParameterValue(2L, 70);
        //good parameters
        var alc = new ParameterValue(3L, 100);
        var turb = new ParameterValue(4L, 0);
        List<ParameterValue> parameterValues = Arrays.asList(cl, ph, alc, turb);

        //products
        var product1 = new ProductFormDTO(1L, 1L, "Cloro", new BrandDTO());
        var product2 = new ProductFormDTO(2L, 2L, "Elevador Ph", new BrandDTO());
        List<ProductFormDTO> productFormDTOS = Arrays.asList(product1, product2);

        form.setParameters(parameterValues);
        form.setProducts(productFormDTOS);
        form.setVolume(25.00);
        
        return form;
    }

    private ApplicationForm getApplicationFormNeedsChlorineAndPhAndDontHasPh() {

        ApplicationForm form = new ApplicationForm();

        //bad params
        var cl = new ParameterValue(1L, 0);
        var ph = new ParameterValue(2L, 70);
        //good parameters
        var alc = new ParameterValue(3L, 100);
        var turb = new ParameterValue(4L, 0);
        List<ParameterValue> parameterValues = Arrays.asList(cl, ph, alc, turb);

        //products
        var product1 = new ProductFormDTO(1L, 1L, "Cloro", new BrandDTO());
        List<ProductFormDTO> productFormDTOS = Arrays.asList(product1);

        form.setParameters(parameterValues);
        form.setProducts(productFormDTOS);
        form.setVolume(25.00);

        return form;
    }

    private ApplicationForm getApplicationFormNeedsChlorineAndHas() {

        ApplicationForm form = new ApplicationForm();

        //bad params
        var cl = new ParameterValue(1L, 0);
        //good parameters
        var ph = new ParameterValue(2L, 74);
        var alc = new ParameterValue(3L, 100);
        var turb = new ParameterValue(4L, 0);
        List<ParameterValue> parameterValues = Arrays.asList(cl, ph, alc, turb);

        //products
        var product1 = new ProductFormDTO(1L, 1L, "Cloro", new BrandDTO());
        List<ProductFormDTO> productFormDTOS = Arrays.asList(product1);

        form.setParameters(parameterValues);
        form.setProducts(productFormDTOS);
        form.setVolume(25.00);

        return form;
    }

    private Optional<Calculation> getCalculationChlornyCalculationHightIntervention() {

        Calculation highInterventionChloriny = new Calculation();

        highInterventionChloriny.setId(1L);
        highInterventionChloriny.setProduct(new Product(1L, getChlorine(),getAnyBrand(), "Cloro"));
        highInterventionChloriny.setInterventionLevel(getHighIntervention());
        highInterventionChloriny.setFormula(new Formula(1L, "(m*v)"));
        highInterventionChloriny.setMultiplier(25.0);

        return Optional.of(highInterventionChloriny);
    }

    private Optional<Calculation> getCalculationPHCalculationNoIntervention() {

        Calculation noInterventionPH = new Calculation();

        noInterventionPH.setId(2L);
        noInterventionPH.setProduct(new Product(2L, getpH(),getAnyBrand(), "Eleveador Ph"));
        noInterventionPH.setInterventionLevel(getNoIntervention());
        noInterventionPH.setFormula(new Formula(1L, "(m*v)"));
        noInterventionPH.setMultiplier(0.0);

        return Optional.of(noInterventionPH);
    }

    private Optional<Calculation> getCalculationPHCalculationMediumIntervention() {

        Calculation mediumInterventionPH = new Calculation();

        mediumInterventionPH.setId(2L);
        mediumInterventionPH.setProduct(new Product(2L, getpH(),getAnyBrand(), "Eleveador Ph"));
        mediumInterventionPH.setInterventionLevel(getMediumervention());
        mediumInterventionPH.setFormula(new Formula(1L, "(m*v)"));
        mediumInterventionPH.setMultiplier(2.0);

        return Optional.of(mediumInterventionPH);
    }

    private Optional<Calculation> getCalculationAlcaCalculationNoIntervention() {

        Calculation noInterventionAlca = new Calculation();

        noInterventionAlca.setId(3L);
        noInterventionAlca.setProduct(new Product(3L, getAlca(), getAnyBrand(), "Eleveador Alcalinidade"));
        noInterventionAlca.setInterventionLevel(getNoIntervention());
        noInterventionAlca.setFormula(new Formula(1L, "(m*v)"));
        noInterventionAlca.setMultiplier(0.0);

        return Optional.of(noInterventionAlca);
    }

    private Optional<Calculation> getCalculationTurbCalculationNoIntervention() {

        Calculation noInterventionTurb = new Calculation();

        noInterventionTurb.setId(4L);
        noInterventionTurb.setProduct(new Product(4L, getTurb(), getAnyBrand(), "Floculante"));
        noInterventionTurb.setInterventionLevel(getNoIntervention());
        noInterventionTurb.setFormula(new Formula(1L, "(m*v)"));
        noInterventionTurb.setMultiplier(0.0);

        return Optional.of(noInterventionTurb);
    }
}
