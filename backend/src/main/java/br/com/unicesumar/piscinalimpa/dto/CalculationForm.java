package br.com.unicesumar.piscinalimpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalculationForm {

    private Long id;
    private Double multiplier;
    @JsonProperty(value = "product")
    private Long productId;
    @JsonProperty(value = "interventionLevel")
    private Long interventionLevelId;
    @JsonProperty(value = "formula")
    private Long formulaId;

    public CalculationForm() {
    }

    public CalculationForm(Long id, Double multiplier, Long productId, Long interventionLevelId, Long formulaId) {
        this.id = id;
        this.multiplier = multiplier;
        this.productId = productId;
        this.interventionLevelId = interventionLevelId;
        this.formulaId = formulaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getInterventionLevelId() {
        return interventionLevelId;
    }

    public void setInterventionLevelId(Long interventionLevelId) {
        this.interventionLevelId = interventionLevelId;
    }

    public Long getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(Long formulaId) {
        this.formulaId = formulaId;
    }
}
