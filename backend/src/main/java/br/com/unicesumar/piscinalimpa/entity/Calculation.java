package br.com.unicesumar.piscinalimpa.entity;


import javax.persistence.*;

@Entity(name = "calculation")
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "product")
    @ManyToOne
    private Product product;

    @JoinColumn(name = "intervention_level")
    @OneToOne
    private InterventionLevel interventionLevel;

    @JoinColumn(name = "formula")
    @ManyToOne
    private Formula formula;

    private Integer multiplier;

    public Calculation() {
    }

    public Calculation(Long id, Product product, InterventionLevel interventionLevel, Formula formula, Integer multiplier) {
        this.id = id;
        this.product = product;
        this.interventionLevel = interventionLevel;
        this.formula = formula;
        this.multiplier = multiplier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public InterventionLevel getInterventionLevel() {
        return interventionLevel;
    }

    public void setInterventionLevel(InterventionLevel interventionLevel) {
        this.interventionLevel = interventionLevel;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Integer getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Integer multiplier) {
        this.multiplier = multiplier;
    }
}
