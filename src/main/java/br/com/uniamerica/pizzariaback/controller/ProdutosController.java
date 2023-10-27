package br.com.uniamerica.pizzariaback.controller;
import br.com.uniamerica.pizzariaback.dto.ProdutosDTO;
import br.com.uniamerica.pizzariaback.entity.Produtos;
import br.com.uniamerica.pizzariaback.repository.ProdutosRep;
import br.com.uniamerica.pizzariaback.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/produtos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdutosController {

    @Autowired
    ProdutosRep produtosRep;

    @Autowired
    ProdutosService produtosService;

    @GetMapping("/{id}")
    public ResponseEntity<Produtos> findByIdPath(@PathVariable("id") final Long id){
        final Produtos produtos = this.produtosRep.findById(id).orElse(null);
        return ResponseEntity.ok(produtos);
    }
    @GetMapping
    public ResponseEntity <List<Produtos>> listaProdutos(){
        return ResponseEntity.ok(this.produtosRep.findAll());
    }

    @PostMapping
    public ProdutosDTO cadastrarProdutos(@RequestBody final ProdutosDTO produtosDTO){
        try {
           return this.produtosService.cadastrarProduto(produtosDTO);
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return null;
        }
    }

    @PutMapping
    public ResponseEntity<String> edita( @RequestBody final ProdutosDTO produtosDTO){
        try {

            this.produtosService.atualizaProduto(produtosDTO);
            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaProdutos(@PathVariable Long id) {
        try {

            this.produtosService.excluirProduto(id);
            return ResponseEntity.ok("Produto Excluido Com Sucesso");
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }

}
