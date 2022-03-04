package br.com.unicesumar.piscinalimpa.controller.adm;

import br.com.unicesumar.piscinalimpa.dto.UserBackofficeDTO;
import br.com.unicesumar.piscinalimpa.exception.UserTypeNotAllowed;
import br.com.unicesumar.piscinalimpa.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adm/v1/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<List<UserBackofficeDTO>> findAll() {
        try {
            List<UserBackofficeDTO> usersBackoffice = userService.listAllUsers();
            return ResponseEntity.ok(usersBackoffice);
        } catch (Exception e) {
            log.error("Erro ao consultar lista de usuários {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
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
