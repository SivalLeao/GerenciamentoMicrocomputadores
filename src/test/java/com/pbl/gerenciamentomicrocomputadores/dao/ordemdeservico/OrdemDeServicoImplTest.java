package com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico;

import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OrdemDeServicoImplTest {

    private OrdemDeServicoDAO dao;
    private OrdemDeServico ordem0;
    private OrdemDeServico ordem1;
    private OrdemDeServico ordem2;
    private OrdemDeServico ordem3;
    private OrdemDeServico ordem4;

    @BeforeEach
    void setUp() {

        dao = DAO.getOrdemDeServico();

        ordem0 = new OrdemDeServico( 1111, 1112);
        ordem1 = new OrdemDeServico( 1121, 1122);
        ordem2 = new OrdemDeServico( 1131, 1132);
        ordem3 = new OrdemDeServico( 1141, 1142);
        ordem4 = new OrdemDeServico( 1111, 1152);

        dao.criar(ordem0);
        dao.criar(ordem1);
        dao.criar(ordem2);
        dao.criar(ordem3);
        dao.criar(ordem4);
    }

    @AfterEach
    void reset() {

        dao.removerTodos();
    }

    @Test
    void criar() {

        List<OrdemDeServico> lista = dao.encontrarTodos();

        assertEquals( lista.get(0), ordem0);
        assertEquals( lista.get(1), ordem1);

        assertEquals(1113, lista.get(0).getIdOrdem());
        assertEquals(1123, lista.get(1).getIdOrdem());

        assertEquals("Em espera", lista.get(0).getStatusAtual());
        assertEquals("Em espera", lista.get(1).getStatusAtual());
    }

    @Test
    void encontrarTodos() {

        List<OrdemDeServico> lista = dao.encontrarTodos();

        assertEquals(5, lista.size());

        assertEquals( lista.get(0), ordem0);
        assertEquals( lista.get(1), ordem1);
    }

    @Test
    void encontrarPorId() {

        assertEquals( dao.encontrarPorId(1113), ordem0);
        assertEquals( dao.encontrarPorId(1123), ordem1);
    }

    @Test
    void atualizar() {

        ordem0.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        ordem0.getDescricaoServico().setMapItens("ram", 2);
        ordem0.getDescricaoServico().setMapItens("hd", 1);
        ordem0.setFormaPagamento("Cartão de crédito");
        ordem0.setSatisfacaoCliente("Excelente");

        assertEquals( dao.encontrarPorId(1113), ordem0);

        OrdemDeServico newOrdem1 = new OrdemDeServico(1121, 1122);

        newOrdem1.setIdOrdem(1113);
        newOrdem1.getDescricaoServico().setTipoDeServico("Limpeza");
        newOrdem1.setFormaPagamento("Transferência bancária");
        newOrdem1.setSatisfacaoCliente("Muito bom");

        dao.atualizar(newOrdem1);

        OrdemDeServico newOrdem2 = dao.encontrarPorId(1113);

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

        assertEquals("Em espera", dao.encontrarPorId(1113).getStatusAtual());

        Map<String, Integer> pecasRetornadas = dao.atualizarStatus(1113, "Em andamento");

        assertEquals("Em andamento", dao.encontrarPorId(1113).getStatusAtual());
        assertNull( pecasRetornadas);

        pecasRetornadas = dao.atualizarStatus(1113, "Cancelado");

        assertEquals("Cancelado", dao.encontrarPorId(1113).getStatusAtual());
        assertEquals( pecasRetornadas.size(), 2);

        assertEquals( pecasRetornadas.get("ram"), 2);
        assertEquals( pecasRetornadas.get("hd"), 1);

        pecasRetornadas = dao.atualizarStatus(1113, "Finalizado");

        assertEquals("Finalizado", dao.encontrarPorId(1113).getStatusAtual());
        assertNull( pecasRetornadas);

        LocalDateTime dataFinalServico = dao.encontrarPorId(1113).getData().getDataFim();
        assertNotNull( dataFinalServico);
    }

    @Test
    void remover() {

        assertEquals( 5, dao.encontrarTodos().size());

        assertEquals( ordem0, dao.encontrarPorId(1113));
        assertEquals( ordem1, dao.encontrarPorId(1123));
        assertEquals( ordem2, dao.encontrarPorId(1133));
        assertEquals( ordem3, dao.encontrarPorId(1143));
        assertEquals( ordem4, dao.encontrarPorId(1153));

        dao.remover(1113);
        dao.remover(1133);
        dao.remover(1153);

        List<OrdemDeServico> lista = dao.encontrarTodos();

        assertEquals( 2, lista.size());
        assertEquals( ordem1, lista.get(0));
        assertEquals( ordem3, lista.get(1));
    }

    @Test
    void encontrarPorIdTecnico() {

        List<OrdemDeServico> listaTecnico = dao.encontrarPorIdTecnico(1111);

        assertEquals( 2, listaTecnico.size());

        assertEquals( ordem0, listaTecnico.get(0));
        assertEquals( ordem4, listaTecnico.get(1));
    }

    @Test
    void listaEmAbertoTecnico() {

        dao.atualizarStatus(1123, "Finalizado");
        dao.atualizarStatus(1133, "Cancelado");
        dao.atualizarStatus(1143, "Em andamento");

        List<OrdemDeServico> listaOpenTecnico = dao.listaEmAbertoTecnico(1111);

        assertEquals( 2, listaOpenTecnico.size());

        assertEquals( ordem0, listaOpenTecnico.get(0));
        assertEquals( ordem4, listaOpenTecnico.get(1));
    }

    @Test
    void checarPorId() {

        assertTrue( dao.checarPorId(1113));
        assertTrue( dao.checarPorId(1123));
        assertFalse( dao.checarPorId(1163));
    }

    @Test
    void checarStatusEmAndamento() {

        dao.atualizarStatus(1113, "Finalizado");
        dao.atualizarStatus(1133, "Cancelado");
        dao.atualizarStatus(1153, "Em andamento");

        assertTrue( dao.checarStatusEmAndamento(1111));

        dao.atualizarStatus(1153, "Em espera");

        assertFalse( dao.checarStatusEmAndamento(1111));
    }

    @Test
    void removerTodos() {

        List<OrdemDeServico> lista = dao.encontrarTodos();

        assertEquals( 5, lista.size());

        dao.removerTodos();

        lista = dao.encontrarTodos();

        assertEquals( 0, lista.size());
    }

}
