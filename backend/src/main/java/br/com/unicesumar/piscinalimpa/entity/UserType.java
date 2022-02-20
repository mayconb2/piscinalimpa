package br.com.unicesumar.piscinalimpa.entity;

public enum UserType {

    ADMIN("ADMIN"),
    USER("USER");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    public static UserType getUserType(String userType) {
        switch (userType) {
            case "ADMIN" : return ADMIN;
            case "USER" : return USER;
            default: throw new RuntimeException("Tipo de usuário:: " + userType + " não é válido!");
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
