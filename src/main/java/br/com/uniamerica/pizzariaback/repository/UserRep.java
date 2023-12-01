package br.com.uniamerica.pizzariaback.repository;

import br.com.uniamerica.pizzariaback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRep extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String login);

}
