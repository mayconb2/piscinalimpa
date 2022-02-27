package br.com.unicesumar.piscinalimpa.entity;

import javax.persistence.*;

@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "affected_parameter")
    @ManyToOne
    private Parameter affectedParameter;

    @JoinColumn(name = "brand")
    @ManyToOne
    private Brand brand;

    private String name;

    public Product() {
    }

    public Product(Long id, Parameter affectedParameter, Brand brand, String name) {
        this.id = id;
        this.affectedParameter = affectedParameter;
        this.brand = brand;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Parameter getAffectedParameter() {
        return affectedParameter;
    }

    public void setAffectedParameter(Parameter affectedParameter) {
        this.affectedParameter = affectedParameter;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
