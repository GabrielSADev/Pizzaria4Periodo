package br.com.uniamerica.pizzariaback.entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "usuarios", schema = "public")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Usuario extends AbstractEntity{

    @Getter @Setter
    @Column (name = "nomeUsuario", length = 50, unique = true)
    private String nomeUsuario;

    @Getter @Setter
    @Column (name = "telefone", length = 15, unique = true)
    private String telefone;
    @Getter @Setter
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    public Usuario(){}

    public Usuario(Long id,String nomeUsuario, String telefone) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.telefone = telefone;
    }
}

