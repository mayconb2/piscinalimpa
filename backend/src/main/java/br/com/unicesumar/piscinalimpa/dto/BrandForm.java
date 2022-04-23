package br.com.unicesumar.piscinalimpa.dto;

public class BrandForm {

    private String name;

    public BrandForm(String name) {
        this.name = name;
    }

    public BrandForm() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
