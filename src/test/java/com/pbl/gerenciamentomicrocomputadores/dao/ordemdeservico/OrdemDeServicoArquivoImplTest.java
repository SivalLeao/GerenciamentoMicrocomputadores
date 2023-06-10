package com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico;

import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrdemDeServicoArquivoImplTest {

    private OrdemDeServico ordem0;
    private OrdemDeServico ordem1;
    private OrdemDeServico ordem2;
    private OrdemDeServico ordem3;
    private OrdemDeServico ordem4;

    @BeforeEach
    void setUp() {

        DAO.getOrdemDeServico().diretorioTest();

        ordem0 = new OrdemDeServico( 1111, 1112);
        ordem1 = new OrdemDeServico( 1121, 1122);
        ordem2 = new OrdemDeServico( 1131, 1132);
        ordem3 = new OrdemDeServico( 1141, 1142);
        ordem4 = new OrdemDeServico( 1111, 1152);

        DAO.getOrdemDeServico().criar(ordem0);
        DAO.getOrdemDeServico().criar(ordem1);
        DAO.getOrdemDeServico().criar(ordem2);
        DAO.getOrdemDeServico().criar(ordem3);
        DAO.getOrdemDeServico().criar(ordem4);
    }

    @AfterEach
    void reset() {

        DAO.getOrdemDeServico().removerTodos();

        DAO.getOrdemDeServico().diretorioPadrao();
    }

    @Test
    void criar() {

        File diretorio = new File("dados salvos");
        File pasta1 = new File(diretorio +"/"+ "Teste Ordem De Servico");
        File arquivoOrdemDeServico = new File(pasta1,"ordemdeservicotest.dat");

        assertTrue(arquivoOrdemDeServico.exists());

        List<OrdemDeServico> lista = DAO.getOrdemDeServico().encontrarTodos();

        assertEquals( lista.get(0), ordem0);
        assertEquals( lista.get(1), ordem1);

        assertEquals(1113, lista.get(0).getIdOrdem());
        assertEquals(1123, lista.get(1).getIdOrdem());

        assertEquals("Em espera", lista.get(0).getStatusAtual());
        assertEquals("Em espera", lista.get(1).getStatusAtual());
    }

    @Test
    void encontrarTodos() {

        List<OrdemDeServico> lista = DAO.getOrdemDeServico().encontrarTodos();

        assertEquals(5, lista.size());

        assertEquals( lista.get(0), ordem0);
        assertEquals( lista.get(1), ordem1);
    }

    @Test
    void encontrarPorId() {

        assertEquals(DAO.getOrdemDeServico().encontrarPorId(1113), ordem0);
        assertEquals(DAO.getOrdemDeServico().encontrarPorId(1123), ordem1);
    }

    @Test
    void atualizar() {

        ordem0.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        ordem0.getDescricaoServico().setMapItens("ram", 2);
        ordem0.getDescricaoServico().setMapItens("hd", 1);
        ordem0.setFormaPagamento("Cartão de crédito");
        ordem0.setSatisfacaoCliente("Excelente");

        assertEquals(DAO.getOrdemDeServico().encontrarPorId(1113), ordem0);

        OrdemDeServico newOrdem1 = new OrdemDeServico(1121, 1122);

        newOrdem1.setIdOrdem(1113);
        newOrdem1.getDescricaoServico().setTipoDeServico("Limpeza");
        newOrdem1.setFormaPagamento("Transferência bancária");
        newOrdem1.setSatisfacaoCliente("Muito bom");

        DAO.getOrdemDeServico().atualizar(newOrdem1);

        OrdemDeServico newOrdem2 = DAO.getOrdemDeServico().encontrarPorId(1113);

        assertEquals( newOrdem2, newOrdem1);

        assertEquals("Limpeza", newOrdem2.getDescricaoServico().getTipoDeServico());
        assertEquals("Transferência bancária", newOrdem2.getFormaPagamento());
        assertEquals("Muito bom", newOrdem2.getSatisfacaoCliente());
    }

    @Test
    void atualizarStatus() {

        ordem0.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        ordem0.getDescricaoServico().setMapItens("ram", 2);
        ordem0.getDescricaoServico().setMapItens("hd", 1);
        ordem0.setFormaPagamento("Cartão de crédito");
        ordem0.setSatisfacaoCliente("Excelente");

        assertEquals("Em espera", DAO.getOrdemDeServico().encontrarPorId(1113).getStatusAtual());

        Map<String, Integer> pecasRetornadas = DAO.getOrdemDeServico().atualizarStatus(1113, "Em andamento");

        assertEquals("Em andamento", DAO.getOrdemDeServico().encontrarPorId(1113).getStatusAtual());
        assertNull( pecasRetornadas);

        pecasRetornadas = DAO.getOrdemDeServico().atualizarStatus(1113, "Cancelado");

        assertEquals("Cancelado", DAO.getOrdemDeServico().encontrarPorId(1113).getStatusAtual());
        assertEquals( pecasRetornadas.size(), 2);

        assertEquals( pecasRetornadas.get("ram"), 2);
        assertEquals( pecasRetornadas.get("hd"), 1);

        pecasRetornadas = DAO.getOrdemDeServico().atualizarStatus(1113, "Finalizado");

        assertEquals("Finalizado", DAO.getOrdemDeServico().encontrarPorId(1113).getStatusAtual());
        assertNull( pecasRetornadas);

        LocalDateTime dataFinalServico = DAO.getOrdemDeServico().encontrarPorId(1113).getData().getDataFim();
        assertNotNull( dataFinalServico);
    }

    @Test
    void remover() {

        assertEquals( 5, DAO.getOrdemDeServico().encontrarTodos().size());

        assertEquals( ordem0, DAO.getOrdemDeServico().encontrarPorId(1113));
        assertEquals( ordem1, DAO.getOrdemDeServico().encontrarPorId(1123));
        assertEquals( ordem2, DAO.getOrdemDeServico().encontrarPorId(1133));
        assertEquals( ordem3, DAO.getOrdemDeServico().encontrarPorId(1143));
        assertEquals( ordem4, DAO.getOrdemDeServico().encontrarPorId(1153));

        DAO.getOrdemDeServico().remover(1113);
        DAO.getOrdemDeServico().remover(1133);
        DAO.getOrdemDeServico().remover(1153);

        List<OrdemDeServico> lista = DAO.getOrdemDeServico().encontrarTodos();

        assertEquals( 2, lista.size());
        assertEquals( ordem1, lista.get(0));
        assertEquals( ordem3, lista.get(1));
    }

    @Test
    void encontrarPorIdTecnico() {

        List<OrdemDeServico> listaTecnico = DAO.getOrdemDeServico().encontrarPorIdTecnico(1111);

        assertEquals( 2, listaTecnico.size());

        assertEquals( ordem0, listaTecnico.get(0));
        assertEquals( ordem4, listaTecnico.get(1));
    }

    @Test
    void encontrarPorIdCliente() {

        List<OrdemDeServico> listaCliente = DAO.getOrdemDeServico().encontrarPorIdCliente(1122);

        assertEquals( 1, listaCliente.size());

        assertEquals( ordem1, listaCliente.get(0));
    }

    @Test
    void listaEmAbertoTecnico() {

        DAO.getOrdemDeServico().atualizarStatus(1123, "Finalizado");
        DAO.getOrdemDeServico().atualizarStatus(1133, "Cancelado");
        DAO.getOrdemDeServico().atualizarStatus(1143, "Em andamento");

        List<OrdemDeServico> listaOpenTecnico = DAO.getOrdemDeServico().listaEmAbertoTecnico(1111);

        assertEquals( 2, listaOpenTecnico.size());

        assertEquals( ordem0, listaOpenTecnico.get(0));
        assertEquals( ordem4, listaOpenTecnico.get(1));
    }

    @Test
    void checarPorId() {

        assertTrue(DAO.getOrdemDeServico().checarPorId(1113));
        assertTrue(DAO.getOrdemDeServico().checarPorId(1123));
        assertFalse(DAO.getOrdemDeServico().checarPorId(1163));
    }

    @Test
    void checarStatusEmAndamento() {

        DAO.getOrdemDeServico().atualizarStatus(1113, "Finalizado");
        DAO.getOrdemDeServico().atualizarStatus(1133, "Cancelado");
        DAO.getOrdemDeServico().atualizarStatus(1153, "Em andamento");

        assertTrue(DAO.getOrdemDeServico().checarStatusEmAndamento(1111));

        DAO.getOrdemDeServico().atualizarStatus(1153, "Em espera");

        assertFalse(DAO.getOrdemDeServico().checarStatusEmAndamento(1111));
    }

    @Test
    void coletarOrdem () {

        assertEquals(ordem0, DAO.getOrdemDeServico().coletarOrdem());

    }

    @Test
    void conseguirOrdemTecnico () {

        OrdemDeServico ordemDeServico = DAO.getOrdemDeServico().encontrarPorId(1143);

        ordemDeServico.setStatusAtual("Em andamento");

        DAO.getOrdemDeServico().atualizar(ordemDeServico);

        assertEquals(ordemDeServico, DAO.getOrdemDeServico().conseguirOrdemTecnico(1141));

    }

    @Test
    void OrdensUtilizandoPeca () {

        DAO.getOrdemDeServico().remover(1113);
        DAO.getOrdemDeServico().remover(1123);
        DAO.getOrdemDeServico().remover(1133);
        DAO.getOrdemDeServico().remover(1143);
        DAO.getOrdemDeServico().remover(1153);

        OrdemDeServico newOrdem1 = new OrdemDeServico(1111,1112);
        newOrdem1.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        newOrdem1.getDescricaoServico().setMapItens("teclado", 5);
        newOrdem1.getDescricaoServico().setMapItens("mouse", 3);

        DAO.getOrdemDeServico().criar(newOrdem1);

        OrdemDeServico newOrdem2 = new OrdemDeServico(1111,1112);
        newOrdem2.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        newOrdem2.getDescricaoServico().setMapItens("tela", 1);
        newOrdem2.getDescricaoServico().setMapItens("hd", 3);

        DAO.getOrdemDeServico().criar(newOrdem2);

        OrdemDeServico newOrdem3 = new OrdemDeServico(1111,1112);
        newOrdem3.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        newOrdem3.getDescricaoServico().setMapItens("teclado", 3);
        newOrdem3.getDescricaoServico().setMapItens("hd", 3);

        DAO.getOrdemDeServico().criar(newOrdem3);

        Map<Integer,Integer> mapOrdemQtd = DAO.getOrdemDeServico().OrdensUtilizandoPeca("teclado");

        assertEquals(mapOrdemQtd.size(), 2);

        assertEquals(mapOrdemQtd.get(1113), 5);
        assertEquals(mapOrdemQtd.get(1133), 3);

    }

    @Test
    void removerTodos() {

        List<OrdemDeServico> lista = DAO.getOrdemDeServico().encontrarTodos();

        assertEquals( 5, lista.size());

        DAO.getOrdemDeServico().removerTodos();

        lista = DAO.getOrdemDeServico().encontrarTodos();

        assertEquals( 0, lista.size());
    }

}
