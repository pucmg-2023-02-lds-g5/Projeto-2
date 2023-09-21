package com.projeto2.app.controllers;

import com.projeto2.app.models.*;
import com.projeto2.app.services.*;
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
@RequestMapping("/cliente")
@Validated
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private AutomovelService automovelService;

    @Autowired
    private AgenteService agenteService;

    @Autowired
    private PedidoAluguelService pedidoAluguelService;
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

    //aluguel
    @PostMapping("/pedido")
    public ResponseEntity<PedidoAluguel> introduzirAluguel(@RequestBody Map<String, Object> request) {
        String placa = String.valueOf(request.get("placa").toString());
        Long clienteId = Long.valueOf(request.get("clienteId").toString());
        Long fornecedorId = Long.valueOf(request.get("fornecedorId").toString());
        Automovel automovel = this.automovelService.findByPlaca(placa);
        PedidoAluguel newObj = new PedidoAluguel();
        newObj.setAutomovel(automovel);
        newObj.setStatus(StatusAluguel.Solicitado);
        newObj.setContratante(this.clienteService.findById(clienteId));
        newObj.setFornecedor(this.agenteService.findById(fornecedorId));
        this.pedidoAluguelService.create(newObj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<Void> cancelarAluguel(@PathVariable Long id){
        this.pedidoAluguelService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
