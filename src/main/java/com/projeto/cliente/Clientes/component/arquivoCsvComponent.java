package com.projeto.cliente.Clientes.component;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.projeto.cliente.Clientes.model.Cliente;
import com.projeto.cliente.Clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class arquivoCsvComponent {

    @Autowired
    private ClienteRepository clienteRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

    @Scheduled(cron = ("*/30 * * * * *"), zone = "America/Sao_Paulo")
    public void arquivoCsv() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<Cliente> clientes = clienteRepository.findAll();

        Writer writer = Files.newBufferedWriter(Paths.get("clientes_" + dateFormat.format(new Date()) + ".csv"));
        StatefulBeanToCsv<Cliente> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();

        beanToCsv.write(clientes);

        writer.flush();
        writer.close();

    }
}
