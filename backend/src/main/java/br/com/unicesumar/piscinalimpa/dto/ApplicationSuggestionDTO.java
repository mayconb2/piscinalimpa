package br.com.unicesumar.piscinalimpa.dto;

import java.math.BigDecimal;

public class ApplicationSuggestionDTO {

    private ProductDTO product;
    private BigDecimal suggestion;
//    private Boolean hasMinimumProducts;

    public ApplicationSuggestionDTO() {
    }

    public ApplicationSuggestionDTO(ProductDTO product, BigDecimal suggestion) {
        this.product = product;
        this.suggestion = suggestion;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public BigDecimal getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(BigDecimal suggestion) {
        this.suggestion = suggestion;
    }

//    public Boolean getHasMinimumProducts() {
//        return hasMinimumProducts;
//    }
//
//    public void setHasMinimumProducts(Boolean hasMinimumProducts) {
//        this.hasMinimumProducts = hasMinimumProducts;
//    }
}
