package br.com.unicesumar.piscinalimpa.controller;

import br.com.unicesumar.piscinalimpa.exception.UserTypeNotAllowed;
import br.com.unicesumar.piscinalimpa.dto.UserBackofficeDTO;
import br.com.unicesumar.piscinalimpa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/adm/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserBackofficeDTO>> findAll() {
        List<UserBackofficeDTO> usersBackoffice = userService.listAllUsers();
        return ResponseEntity.ok(usersBackoffice);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserBackofficeDTO userDTO) {

        try {
            var user = userService.createUser(userDTO);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (UserTypeNotAllowed e) {
            log.error("Tipo de usuário não permitido:: " + userDTO.getType());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tipo de usuário não permitido:: " + userDTO.getType());
        }

    }
}
