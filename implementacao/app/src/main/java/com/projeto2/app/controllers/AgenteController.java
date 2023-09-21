package com.projeto2.app.controllers;

import com.projeto2.app.models.Agente;
import com.projeto2.app.models.Cliente;
import com.projeto2.app.models.StatusAluguel;
import com.projeto2.app.models.Usuario;
import com.projeto2.app.services.AgenteService;
import com.projeto2.app.services.PedidoAluguelService;
import com.projeto2.app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agente")
public class AgenteController {
    @Autowired
    private UserService userService;

    @Autowired
    private AgenteService agenteService;

    @Autowired
    private PedidoAluguelService pedidoAluguelService;

    @GetMapping("/{id}")
    public ResponseEntity<Agente> findById(@PathVariable Long id){
        Agente obj = this.agenteService.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    @Validated(Usuario.CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Agente obj){
        this.agenteService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

//    @PutMapping("/{id}")
//    @Validated(Usuario.CreateUser.class)
//    public ResponseEntity<Void> update(@Valid @RequestBody Agente obj,@PathVariable Long id){
//
//        obj.setId(id);
//        obj = this.agenteService.update(obj);
//        return ResponseEntity.noContent().build();
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateName(@RequestBody String novoNome, @PathVariable Long id) {
        if (novoNome == null || novoNome.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Agente obj = this.agenteService.updateName(novoNome, id);

        if (obj == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.agenteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Agente>> findAll() {
        List<Agente> Agente = agenteService.findAll();
        return ResponseEntity.ok(Agente);
    }

    @PutMapping("/avaliar/{id}")
    public ResponseEntity<Void> avaliarAluguel(@RequestBody Map<String,Object> request,@PathVariable Long id){
        String modificarStatus = String.valueOf(request.get("statusAluguel").toString());
        StatusAluguel status = StatusAluguel.fromDescription(modificarStatus);
        pedidoAluguelService.modificarStatus(status,id);
        return ResponseEntity.ok().build();

    }


}
