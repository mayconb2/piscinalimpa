package br.com.unicesumar.piscinalimpa.security.service;


import br.com.unicesumar.piscinalimpa.repository.UserBackofficeRepository;
import br.com.unicesumar.piscinalimpa.security.data.UserDetailData;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserBackofficeRepository userBackofficeRepository;

    public UserDetailServiceImpl(UserBackofficeRepository userBackofficeRepository) {
        this.userBackofficeRepository = userBackofficeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var userBackoffice = userBackofficeRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado:: " + username));

        return new UserDetailData(userBackoffice);

    }
}
