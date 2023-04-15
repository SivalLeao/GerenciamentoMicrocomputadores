package com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico;

import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
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
        dao = new OrdemDeServicoImpl();

        ordem0 = new OrdemDeServico( 1111, 1112);
        ordem1 = new OrdemDeServico( 1121, 1122);
        ordem2 = new OrdemDeServico( 1131, 1132);
        ordem3 = new OrdemDeServico( 1141, 1142);
        ordem4 = new OrdemDeServico( 1111, 1152);

        dao.create(ordem0);
        dao.create(ordem1);
        dao.create(ordem2);
        dao.create(ordem3);
        dao.create(ordem4);
    }

    @Test
    void create () {
        ordem0.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        ordem0.getDescricaoServico().setMapItens("ram", 2);
        ordem0.getDescricaoServico().setMapItens("hd", 1);
        ordem0.setFormaPagamento("Cartão de crédito");
        ordem0.setSatisfacaoCliente("Excelente");

        ordem1.getDescricaoServico().setTipoDeServico("Limpeza");
        ordem1.setFormaPagamento("Transferência bancária");
        ordem1.setSatisfacaoCliente("Muito bom");

        dao.create(ordem0);
        dao.create(ordem1);

        List<OrdemDeServico> lista = dao.findMany();

        assertEquals( lista.get(0), ordem0);
        assertEquals( lista.get(1), ordem1);

        assertEquals(1163, lista.get(0).getIdOrdem());
        assertEquals(1173, lista.get(1).getIdOrdem());

        assertEquals("Em espera", lista.get(0).getStatusAtual());
        assertEquals("Em espera", lista.get(1).getStatusAtual());
    }

    @Test
    void findMany () {
        List<OrdemDeServico> lista = dao.findMany();

        assertEquals(5, lista.size());

        assertEquals( lista.get(0), ordem0);
        assertEquals( lista.get(1), ordem1);
    }

    @Test
    void findById () {
        assertEquals( dao.findById(1113), ordem0);
        assertEquals( dao.findById(1123), ordem1);
    }

    @Test
    void update () {
        ordem0.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        ordem0.getDescricaoServico().setMapItens("ram", 2);
        ordem0.getDescricaoServico().setMapItens("hd", 1);
        ordem0.setFormaPagamento("Cartão de crédito");
        ordem0.setSatisfacaoCliente("Excelente");

        assertEquals( dao.findById(1113), ordem0);

        OrdemDeServico ordem1 = new OrdemDeServico(1121, 1122);

        ordem1.setIdOrdem(1113);
        ordem1.getDescricaoServico().setTipoDeServico("Limpeza");
        ordem1.setFormaPagamento("Transferência bancária");
        ordem1.setSatisfacaoCliente("Muito bom");

        dao.update(ordem1);

        OrdemDeServico ordem2 = dao.findById(1113);

        assertEquals( ordem2, ordem1);

        assertEquals("Limpeza", ordem2.getDescricaoServico().getTipoDeServico());
        assertEquals("Transferência bancária", ordem2.getFormaPagamento());
        assertEquals("Muito bom", ordem2.getSatisfacaoCliente());
    }

    @Test
    void updateStatus () {
        ordem0.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        ordem0.getDescricaoServico().setMapItens("ram", 2);
        ordem0.getDescricaoServico().setMapItens("hd", 1);
        ordem0.setFormaPagamento("Cartão de crédito");
        ordem0.setSatisfacaoCliente("Excelente");

        assertEquals("Em espera", dao.findById(1113).getStatusAtual());

        Map<String, Integer> pecasRetornadas = dao.updateStatus(1113, "Em andamento");

        assertEquals("Em andamento", dao.findById(1113).getStatusAtual());
        assertNull( pecasRetornadas);

        pecasRetornadas = dao.updateStatus(1113, "Cancelado");

        assertEquals("Cancelado", dao.findById(1113).getStatusAtual());
        assertEquals( pecasRetornadas.size(), 2);

        assertEquals( pecasRetornadas.get("ram"), 2);
        assertEquals( pecasRetornadas.get("hd"), 1);

        pecasRetornadas = dao.updateStatus(1113, "Finalizado");

        assertEquals("Finalizado", dao.findById(1113).getStatusAtual());
        assertNull( pecasRetornadas);

        LocalDateTime dataFinalServico = dao.findById(1113).getData().getDataFim();
        assertNotNull( dataFinalServico);
    }

    @Test
    void delete () {
        assertEquals( 5, dao.findMany().size());

        assertEquals( ordem0, dao.findById(1113));
        assertEquals( ordem1, dao.findById(1123));
        assertEquals( ordem2, dao.findById(1133));
        assertEquals( ordem3, dao.findById(1143));
        assertEquals( ordem4, dao.findById(1153));

        dao.delete(1113);
        dao.delete(1133);
        dao.delete(1153);

        List<OrdemDeServico> lista = dao.findMany();

        assertEquals( 2, lista.size());
        assertEquals( ordem1, lista.get(0));
        assertEquals( ordem3, lista.get(1));
    }

    @Test
    void findByIdTecnico () {
        List<OrdemDeServico> listaTecnico = dao.findByIdTecnico(1111);

        assertEquals( 2, listaTecnico.size());

        assertEquals( ordem0, listaTecnico.get(0));
        assertEquals( ordem4, listaTecnico.get(1));
    }

    @Test
    void openListTecnico () {
        dao.updateStatus(1123, "Finalizado");
        dao.updateStatus(1133, "Cancelado");
        dao.updateStatus(1143, "Em andamento");

        List<OrdemDeServico> listaOpenTecnico = dao.openListTecnico(1111);

        assertEquals( 2, listaOpenTecnico.size());

        assertEquals( ordem0, listaOpenTecnico.get(0));
        assertEquals( ordem4, listaOpenTecnico.get(1));
    }

    @Test
    void checkById () {
        assertTrue( dao.checkById(1113));
        assertTrue( dao.checkById(1123));
        assertFalse( dao.checkById(1163));
    }

    @Test
    void checkStatus () {
        dao.updateStatus(1113, "Finalizado");
        dao.updateStatus(1133, "Cancelado");
        dao.updateStatus(1153, "Em andamento");

        assertTrue( dao.checkStatus(1111));

        dao.updateStatus(1153, "Em espera");

        assertFalse( dao.checkStatus(1111));
    }

    @Test
    void deleteMany () {
        List<OrdemDeServico> lista = dao.findMany();

        assertEquals( 5, lista.size());

        dao.deleteMany();

        lista = dao.findMany();

        assertEquals( 0, lista.size());
    }
}
