package com.pbl.gerenciamentomicrocomputadores.model;

import com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico.OrdemDeServicoDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico.OrdemDeServicoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OrdemDeServicoTest {

    private OrdemDeServicoDAO dao;
    private OrdemDeServico ordem0;
    private OrdemDeServico ordem1;
    private Peca peca0;
    private Peca peca1;
    private Map<String, Peca> estoque;

    @BeforeEach
    void setUp() {
        dao = new OrdemDeServicoImpl();

        ordem0 = new OrdemDeServico( 1111, 1112);
        ordem1 = new OrdemDeServico( 1121, 1122);

        dao.create(ordem0);
        dao.create(ordem1);

        peca0 = new Peca("ram", 3, 20, 20);
        peca1 = new Peca("hd", 3, 30, 30);

        Map<String, Peca> estoque = new HashMap<String, Peca>();

        estoque.put("ram", peca0);
        estoque.put("hd", peca1);
    }

    @Test
    void calcularTempoDeServico () {
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
        ordem0.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        ordem0.getDescricaoServico().setMapItens("ram", 2);
        ordem0.getDescricaoServico().setMapItens("hd", 2);

        Map<String, Peca> estoque = new HashMap<String, Peca>();

        estoque.put("ram", peca0);
        estoque.put("hd", peca1);

        double valorServico = ordem0.calcularValorServico( estoque);
        assertEquals(100, valorServico);

        ordem0.getDescricaoServico().setTipoDeServico("Sistema operacional");

        valorServico = ordem0.calcularValorServico( estoque);
        assertEquals(50, valorServico);

        ordem0.getDescricaoServico().setTipoDeServico("Programas");

        valorServico = ordem0.calcularValorServico( estoque);
        assertEquals(10, valorServico);

        ordem0.getDescricaoServico().setTipoDeServico("Limpeza");

        valorServico = ordem0.calcularValorServico( estoque);
        assertEquals(70, valorServico);
    }

    @Test
    void imprimirOrdem () {
        assertEquals( """
                ID da ordem: 1113
                ID do cliente: 1112
                ID do técnico: 1111
                Status atual: Em espera
                """, ordem0.imprimirOrdem());
    }

    @Test
    void  imprimirFatura () {

        ordem0.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        ordem0.getDescricaoServico().setMapItens("ram", 3);
        ordem0.getDescricaoServico().setMapItens("hd", 1);

        double valorServico = ordem0.calcularValorServico(estoque);

        ordem0.setValorTotalFatura(valorServico);

        assertEquals("""
                Tipo de serviço: Montagem/Instalação
                Peças utilizadas:
                
                Peça 1 - nome: hd ; preço: 30,0 ; quantidade: 1
                Peça 2 - nome: ram ; preço: 20,0 ; quantidade: 3
                
                Valor total: 90,0
                """, ordem0.imprimirFatura(estoque));

        ordem1.getDescricaoServico().setTipoDeServico("Limpeza");

        valorServico = ordem1.calcularValorServico(estoque);

        ordem1.setValorTotalFatura(valorServico);

        assertEquals("""
                Tipo de serviço: Limpeza
                Valor total: 70,0
                """, ordem1.imprimirFatura(estoque));
    }

    @Test
    void imprimirRelatorio () {

        ordem0.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        ordem0.getDescricaoServico().setMapItens("ram", 3);
        ordem0.getDescricaoServico().setMapItens("hd", 1);
        ordem0.setSatisfacaoCliente("Trabalho bem feito");

        LocalDateTime dataInicial = LocalDateTime.parse("2023-04-09 13:46:10",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime dataFinal = LocalDateTime.parse("2023-04-09 13:46:35",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ordem0.getData().setDataInicio( dataInicial);
        ordem0.getData().setDataFim( dataFinal);

        assertEquals("""
                Tempo médio de espera: 25 segundos
                Peças utilizadas:
                
                Peça 1 - nome: hd ; custo: 30,0 ; quantidade: 1
                Peça 2 - nome: ram ; custo: 20,0 ; quantidade: 3
                
                Satisfação do cliente: Trabalho bem feito
                """, ordem0.imprimirRelatorio(estoque));


        ordem1.getDescricaoServico().setTipoDeServico("Sistema operacional");
        ordem1.setSatisfacaoCliente("Trabalho demorado");

        dataInicial = LocalDateTime.parse("2023-04-09 13:46:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        dataFinal = LocalDateTime.parse("2023-04-09 13:47:40",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ordem1.getData().setDataInicio( dataInicial);
        ordem1.getData().setDataFim( dataFinal);

        assertEquals("""
                Tempo médio de espera: 100 segundos
                Satisfação do cliente: Trabalho demorado
                """, ordem1.imprimirRelatorio(estoque));

    }

}
