package br.com.uniamerica.pizzariaback.service;

import br.com.uniamerica.pizzariaback.dto.ProdutosDTO;
import br.com.uniamerica.pizzariaback.entity.EstoqueProds;
import br.com.uniamerica.pizzariaback.entity.Produtos;
import br.com.uniamerica.pizzariaback.repository.EstoqueProdRep;
import br.com.uniamerica.pizzariaback.repository.ProdutosRep;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class ProdutosService {

    @Autowired
    ProdutosRep produtosRep;
    @Autowired
    private EstoqueProdRep estoqueProdRep;

    @Transactional(rollbackFor = Exception.class)
    public ProdutosDTO cadastrarProduto (final ProdutosDTO produtosDTO){
        var produtos = new Produtos();
        BeanUtils.copyProperties(produtosDTO,produtos);

        Assert.isTrue(produtos.getQuantidadeprod() != 0, "A quantidade do produto não pode ser nula!!");
        Assert.isTrue(produtos.getEstoqueProds() != null, "O Produto não pode ser nulo!!");

        float total = 0;

        EstoqueProds estoqueProds = produtos.getEstoqueProds();
        Optional <EstoqueProds> estoqueTemp = estoqueProdRep.findById(estoqueProds.getId());
        total += estoqueTemp.get().getPrecoProdutos() * produtos.getQuantidadeprod();

        produtos.setTotalprod(total);

        Produtos produtosSalvo = this.produtosRep.save(produtos);
        ProdutosDTO produtosDTO2 = new ProdutosDTO();
        BeanUtils.copyProperties(produtosSalvo, produtosDTO2);
        return produtosDTO2;
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizaProduto(ProdutosDTO produtoDTO) {

        Produtos produtoExistente = this.produtosRep.findById(produtoDTO.getId()).orElse(null);


        if (produtoExistente != null) {

            BeanUtils.copyProperties(produtoDTO, produtoExistente);


            this.produtosRep.save(produtoExistente);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void excluirProduto(final Long id){

        final Produtos produtoBanco = this.produtosRep.findById(id).orElse(null);

        if (produtoBanco == null || !produtoBanco.getId().equals(id)){
            throw new RegistroNaoEncontradoException("Não foi possivel identificar o produto informado.");
        }
        this.produtosRep.delete(produtoBanco);
    }

    public static class RegistroNaoEncontradoException extends RuntimeException {
        public RegistroNaoEncontradoException(String message) {
            super(message);
        }
    }
}
