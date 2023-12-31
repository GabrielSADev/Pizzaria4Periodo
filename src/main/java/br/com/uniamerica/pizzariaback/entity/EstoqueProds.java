package br.com.uniamerica.pizzariaback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estoqueProdutos", schema = "public")
public class EstoqueProds extends AbstractEntity{

    @Getter @Setter
    @Column(name = "precoProdutos", nullable = false)
    private float precoProdutos ;

    @Getter @Setter
    @Column (name = "nomeProduto", nullable = false, length = 40, unique = true)
    private String nomeProduto;

    public EstoqueProds(){

    }
    public EstoqueProds(Long id,float precoProdutos, String nomeProduto) {
        this.id = id;
        this.precoProdutos = precoProdutos;
        this.nomeProduto = nomeProduto;
    }
}

