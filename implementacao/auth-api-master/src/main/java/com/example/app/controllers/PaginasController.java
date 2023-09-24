package com.example.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginasController {

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }
    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }

    @GetMapping("/agente")
    public String agente(Model model){
        return "agente";
    }

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }
    @GetMapping("/cliente/perfil")
    public String perfil(Model model){
        return "perfil";
    }


    @GetMapping("/cliente/verpedidos")
    public String pedidos(Model model){
        return "verpedidos";
    }
    @GetMapping("/agente/verpedidos")
    public String agentepedidos(Model model){
        return "agentePedidos";
    }
}
