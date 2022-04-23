package br.com.unicesumar.piscinalimpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormulaDTO {

    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "formula")
    private String formula;

    public FormulaDTO() {
    }

    public FormulaDTO(Long id, String formula) {
        this.id = id;
        this.formula = formula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
