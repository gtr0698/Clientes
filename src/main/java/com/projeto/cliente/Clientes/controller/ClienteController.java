package com.projeto.cliente.Clientes.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.projeto.cliente.Clientes.dto.ClienteResponseDto;
import com.projeto.cliente.Clientes.dto.CreateClienteRequestDto;
import com.projeto.cliente.Clientes.dto.UpdateClienteRequestDto;
import com.projeto.cliente.Clientes.service.ClienteService;
import jakarta.validation.Valid;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listar(){
        List<ClienteResponseDto> clientes = clienteService.listar(PageRequest.of(0,4,Sort.Direction.ASC,"nome","dtNascimento")).stream()
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

    @GetMapping("/pdf")
    public List<ClienteResponseDto>geraPdf() throws JsonProcessingException {
        List<ClienteResponseDto>dadosRelatorio = clienteService.listar(PageRequest.of(0,4,Sort.Direction.ASC,"nome"))
                .stream().map(cliente -> new ClienteResponseDto(cliente)).collect(Collectors.toList());

        try {

            String userHomeDirectory = System.getProperty("user.home");

            String outputFile = userHomeDirectory + File.separatorChar + "ArquivoClientes.pdf";

            JRBeanCollectionDataSource consultaJRBean = new JRBeanCollectionDataSource(dadosRelatorio);

            //Mapa para parametros jasper reports
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("ConsultaDataSource", consultaJRBean);

            //Editar o caminho dos arquivos salvos no parametro sourceFileName
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\guilh\\Documents\\Projetos\\Clientes\\src\\main\\resources\\templates\\customer.jasper", parametros, new JREmptyDataSource());

            OutputStream outputStream = new FileOutputStream(new File(outputFile));

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            System.out.println("Arquivo Gerado");
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        return dadosRelatorio;
    }
}
