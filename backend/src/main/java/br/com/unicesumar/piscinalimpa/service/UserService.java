package br.com.unicesumar.piscinalimpa.service;

import br.com.unicesumar.piscinalimpa.dto.UserBackofficeDTO;
import br.com.unicesumar.piscinalimpa.dto.UserBackofficeForm;
import br.com.unicesumar.piscinalimpa.entity.UserBackoffice;
import br.com.unicesumar.piscinalimpa.entity.UserType;
import br.com.unicesumar.piscinalimpa.exception.NotFoundException;
import br.com.unicesumar.piscinalimpa.exception.PasswordDoesntMatchException;
import br.com.unicesumar.piscinalimpa.exception.UserTypeNotAllowed;
import br.com.unicesumar.piscinalimpa.repository.UserBackofficeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserBackofficeRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public UserService(UserBackofficeRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    public List<UserBackofficeDTO> listAllUsers() {
        List<UserBackoffice> listUsers = userRepository.findAll();
        return listUsers.stream()
                .map(user -> mapper.map(user, UserBackofficeDTO.class))
                .collect(Collectors.toList());
    }

    public UserBackoffice createUser(UserBackofficeDTO dto) {

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        if(!(dto.getType().equalsIgnoreCase(UserType.USER.getType()) || dto.getType().equalsIgnoreCase(UserType.ADMIN.getType()))) {
            throw new UserTypeNotAllowed("Tipo de usuário não permitido:: " + dto.getType());
        }

        return userRepository.save(mapper.map(dto, UserBackoffice.class));
    }

    public UserBackoffice findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível achar usuário com id: " + id));
    }

    public UserBackoffice update(Long id, UserBackofficeForm userForm) {

        if(!this.verifyOldPassword(id, userForm.getOldPassword())) {
            throw new PasswordDoesntMatchException("Password não confere!");
        }

        UserBackoffice user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível achar usuário com id: " + id));

        user.setPassword(passwordEncoder.encode(userForm.getNewPassword()));

        return this.userRepository.save(user);
    }

    public Boolean verifyOldPassword(Long id, String password) {
        UserBackoffice userBackoffice = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível achar usuário com id: " + id));

        return passwordEncoder.matches(password, userBackoffice.getPassword());
    }
}
