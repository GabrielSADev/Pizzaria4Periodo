package br.com.uniamerica.pizzariaback.controller;

import br.com.uniamerica.pizzariaback.dto.PizzaDTO;
import br.com.uniamerica.pizzariaback.entity.Pizza;
import br.com.uniamerica.pizzariaback.repository.PizzaRep;
import br.com.uniamerica.pizzariaback.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pizza")
@CrossOrigin(origins = "http://localhost:4200")
public class PizzaController {

    @Autowired
    private PizzaRep pizzaRep;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> findByIdPath(@PathVariable("id") final Long id){
        final Pizza pizza = this.pizzaRep.findById(id).orElse(null);
        return ResponseEntity.ok(pizza);
    }

    @GetMapping
    public ResponseEntity <List<Pizza>> listaCompleta(){
        return ResponseEntity.ok(this.pizzaRep.findAll());
    }

    @PostMapping
    public PizzaDTO cadastrar (@RequestBody final PizzaDTO pizzaDTO){
    try {
        return this.pizzaService.cadastrarPizza(pizzaDTO);
    }
    catch (RuntimeException e){
        String errorMessage = getErrorMessage(e);
        return null;
        }
    }

    @PutMapping
    public ResponseEntity<String> editaPizza(@RequestBody final PizzaDTO pizzaDTO){
        try {
        this.pizzaService.atualizPizza(pizzaDTO);
            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleta(@PathVariable Long id) {
        try {

            this.pizzaService.excluirPizza(id);
            return ResponseEntity.ok("Pizza excluída!!");
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
