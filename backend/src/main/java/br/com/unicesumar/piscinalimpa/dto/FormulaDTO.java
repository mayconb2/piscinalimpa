package br.com.unicesumar.piscinalimpa.dto;

public class FormulaDTO {

    private Long id;
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
