package br.com.uniamerica.pizzariaback.dto;
import br.com.uniamerica.pizzariaback.entity.Usuario;
import lombok.Data;

@Data
public class EnderecoDTO extends AbstractDTO{

    private String rua;

    private String bairro;

    private int numeroEnd;

    private Usuario usuario;

    public EnderecoDTO(){
    }

    public EnderecoDTO(String rua, String bairro, int numeroEnd, Usuario usuario) {
        this.rua = rua;
        this.bairro = bairro;
        this.numeroEnd = numeroEnd;
        this.usuario = usuario;
    }
}
