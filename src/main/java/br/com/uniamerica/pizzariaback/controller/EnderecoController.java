package br.com.uniamerica.pizzariaback.controller;
import br.com.uniamerica.pizzariaback.dto.EnderecoDTO;
import br.com.uniamerica.pizzariaback.entity.Endereco;
import br.com.uniamerica.pizzariaback.repository.EnderecoRep;
import br.com.uniamerica.pizzariaback.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/api/endereco")
@CrossOrigin(origins = "http://localhost:4200")
public class EnderecoController {

    @Autowired
    private EnderecoRep enderecoRep;

    @Autowired
    private EnderecoService enderecoService;


    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findByIdPath(@PathVariable("id") final Long id){
        final Endereco endereco = this.enderecoRep.findById(id).orElse(null);
        return ResponseEntity.ok(endereco);
    }
    @GetMapping
    public ResponseEntity <List<Endereco>> listaEnderecos(){
        return ResponseEntity.ok(this.enderecoRep.findAll());
    }

    @PostMapping
    public ResponseEntity<String> cadastrarEndereco(@RequestBody final EnderecoDTO enderecoDTO){
        try {
            this.enderecoService.cadastrarEndereco(enderecoDTO);
            return new ResponseEntity<>( HttpStatus.OK);        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping
    public ResponseEntity<String> editaEnd(@RequestBody final EnderecoDTO enderecoDTO){
        try {
            this.enderecoService.atualizaEndereco(enderecoDTO);
            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deletaEnd(@RequestParam("id") final Long id){
        try {
            this.enderecoService.excluiEnd(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }

    private String getErrorMessage(Exception e) {
        return "Error: " + e.getMessage();
    }

}
