package br.com.unicesumar.piscinalimpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductFormDTO {

    private Long id;
    @JsonProperty(value = "affectedParameter")
    private Long affectedParameterId;
    private String name;
    @JsonProperty(value = "brand")
    private BrandDTO brandId;

    public ProductFormDTO() {
    }

    public ProductFormDTO(Long id, Long affectedParameterId, String name, BrandDTO brandId) {
        this.id = id;
        this.affectedParameterId = affectedParameterId;
        this.name = name;
        this.brandId = brandId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BrandDTO getBrandId() {
        return brandId;
    }

    public void setBrandId(BrandDTO brandId) {
        this.brandId = brandId;
    }
}
