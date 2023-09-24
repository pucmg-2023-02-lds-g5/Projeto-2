package com.example.app.controllers;

//import com.example.app.models.automovel.Automovel;
import com.example.app.models.agente.Agente;
import com.example.app.models.automovel.Automovel;
import com.example.app.models.cliente.Cliente;
import com.example.app.models.pedidoAluguel.PedidoAluguel;
import com.example.app.models.pedidoAluguel.StatusAluguel;
import com.example.app.models.profissao.Profissao;
import com.example.app.models.user.User;
import com.example.app.repositories.PedidoAluguelRepository;
import com.example.app.repositories.UserRepository;
import com.example.app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cliente")
public class ClienteController {
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

    @Autowired
    private PedidoAluguelRepository pedidoAluguelRepository;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

//    aluguel
    @PostMapping("/pedido")
    public ResponseEntity<PedidoAluguel> introduzirAluguel(@RequestBody Map<String, Object> request) {
        System.out.println(request);
        String placa = String.valueOf(request.get("placa").toString());
        String contratanteId = String.valueOf(request.get("contratanteId").toString());
        String fornecedorId = String.valueOf(request.get("fornecedorId").toString());
        String dataTermino = String.valueOf(request.get("dataDevolucao").toString());
        String dataInicio = String.valueOf(request.get("dataRetirada").toString());

        PedidoAluguel newObj = new PedidoAluguel();
        newObj.setDataInicio(dataInicio);
        newObj.setDataTermino(dataTermino);
        newObj.setPlacaAutomovel(placa);
        newObj.setStatus(StatusAluguel.Solicitado);
        newObj.setContratante(contratanteId);
        newObj.setFornecedor(fornecedorId);
        this.pedidoAluguelService.create(newObj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoAluguel>> pedidos(){
        List<PedidoAluguel> pedidoAluguelList = pedidoAluguelService.findAll();
        return ResponseEntity.ok(pedidoAluguelList);
    }

    @PostMapping("/profissao")
    public ResponseEntity<PedidoAluguel> introduzirProfissao(@RequestBody Map<String, Object> request) {
        System.out.println("Solicitação recebida: " + request);
        String empregador = String.valueOf(request.get("empregador").toString());
        String nome = String.valueOf(request.get("nome").toString());
        Double salario = Double.valueOf(request.get("salario").toString());
        String userId = String.valueOf(request.get("userId").toString());
        Profissao newObj = new Profissao(empregador,nome,salario);
        newObj.setEmpregador(empregador);
        newObj.setNome(nome);
        newObj.setSalario(salario);
        newObj.setUser_id(userId);
        this.profissaoService.create(newObj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @DeleteMapping("/profissao/{id}")
    public ResponseEntity<Void> cancelarProfissao(@PathVariable Long id){
        this.profissaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<Void> cancelarAluguel(@PathVariable Long id){
        this.pedidoAluguelService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profissoes")
    public ResponseEntity<List<Profissao>> profissoes(){
        List<Profissao> profissaoList = profissaoService.findAll();
        return ResponseEntity.ok(profissaoList);
    }
    @GetMapping("/profissaoEspecifica/{id}")
    public ResponseEntity<Profissao> profissao(@PathVariable Long id) {
        Profissao profissao = profissaoService.findById(id);
        return ResponseEntity.ok(profissao);
    }


    @PutMapping("/profissao/{id}")
    public ResponseEntity<Profissao> editprofissao(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        System.out.println(request);
        String empregador = request.get("empregador").toString();
        String nome = request.get("nome").toString();
        Double salario = Double.valueOf(request.get("salario").toString());

        Profissao profissao = profissaoService.findById(id);

        profissao.setNome(nome);
        profissao.setEmpregador(empregador);
        profissao.setSalario(salario);
        profissaoService.update(profissao);
        return ResponseEntity.ok(profissao);
    }
    @GetMapping("/verAgentes")
    public ResponseEntity<List<Agente>> verAgentes(){
        List<Agente> agenteList = agenteService.findAll();;
        return ResponseEntity.ok(agenteList);
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

    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<Void> deletarPedidos(@PathVariable Long id){
        this.pedidoAluguelService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/editarPedido")
    public ResponseEntity<PedidoAluguel> editarPedido(@PathVariable Long id, @RequestBody Map<String, Object> request){
         try {
        Optional<PedidoAluguel> pedidoOptional = pedidoAluguelRepository.findById(id);
        
        if (pedidoOptional.isPresent()) {
            PedidoAluguel pedido = pedidoOptional.get();
        
            if (request.containsKey("statusAluguel")) {
                pedido.setStatus(StatusAluguel.valueOf((String) request.get("statusAluguel")));
            }
            if (request.containsKey("dataInicio")) {
                pedido.setDataInicio((String) request.get("dataInicio"));
            }
            if (request.containsKey("dataTermino")) {
                pedido.setDataTermino((String) request.get("dataTermino"));
            }
            pedidoAluguelRepository.save(pedido);
            
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    }

}
