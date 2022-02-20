package br.com.unicesumar.piscinalimpa.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "user_backoffice")
public class UserBackoffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String type;

    public UserBackoffice() {
    }

    public UserBackoffice(Long id, String login, String password, String type) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.type = type;
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
