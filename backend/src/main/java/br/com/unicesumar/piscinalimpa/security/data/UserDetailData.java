package br.com.unicesumar.piscinalimpa.security.data;

import br.com.unicesumar.piscinalimpa.entity.UserBackoffice;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailData implements UserDetails {

    private final UserBackoffice userBackoffice;

    public UserDetailData(UserBackoffice userBackoffice) {
        this.userBackoffice = userBackoffice;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return userBackoffice.getPassword();
    }

    @Override
    public String getUsername() {
        return userBackoffice.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
