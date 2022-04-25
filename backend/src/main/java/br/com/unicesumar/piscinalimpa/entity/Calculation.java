package br.com.unicesumar.piscinalimpa.entity;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = "calculation")
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "product")
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @JoinColumn(name = "intervention_level")
    @ManyToOne(fetch = FetchType.EAGER)
    private InterventionLevel interventionLevel;

    @JoinColumn(name = "formula")
    @ManyToOne(fetch = FetchType.EAGER)
    private Formula formula;

    private Double multiplier;

    public Calculation() {
    }

    public Calculation(Long id, Product product, InterventionLevel interventionLevel, Formula formula, Double multiplier) {
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

    public Double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }
}
