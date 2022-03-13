package br.com.unicesumar.piscinalimpa.dto;

import java.util.List;

public class ApplicationForm {

    private Double volume;
    private List<ProductDTO> products;
    private List<ParameterValue> parameters;

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

    public List<ParameterValue> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterValue> parameters) {
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
