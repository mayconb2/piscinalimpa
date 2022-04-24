package br.com.unicesumar.piscinalimpa.controller.adm;

import br.com.unicesumar.piscinalimpa.dto.UserBackofficeDTO;
import br.com.unicesumar.piscinalimpa.dto.UserBackofficeForm;
import br.com.unicesumar.piscinalimpa.entity.UserBackoffice;
import br.com.unicesumar.piscinalimpa.exception.UserTypeNotAllowed;
import br.com.unicesumar.piscinalimpa.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adm/v1/user")
@Slf4j
public class UserAdmController {

    private final UserService userService;
    private final ModelMapper mapper;

    public UserAdmController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
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

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<UserBackofficeDTO> findById(@PathVariable Long id) {
        try {
            UserBackoffice userEntity = userService.findById(id);
            return  ResponseEntity.ok(this.mapper.map(userEntity, UserBackofficeDTO.class));
        } catch (Exception e) {
            log.error("Erro ao consultar usuário {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    @ApiOperation(value = "Bearer Token Needed", authorizations = { @Authorization(value="jwtToken") })
    public ResponseEntity<UserBackofficeDTO> updateUser(@PathVariable Long id, @RequestBody UserBackofficeForm userDTO) {
        try {
            UserBackoffice userEntity = this.userService.update(id, userDTO);
            return ResponseEntity.ok(this.mapper.map(userEntity, UserBackofficeDTO.class));
        } catch (Exception e) {
            log.error("Erro ao atualizar usuário: {}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
