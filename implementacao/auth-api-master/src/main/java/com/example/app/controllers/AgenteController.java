package com.example.app.controllers;

import com.example.app.models.agente.Agente;
import com.example.app.models.automovel.Automovel;
import com.example.app.models.cliente.Cliente;
import com.example.app.models.pedidoAluguel.PedidoAluguel;
import com.example.app.models.pedidoAluguel.StatusAluguel;
import com.example.app.models.user.User;
import com.example.app.repositories.UserRepository;
import com.example.app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("agente")
public class AgenteController {
    @Autowired
    private PedidoAluguelService pedidoAluguelService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private AutomovelService automovelService;

    @Autowired
    private AgenteService agenteService;
    @Autowired
    private ProfissaoService profissaoService;

    @Autowired
    private UserRepository userRepository;

    //Avaliar Contrato
    @PutMapping("/avaliar/{id}")
    public ResponseEntity<Void> avaliarAluguel(@RequestBody Map<String,Object> request, @PathVariable Long id){
        String modificarStatus = String.valueOf(request.get("statusAluguel").toString());
        StatusAluguel status = StatusAluguel.fromDescription(modificarStatus);
        pedidoAluguelService.modificarStatus(status,id);
        return ResponseEntity.ok().build();

    }


//    Automoveis

    @PostMapping("/automovel")
    public ResponseEntity<?> create(@RequestBody Automovel obj){
        if(this.automovelService.findByPlaca(obj.getPlaca())!= null){
            return ResponseEntity.badRequest().body("A placa '" + obj.getPlaca() + "' já está em uso.");
        }
        this.automovelService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getMatricula()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/automovel/{id}")
    public ResponseEntity<Void> update(@RequestBody Automovel obj, @PathVariable String matricula){
        obj.setMatricula(matricula);
        obj = this.automovelService.update(obj);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/pedidorecusado/{id}")
    public ResponseEntity<Void> recusar(@RequestBody PedidoAluguel obj, @PathVariable Long id){
        PedidoAluguel newObj= pedidoAluguelService.findById(id);
        newObj.setStatus(obj.getStatus());
        this.pedidoAluguelService.update(newObj);
        return ResponseEntity.noContent().build();

    }
    @PutMapping("/pedidoaprovado/{id}")
    public ResponseEntity<Void> aprovar(@RequestBody PedidoAluguel obj, @PathVariable Long id){
        PedidoAluguel newObj= pedidoAluguelService.findById(id);
        newObj.setStatus(obj.getStatus());
        this.pedidoAluguelService.update(newObj);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("verpedidosSeparadas")
    public ResponseEntity<Map<String, Object>> teste() {
        List<Automovel> automovels = automovelService.findAll();
        List<Agente> agentes = agenteService.findAll();
        List<Cliente> clientes = clienteService.findAll();
        List<PedidoAluguel> pedidoAluguelList = pedidoAluguelService.findAll();
        List<User> userList = userRepository.findAll();

        List<Agente> agentesFiltrados = agentes.stream()
                .filter(agente -> pedidoAluguelList.stream()
                        .anyMatch(pedido -> pedido.getFornecedor().equals(agente.getUser())))
                .collect(Collectors.toList());

        List<Cliente> clientesFiltrados = clientes.stream()
                .filter(cliente -> pedidoAluguelList.stream()
                        .anyMatch(pedido -> pedido.getContratante().equals(cliente.getUser())))
                .collect(Collectors.toList());

        List<Automovel> automoveisFiltrados = automovels.stream()
                .filter(automovel -> pedidoAluguelList.stream()
                        .anyMatch(pedido -> pedido.getPlacaAutomovel().equals(automovel.getPlaca())))
                .collect(Collectors.toList());
        List<PedidoAluguel> pedidosFiltrados = pedidoAluguelList.stream()
                .filter(pedido -> automovels.stream()
                        .anyMatch(automovel -> automovel.getPlaca().equals(pedido.getPlacaAutomovel())))
                .collect(Collectors.toList());

        List<String> userFiltradoNomes = userList.stream()
                .filter(user -> pedidoAluguelList.stream()
                        .anyMatch(pedido -> pedido.getContratante().equals(user.getId())))
                .map(User::getName) // Mapeie User para o nome do usuário
                .collect(Collectors.toList());
        List<String> agenteFiltradoNomes = userList.stream()
                .filter(user -> pedidoAluguelList.stream()
                        .anyMatch(pedido -> pedido.getFornecedor().equals(user.getId())))
                .map(User::getName)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("agentes", agenteFiltradoNomes);
        response.put("clientes", userFiltradoNomes);
        response.put("pedidos", pedidosFiltrados);
        response.put("automoveis", automoveisFiltrados);

        return ResponseEntity.ok(response);
    }



}
