package br.com.uniamerica.pizzariaback.controller;
import br.com.uniamerica.pizzariaback.dto.EstoqueDTO;
import br.com.uniamerica.pizzariaback.entity.EstoqueProds;
import br.com.uniamerica.pizzariaback.repository.EstoqueProdRep;
import br.com.uniamerica.pizzariaback.service.EstoqueProdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/estoqueProd")
@CrossOrigin(origins = "http://localhost:4200")
public class EstoqueProdsController {

    @Autowired
    private EstoqueProdRep estoqueProdRep;

    @Autowired
    private EstoqueProdsService estoqueProdsService;

   @GetMapping("/{id}")
   public ResponseEntity<EstoqueProds> findByIdPath(@PathVariable("id") final Long id){
       final EstoqueProds estoqueProds = this.estoqueProdRep.findById(id).orElse(null);
       return ResponseEntity.ok(estoqueProds);
   }

    @GetMapping
    public ResponseEntity <List<EstoqueProds>> listaCompleta(){
        return ResponseEntity.ok(this.estoqueProdRep.findAll());
    }

    @PostMapping
    public ResponseEntity <String> cadastrarEstoque(@RequestBody final EstoqueDTO estoqueDTO){
        try {
            estoqueProdsService.cadastrarNoEstoque(estoqueDTO);
            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping
    public ResponseEntity<String> editarEstoque(@RequestBody final EstoqueProds estoqueProds){
        try {
            estoqueProdsService.atualizaEstoque(estoqueProds);
            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deletaNoEstoque(@RequestParam("id") final Long id) {
        try {
           this.estoqueProdsService.excluirProdEst(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }

    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }

   public static class RegistroNaoEncontradoException extends RuntimeException {
        public RegistroNaoEncontradoException(String message) {
            super(message);
        }
    }

}

