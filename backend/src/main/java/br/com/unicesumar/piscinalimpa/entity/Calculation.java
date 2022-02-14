package br.com.unicesumar.piscinalimpa.entity;


import javax.persistence.*;

@Entity(name = "calculation")
public class Calculation {

    @Id
    private Long id;

    @JoinColumn(name = "product")
    @ManyToOne
    private Product product;

    @JoinColumn(name = "intervention_level")
    @OneToOne
    private InterventionLevel interventionLevel;

    public Calculation() {
    }

    public Calculation(Long id, Product product, InterventionLevel interventionLevel) {
        this.id = id;
        this.product = product;
        this.interventionLevel = interventionLevel;
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
}
