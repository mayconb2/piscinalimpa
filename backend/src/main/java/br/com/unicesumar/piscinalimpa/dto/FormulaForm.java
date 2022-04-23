package br.com.unicesumar.piscinalimpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormulaForm {

    @JsonProperty(value = "formula")
    private String formula;

    public FormulaForm() {
    }

    public FormulaForm(String formula) {
        this.formula = formula;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
