package br.com.unicesumar.piscinalimpa.dto;

import java.math.BigDecimal;

public class ApplicationSuggestionDTO {

    private ProductFormDTO product;
    private BigDecimal suggestion;

    public ApplicationSuggestionDTO() {
    }

    public ApplicationSuggestionDTO(ProductFormDTO product, BigDecimal suggestion) {
        this.product = product;
        this.suggestion = suggestion;
    }

    public ProductFormDTO getProduct() {
        return product;
    }

    public void setProduct(ProductFormDTO product) {
        this.product = product;
    }

    public BigDecimal getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(BigDecimal suggestion) {
        this.suggestion = suggestion;
    }

}
