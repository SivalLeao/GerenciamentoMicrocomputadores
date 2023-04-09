package com.pbl.gerenciamentomicrocomputadores.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OrdemDeServicoTest {

    @Test
    void calcularTempoDeServico () {

        OrdemDeServico ordem0 = new OrdemDeServico(1111, 1112);

        LocalDateTime dataInicial = LocalDateTime.parse("2023-04-09 13:46:10",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime dataFinal = LocalDateTime.parse("2023-04-09 13:46:25",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ordem0.getData().setDataInicio( dataInicial);
        ordem0.getData().setDataFim( dataFinal);

        long tempoDeEsperaSegundos = ordem0.calcularTempoDeServico();

        assertEquals( 15, tempoDeEsperaSegundos);
    }

    @Test
    void calcularValorServico () {

        OrdemDeServico ordem0 = new OrdemDeServico(1111, 1112);

        ordem0.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        ordem0.getDescricaoServico().setMapItens("ram", 2);
        ordem0.getDescricaoServico().setMapItens("hd", 2);

        Map<String, Peca> mapItensValor = new HashMap<String, Peca>();

        Peca peca0 = new Peca("ram", 3, 20, 20);
        Peca peca1 = new Peca("hd", 3, 30, 30);

        mapItensValor.put("ram", peca0);
        mapItensValor.put("hd", peca1);

        double valorServico = ordem0.calcularValorServico( mapItensValor);
        assertEquals(100, valorServico);

        ordem0.getDescricaoServico().setTipoDeServico("Sistema operacional");

        valorServico = ordem0.calcularValorServico( mapItensValor);
        assertEquals(50, valorServico);

        ordem0.getDescricaoServico().setTipoDeServico("Programas");

        valorServico = ordem0.calcularValorServico( mapItensValor);
        assertEquals(10, valorServico);

        ordem0.getDescricaoServico().setTipoDeServico("Limpeza");

        valorServico = ordem0.calcularValorServico( mapItensValor);
        assertEquals(70, valorServico);
    }

}
