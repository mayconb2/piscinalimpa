package br.com.unicesumar.piscinalimpa.dto;

import java.util.List;

public class ApplicationForm {

    private Double volume;
    private List<ProductDTO> products;
    private List<ParameterValues> parameters;
//    private Long interventionLevel;

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public List<ParameterValues> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterValues> parameters) {
        this.parameters = parameters;
    }

    //    public Long getInterventionLevel() {
//        return interventionLevel;
//    }
//
//    public void setInterventionLevel(Long interventionLevel) {
//        this.interventionLevel = interventionLevel;
//    }
}
