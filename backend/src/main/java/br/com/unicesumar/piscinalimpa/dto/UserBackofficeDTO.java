package br.com.unicesumar.piscinalimpa.dto;

import br.com.unicesumar.piscinalimpa.entity.UserType;

import java.io.Serializable;

public class UserBackofficeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String login;
    private String password;
    private String type;

    public UserBackofficeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
