package br.com.uniamerica.pizzariaback.controller;

import br.com.uniamerica.pizzariaback.dto.LoginDTO;
import br.com.uniamerica.pizzariaback.dto.UserDTO;
import br.com.uniamerica.pizzariaback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> logar(@RequestBody LoginDTO loginDTO) {
        try {
            return ResponseEntity.ok(userService.logar(loginDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("deslogar")
    public ResponseEntity<HttpStatus> logout() {

        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(null, HttpStatus.OK);

    }

}
