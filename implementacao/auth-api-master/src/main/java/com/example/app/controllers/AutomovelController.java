package com.example.app.controllers;

//import com.example.app.models.automovel.Automovel;
import com.example.app.models.automovel.Automovel;
import com.example.app.services.AutomovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("automovel")
public class AutomovelController {

    @Autowired
    private AutomovelService automovelService;

    @GetMapping
    public ResponseEntity<List<Automovel>> findAll(){
        List<Automovel> automovelList = this.automovelService.findAll();
        return ResponseEntity.ok(automovelList);
    }
}
