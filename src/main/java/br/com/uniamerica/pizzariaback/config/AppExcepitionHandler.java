package br.com.uniamerica.pizzariaback.config;

import br.com.uniamerica.pizzariaback.dto.MensagemDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExcepitionHandler {
/*
    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<MensagemDTO> usernameNotFoundException(UsernameNotFoundException ex){
        MensagemDTO menssage = new MensagemDTO(ex.getMessage());
        return new ResponseEntity<>(menssage, HttpStatus.UNAUTHORIZED);
    }

 */
}
