package br.com.unicesumar.piscinalimpa.dto;

public class UserBackofficeForm {

    private Long id;
    private String login;
    private String oldPassword;
    private String newPassword;

    public UserBackofficeForm() {
    }

    public UserBackofficeForm(Long id, String login, String oldPassword, String newPassword, String newPassword2) {
        this.id = id;
        this.login = login;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
