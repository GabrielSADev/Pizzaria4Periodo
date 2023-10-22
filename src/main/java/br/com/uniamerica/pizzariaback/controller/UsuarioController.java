package br.com.uniamerica.pizzariaback.controller;
import br.com.uniamerica.pizzariaback.dto.UsuarioDTO;
import br.com.uniamerica.pizzariaback.entity.Usuario;
import br.com.uniamerica.pizzariaback.repository.UsuarioRep;
import br.com.uniamerica.pizzariaback.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRep usuarioRep;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findByIdPath(@PathVariable("id") final Long id){
        final Usuario usuario = this.usuarioRep.findById(id).orElse(null);
        return ResponseEntity.ok(usuario);
    }
    @GetMapping
    public ResponseEntity <List<Usuario>> listaUsers(){
        return ResponseEntity.ok(this.usuarioRep.findAll());
    }

    @GetMapping("/nome/{nomeUsuario}")
    public ResponseEntity<Usuario> nomeBusca(@PathVariable("nomeUsuario") String nomeUsuario){
        Usuario usuario = usuarioRep.findByNomeUsuario(nomeUsuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<String> cadastrarUser(@RequestBody final UsuarioDTO usuarioDTO){
        try {
            usuarioService.cadastraUsuario(usuarioDTO);
            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @PutMapping
    public ResponseEntity<String> editarUsuario( @RequestBody final Usuario usuario){
        try {
            this.usuarioService.atualizaUsuario(usuario);
            return new ResponseEntity<>( HttpStatus.OK);        }
        catch (RuntimeException e){
            String errorMessage = getErrorMessage(e);
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deletaUsuario(@RequestParam("id") final Long id) {
        try {
            this.usuarioService.excluirUsuario(id);
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

    public static class RegistroNaoEncontradoException extends RuntimeException {
        public RegistroNaoEncontradoException(String message) {
            super(message);
        }
    }


}
