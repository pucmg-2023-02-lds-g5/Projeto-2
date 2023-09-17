package com.projeto2.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Paginas {
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }
    @GetMapping("/crud")
    public String criarCliente(Model model){
        return "criar";
    }
}
