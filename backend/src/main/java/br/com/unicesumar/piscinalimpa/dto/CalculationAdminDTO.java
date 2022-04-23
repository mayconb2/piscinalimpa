package br.com.unicesumar.piscinalimpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalculationAdminDTO {

    private Long id;
    private Integer multiplier;
    private ProductEagerDTO product;
    @JsonProperty(value = "interventionLevel")
    private InterventionLevelDTO interventionLevel;
    @JsonProperty(value = "formula")
    private FormulaDTO formula;

    public CalculationAdminDTO() {
    }

    public CalculationAdminDTO(Long id, Integer multiplier, ProductEagerDTO product, InterventionLevelDTO interventionLevel, FormulaDTO formulaDTO) {
        this.id = id;
        this.multiplier = multiplier;
        this.product = product;
        this.interventionLevel = interventionLevel;
        this.formula = formulaDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
    }

    public ProductEagerDTO getProduct() {
        return product;
    }

    public void setProduct(ProductEagerDTO product) {
        this.product = product;
    }

    public InterventionLevelDTO getInterventionLevel() {
        return interventionLevel;
    }

    public void setInterventionLevel(InterventionLevelDTO interventionLevel) {
        this.interventionLevel = interventionLevel;
    }

    public FormulaDTO getFormula() {
        return formula;
    }

    public void setFormula(FormulaDTO formulaDTO) {
        this.formula = formulaDTO;
    }
}
