package br.com.uniamerica.pizzariaback.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "endereco", schema = "public")
public class Endereco extends AbstractEntity{
    @Getter @Setter
    @Column (name = "rua", length = 30, nullable = false)
    private String rua;

    @Getter @Setter
    @Column (name = "bairro", nullable = false, length = 30)
    private String bairro;

    @Getter @Setter
    @Column (name = "numeroEnd", length = 5, nullable = false)
    private int numeroEnd;

    @Getter @Setter
    @ManyToOne
    @JsonBackReference
    private Usuario usuario;

    public Endereco(){

    }
    public Endereco(Long id,String rua, String bairro, int numeroEnd, Usuario usuario) {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.numeroEnd = numeroEnd;
        this.usuario = usuario;
    }
}
