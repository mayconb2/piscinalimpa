package br.com.unicesumar.piscinalimpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductEagerDTO {
    private Long id;
    @JsonProperty(value = "affectedParameter")
    private Long affectedParameterId;
    private String name;
    @JsonProperty(value = "brand")
    private BrandDTO brand;

    public ProductEagerDTO() {
    }

    public ProductEagerDTO(Long id, Long affectedParameterId, String name, BrandDTO brand) {
        this.id = id;
        this.affectedParameterId = affectedParameterId;
        this.name = name;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAffectedParameterId() {
        return affectedParameterId;
    }

    public void setAffectedParameterId(Long affectedParameterId) {
        this.affectedParameterId = affectedParameterId;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
