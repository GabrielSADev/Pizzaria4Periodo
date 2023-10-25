package br.com.uniamerica.pizzariaback.controller;

import br.com.uniamerica.pizzariaback.dto.SaboresDTO;
import br.com.uniamerica.pizzariaback.entity.Sabores;
import br.com.uniamerica.pizzariaback.repository.SaboresRep;
import br.com.uniamerica.pizzariaback.service.SaboresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sabores")
@CrossOrigin(origins = "http://localhost:4200")
public class SaboresController {

   @Autowired
    private SaboresRep saboresRep;

   @Autowired
    private SaboresService saboresService;


    @GetMapping("/{id}")
    public ResponseEntity<Sabores> findByIdPath(@PathVariable("id") final Long id){
        final Sabores sabores = this.saboresRep.findById(id).orElse(null);
        return ResponseEntity.ok(sabores);
    }
    @GetMapping
    public ResponseEntity <List<Sabores>> listaSabores(){
        return ResponseEntity.ok(this.saboresRep.findAll());
    }

    @PostMapping
    public ResponseEntity <String> cadastrarSabores(@RequestBody final SaboresDTO saboresDTO){
        try {
            saboresService.cadastarSabor(saboresDTO);
            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping
    public ResponseEntity<String> editaSabor(@RequestBody final Sabores sabores){
        try {
            this.saboresService.atualizaSabor(sabores);

            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }


    @DeleteMapping
    public ResponseEntity<HttpStatus> deletaSabor(@RequestParam("id") final Long id) {
        try {
                this.saboresService.excluirSabor(id);
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
