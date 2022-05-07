package br.com.unicesumar.piscinalimpa.dto;

public class ParameterValue {

    private Long parameterId;
    private Integer value;

    public ParameterValue() {
    }

    public ParameterValue(Long parameterId, Integer value) {
        this.parameterId = parameterId;
        this.value = value;
    }

    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
