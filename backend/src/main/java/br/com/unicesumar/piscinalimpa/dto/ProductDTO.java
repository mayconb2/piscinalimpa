package br.com.unicesumar.piscinalimpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {

    private Long id;
    @JsonProperty(value = "affectedParameter")
    private Long affectedParameterId;
    private String name;
    @JsonProperty(value = "brandId")
    private Long brandId;
    @JsonProperty(value = "brandName")
    private String brandName;

    public ProductDTO() {
    }

    public ProductDTO(Long id, Long affectedParameterId, String name, Long brandId, String brandName) {
        this.id = id;
        this.affectedParameterId = affectedParameterId;
        this.name = name;
        this.brandId = brandId;
        this.brandName = brandName;
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

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
