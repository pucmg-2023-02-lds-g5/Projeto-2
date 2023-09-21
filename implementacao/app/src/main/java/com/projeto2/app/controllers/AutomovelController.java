package com.projeto2.app.controllers;

import com.projeto2.app.models.Automovel;
import com.projeto2.app.services.AutomovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/automovel")
public class AutomovelController {
    @Autowired
    private AutomovelService automovelService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Automovel obj){
        if(this.automovelService.findByPlaca(obj.getPlaca())!= null){
            return ResponseEntity.badRequest().body("A placa '" + obj.getPlaca() + "' já está em uso.");
        }
        this.automovelService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getMatricula()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Automovel obj, @PathVariable Long matricula){
        obj.setMatricula(matricula);
        obj = this.automovelService.update(obj);
        return ResponseEntity.noContent().build();

    }
    @GetMapping
    public ResponseEntity<List<Automovel>> findAll(){
        List<Automovel> automovelList = this.automovelService.findAll();
        return ResponseEntity.ok(automovelList);
    }



}
