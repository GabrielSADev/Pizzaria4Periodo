package br.com.uniamerica.pizzariaback.controller;
import br.com.uniamerica.pizzariaback.dto.FuncionarioDTO;
import br.com.uniamerica.pizzariaback.entity.Funcionario;
import br.com.uniamerica.pizzariaback.repository.FuncionarioRep;
import br.com.uniamerica.pizzariaback.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/funcionario")
@CrossOrigin(origins = "http://localhost:4200")
public class FuncionarioController {

    @Autowired
    private FuncionarioRep funcionarioRep;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findByIdPath(@PathVariable("id") final Long id){
        final Funcionario funcionario = this.funcionarioRep.findById(id).orElse(null);
        return ResponseEntity.ok(funcionario);
    }
    @GetMapping
    public ResponseEntity <List<Funcionario>> listaFuncionarios(){
        return ResponseEntity.ok(this.funcionarioRep.findAll());
    }


    @PostMapping
    public ResponseEntity <String> cadastrarFuncionario(@RequestBody final FuncionarioDTO funcionarioDTO){
        try {
            funcionarioService.cadastrarFuncionario(funcionarioDTO);
            return new ResponseEntity<>( HttpStatus.OK);
    }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping
    public ResponseEntity<String> editaFunc(@RequestBody final FuncionarioDTO funcionarioDTO){
        try {
            this.funcionarioService.atualizaFuncionario(funcionarioDTO);

            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deletaFuncionario(@RequestParam("id") final Long id) {
        try {

            this.funcionarioService.excluirFuncionario(id);
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
