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
    public ResponseEntity<List<ClienteResponseDto>> listar(Pageable pageable){
        List<ClienteResponseDto> clientes = clienteService.listar(pageable).stream()
                .map(cliente -> new ClienteResponseDto(cliente)).collect(Collectors.toList());

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteResponseDto> buscar(@PathVariable Long clienteId){
        ClienteResponseDto clienteLocalizado = new ClienteResponseDto(clienteService.buscar(clienteId));

        return ResponseEntity.ok(clienteLocalizado);
    }

    @GetMapping("/nome/{clienteNome}")
    public ResponseEntity<ClienteResponseDto> buscarPorNome(@PathVariable String clienteNome){
        ClienteResponseDto clienteLocalizadoNome = new ClienteResponseDto(clienteService.buscarPorNome(clienteNome));

        return ResponseEntity.ok(clienteLocalizadoNome);
    }

    @GetMapping("/datan/{clienteDtNascimento}")
    public ResponseEntity<ClienteResponseDto> buscarPorDataNascimento(@PathVariable LocalDate clienteDataNascimento){
        ClienteResponseDto clienteDtNascimento = new ClienteResponseDto(clienteService.buscarPorDtNascimento(clienteDataNascimento));

        return ResponseEntity.ok(clienteDtNascimento);
    }

    @GetMapping("/datac/{clienteDtCriacao}")
    public ResponseEntity<ClienteResponseDto> buscarPorDataCriacao(@PathVariable LocalDate clienteDataCriacao){
        ClienteResponseDto clienteDtCriacao = new ClienteResponseDto(clienteService.buscarPorDtCriacao(clienteDataCriacao));

        return ResponseEntity.ok(clienteDtCriacao);
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
