package br.com.unicesumar.piscinalimpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "water_parameter")
public class Parameter {

    @Id
    private Long id;
    private String name;

    public Parameter() {
    }

    public Parameter(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
