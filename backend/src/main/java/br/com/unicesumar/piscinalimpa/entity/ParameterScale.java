package br.com.unicesumar.piscinalimpa.entity;

import javax.persistence.*;

@Entity(name = "parameter_scale")
public class ParameterScale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer value;

    @JoinColumn(name = "intervention_level")
    @ManyToOne
    private InterventionLevel interventionLevel;

    @JoinColumn(name = "water_parameter")
    @ManyToOne
    private Parameter parameter;

    public ParameterScale() {
    }

    public ParameterScale(Long id, Integer value, InterventionLevel interventionLevel, Parameter parameter) {
        this.id = id;
        this.value = value;
        this.interventionLevel = interventionLevel;
        this.parameter = parameter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public InterventionLevel getInterventionLevel() {
        return interventionLevel;
    }

    public void setInterventionLevel(InterventionLevel interventionLevel) {
        this.interventionLevel = interventionLevel;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }
}
