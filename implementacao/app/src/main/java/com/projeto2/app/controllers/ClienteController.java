package com.projeto2.app.controllers;

import com.projeto2.app.models.Agente;
import com.projeto2.app.models.Cliente;
import com.projeto2.app.models.Usuario;
import com.projeto2.app.services.ClienteService;
import com.projeto2.app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@Validated
public class ClienteController {
    @Autowired
    private UserService userService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Cliente obj = (Cliente)this.clienteService.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    @Validated(Usuario.CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Cliente obj){
        this.clienteService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
//    @PutMapping("/{id}")
//    @Validated(Usuario.CreateUser.class)
//    public ResponseEntity<Void> update(@Valid @RequestBody Cliente obj,@PathVariable Long id){
//
//        obj.setId(id);
//        obj = this.clienteService.update(obj);
//        return ResponseEntity.noContent().build();
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateName(
            @RequestBody String novoNome,
            @RequestParam String cpf,
            @RequestParam String rg,
            @PathVariable Long id
    ) {
        if (novoNome == null || novoNome.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }


        Cliente obj = this.clienteService.updateName(novoNome, cpf, rg, id);

        if (obj == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }
}
