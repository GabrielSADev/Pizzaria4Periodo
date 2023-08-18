package br.com.uniamerica.pizzariaBack.service;

import br.com.uniamerica.pizzariaBack.dto.FuncionarioDTO;
import br.com.uniamerica.pizzariaBack.entity.Funcionario;
import br.com.uniamerica.pizzariaBack.repository.FuncionarioRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRep funcionarioRep;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrarFuncionario(final FuncionarioDTO funcionarioDTO){
        var funcionario = new Funcionario();
        BeanUtils.copyProperties(funcionarioDTO,funcionario);

        this.funcionarioRep.save(funcionario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaFuncionario(FuncionarioDTO funcionarioDTO) {

        Funcionario funcionarioExistente = this.funcionarioRep.findById(funcionarioDTO.getId()).orElse(null);


        if (funcionarioExistente != null) {

            BeanUtils.copyProperties(funcionarioDTO, funcionarioExistente);


            this.funcionarioRep.save(funcionarioExistente);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void excluirFuncionario(final Long id){

        final Funcionario funcionarioBanco = this.funcionarioRep.findById(id).orElse(null);

        if (funcionarioBanco == null || funcionarioBanco.getId()!=(id)){
            throw new RuntimeException("Não foi possivel identificar o funcionario informado.");
        }
        this.funcionarioRep.delete(funcionarioBanco);
    }

}
