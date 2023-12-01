package br.com.uniamerica.pizzariaback.service;

import br.com.uniamerica.pizzariaback.config.JwtServiceGenerator;
import br.com.uniamerica.pizzariaback.dto.LoginDTO;
import br.com.uniamerica.pizzariaback.dto.UserDTO;
import br.com.uniamerica.pizzariaback.entity.User;
import br.com.uniamerica.pizzariaback.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRep userRep;
    @Autowired
    private JwtServiceGenerator jwtServiceGenerator;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserDTO logar(LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = userRep.findByUsername(loginDTO.getUsername()).orElseThrow();
            var jwtToken = jwtServiceGenerator.generateToken(user);

            return toUserDTO(user, jwtToken);
        } catch (AuthenticationException e) {
            // Tratar falha na autenticação
            throw new RuntimeException("Falha na autenticação: " + e.getMessage());
        }
    }


    private UserDTO toUserDTO(User user, String token) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setRole(user.getRole());
        userDTO.setToken(token);
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

}
