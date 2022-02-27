package br.com.unicesumar.piscinalimpa.dto;

import java.util.List;

public class CalculationDTO {

    private Boolean hasMininumProducts;
    private List<ApplicationSuggestionDTO> applicationSuggestions;

    public CalculationDTO() {
    }

    public CalculationDTO(Boolean hasMininumProducts, List<ApplicationSuggestionDTO> applicationSuggestions) {
        this.hasMininumProducts = hasMininumProducts;
        this.applicationSuggestions = applicationSuggestions;
    }

    public Boolean getHasMininumProducts() {
        return hasMininumProducts;
    }

    public void setHasMininumProducts(Boolean hasMininumProducts) {
        this.hasMininumProducts = hasMininumProducts;
    }

    public List<ApplicationSuggestionDTO> getApplicationSuggestions() {
        return applicationSuggestions;
    }

    public void setApplicationSuggestions(List<ApplicationSuggestionDTO> applicationSuggestions) {
        this.applicationSuggestions = applicationSuggestions;
    }
}
