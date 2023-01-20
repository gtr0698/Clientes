package com.projeto.cliente.Clientes.controller;

import com.projeto.cliente.Clientes.dto.ClienteResponseDto;
import com.projeto.cliente.Clientes.dto.CreateClienteRequestDto;
import com.projeto.cliente.Clientes.dto.UpdateClienteRequestDto;
import com.projeto.cliente.Clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listar(){
        List<ClienteResponseDto> clientes = clienteService.listar().stream()
                .map(cliente -> new ClienteResponseDto(cliente)).collect(Collectors.toList());

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/busca")
    public ResponseEntity<List<ClienteResponseDto>> buscar(@RequestParam String nome){
        List<ClienteResponseDto> clienteLocalizado = clienteService.buscar(nome).stream()
                .map(cliente -> new ClienteResponseDto(cliente)).collect(Collectors.toList());

        return ResponseEntity.ok(clienteLocalizado);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDto>adicionar(@Valid @RequestBody CreateClienteRequestDto cliente){
        ClienteResponseDto clienteSalvo = new ClienteResponseDto(clienteService.salvar(cliente));

        return ResponseEntity.ok(clienteSalvo);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteResponseDto>atualizar(@Valid @PathVariable Long clienteId,
                                                       @RequestBody UpdateClienteRequestDto categoria){
        ClienteResponseDto clienteSalvo = new ClienteResponseDto(clienteService.atualizar(clienteId,
                categoria));

        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<?> remover(@PathVariable Long clienteId){
        clienteService.excluir(clienteId);

        return ResponseEntity.noContent().build();
    }
}
