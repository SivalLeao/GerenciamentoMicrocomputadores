package com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico;

import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OrdemDeServicoImplTest {

    @Test
    void create () {

        OrdemDeServicoDAO dao = new OrdemDeServicoImpl();

        OrdemDeServico ordem0 = new OrdemDeServico( 1111, 1112);

        ordem0.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        ordem0.getDescricaoServico().setMapItens("ram", 2);
        ordem0.getDescricaoServico().setMapItens("hd", 1);
        ordem0.setFormaPagamento("Cartão de crédito");
        ordem0.setSatisfacaoCliente("Excelente");

        OrdemDeServico ordem1 = new OrdemDeServico( 1121, 1122);

        ordem1.getDescricaoServico().setTipoDeServico("Limpeza");
        ordem1.setFormaPagamento("Transferência bancária");
        ordem1.setSatisfacaoCliente("Muito bom");

        dao.create(ordem0);
        dao.create(ordem1);

        List<OrdemDeServico> lista = dao.findMany();

        assertEquals( lista.get(0), ordem0);
        assertEquals( lista.get(1), ordem1);

        assertEquals( lista.get(0).getIdOrdem(), 1113);
        assertEquals( lista.get(1).getIdOrdem(), 1123);

        assertEquals( lista.get(0).getStatusAtual(), "Em espera");
        assertEquals( lista.get(1).getStatusAtual(), "Em espera");
    }

    @Test
    void findMany () {

        OrdemDeServicoDAO dao = new OrdemDeServicoImpl();

        OrdemDeServico ordem0 = new OrdemDeServico(1111, 1112);
        OrdemDeServico ordem1 = new OrdemDeServico(1121, 1122);

        dao.create(ordem0);
        dao.create(ordem1);

        List<OrdemDeServico> lista = dao.findMany();

        assertEquals( lista.size(), 2);

        assertEquals( lista.get(0), ordem0);
        assertEquals( lista.get(1), ordem1);
    }

    @Test
    void findById () {

        OrdemDeServicoDAO dao = new OrdemDeServicoImpl();

        OrdemDeServico ordem0 = new OrdemDeServico(1111, 1112);
        OrdemDeServico ordem1 = new OrdemDeServico(1121, 1122);

        dao.create(ordem0);
        dao.create(ordem1);

        assertEquals( dao.findById(1113), ordem0);
        assertEquals( dao.findById(1123), ordem1);
    }

    @Test
    void update () {

        OrdemDeServicoDAO dao = new OrdemDeServicoImpl();

        OrdemDeServico ordem0 = new OrdemDeServico(1111, 1112);

        ordem0.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        ordem0.getDescricaoServico().setMapItens("ram", 2);
        ordem0.getDescricaoServico().setMapItens("hd", 1);
        ordem0.setFormaPagamento("Cartão de crédito");
        ordem0.setSatisfacaoCliente("Excelente");

        dao.create(ordem0);

        assertEquals( dao.findById(1113), ordem0);

        OrdemDeServico ordem1 = new OrdemDeServico(1121, 1122);

        ordem1.setIdOrdem(1113);
        ordem1.getDescricaoServico().setTipoDeServico("Limpeza");
        ordem1.setFormaPagamento("Transferência bancária");
        ordem1.setSatisfacaoCliente("Muito bom");

        dao.update(ordem1);

        OrdemDeServico ordem2 = dao.findById(1113);

        assertEquals( ordem2, ordem1);

        assertEquals( ordem2.getDescricaoServico().getTipoDeServico(), "Limpeza");
        assertEquals( ordem2.getFormaPagamento(), "Transferência bancária");
        assertEquals( ordem2.getSatisfacaoCliente(), "Muito bom");
    }

    @Test
    void updateStatus () {

        OrdemDeServicoDAO dao = new OrdemDeServicoImpl();

        OrdemDeServico ordem0 = new OrdemDeServico(1111, 1112);

        ordem0.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        ordem0.getDescricaoServico().setMapItens("ram", 2);
        ordem0.getDescricaoServico().setMapItens("hd", 1);
        ordem0.setFormaPagamento("Cartão de crédito");
        ordem0.setSatisfacaoCliente("Excelente");

        dao.create(ordem0);

        assertEquals( dao.findById(1113).getStatusAtual(), "Em espera");

        Map<String, Integer> pecasRetornadas = dao.updateStatus(1113, "Em andamento");

        assertEquals( dao.findById(1113).getStatusAtual(), "Em andamento");
        assertNull( pecasRetornadas);

        pecasRetornadas = dao.updateStatus(1113, "Cancelado");

        assertEquals( dao.findById(1113).getStatusAtual(), "Cancelado");
        assertEquals( pecasRetornadas.size(), 2);

        assertEquals( pecasRetornadas.get("ram"), 2);
        assertEquals( pecasRetornadas.get("hd"), 1);

        pecasRetornadas = dao.updateStatus(1113, "Finalizado");

        assertEquals( dao.findById(1113).getStatusAtual(), "Finalizado");
        assertNull( pecasRetornadas);

        LocalDateTime dataFinalServico = dao.findById(1113).getData().getDataFim();
        assertNotNull( dataFinalServico);
    }

    @Test
    void delete () {

        OrdemDeServicoDAO dao = new OrdemDeServicoImpl();

        OrdemDeServico ordem0 = new OrdemDeServico( 1111, 1112);
        OrdemDeServico ordem1 = new OrdemDeServico( 1121, 1122);
        OrdemDeServico ordem2 = new OrdemDeServico( 1131, 1132);
        OrdemDeServico ordem3 = new OrdemDeServico( 1141, 1142);
        OrdemDeServico ordem4 = new OrdemDeServico( 1151, 1152);

        dao.create(ordem0);
        dao.create(ordem1);
        dao.create(ordem2);
        dao.create(ordem3);
        dao.create(ordem4);

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

        OrdemDeServicoDAO dao = new OrdemDeServicoImpl();

        OrdemDeServico ordem0 = new OrdemDeServico( 1111, 1112);
        OrdemDeServico ordem1 = new OrdemDeServico( 1121, 1122);
        OrdemDeServico ordem2 = new OrdemDeServico( 1111, 1132);
        OrdemDeServico ordem3 = new OrdemDeServico( 1121, 1142);
        OrdemDeServico ordem4 = new OrdemDeServico( 1111, 1152);

        dao.create(ordem0);
        dao.create(ordem1);
        dao.create(ordem2);
        dao.create(ordem3);
        dao.create(ordem4);

        List<OrdemDeServico> listaTecnico = dao.findByIdTecnico(1111);

        assertEquals( 3, listaTecnico.size());

        assertEquals( ordem0, listaTecnico.get(0));
        assertEquals( ordem2, listaTecnico.get(1));
        assertEquals( ordem4, listaTecnico.get(2));
    }

    @Test
    void openListTecnico () {

        OrdemDeServicoDAO dao = new OrdemDeServicoImpl();

        OrdemDeServico ordem0 = new OrdemDeServico( 1111, 1112);
        OrdemDeServico ordem1 = new OrdemDeServico( 1111, 1122);
        OrdemDeServico ordem2 = new OrdemDeServico( 1111, 1132);
        OrdemDeServico ordem3 = new OrdemDeServico( 1111, 1142);
        OrdemDeServico ordem4 = new OrdemDeServico( 1121, 1152);

        dao.create(ordem0);
        dao.create(ordem1);
        dao.create(ordem2);
        dao.create(ordem3);
        dao.create(ordem4);

        dao.updateStatus(1123, "Finalizado");
        dao.updateStatus(1133, "Cancelado");
        dao.updateStatus(1143, "Em andamento");


        List<OrdemDeServico> listaOpenTecnico = dao.openListTecnico(1111);

        assertEquals( 2, listaOpenTecnico.size());

        assertEquals( ordem0, listaOpenTecnico.get(0));
        assertEquals( ordem3, listaOpenTecnico.get(1));
    }

    @Test
    void checkById () {

        OrdemDeServicoDAO dao = new OrdemDeServicoImpl();

        OrdemDeServico ordem0 = new OrdemDeServico( 1111, 1112);
        OrdemDeServico ordem1 = new OrdemDeServico( 1121, 1122);

        dao.create(ordem0);
        dao.create(ordem1);

        assertTrue( dao.checkById(1113));
        assertTrue( dao.checkById(1123));
        assertFalse( dao.checkById(1133));
    }

    @Test
    void checkStatus () {

        OrdemDeServicoDAO dao = new OrdemDeServicoImpl();

        OrdemDeServico ordem0 = new OrdemDeServico( 1111, 1112);
        OrdemDeServico ordem1 = new OrdemDeServico( 1111, 1122);
        OrdemDeServico ordem2 = new OrdemDeServico( 1111, 1132);
        OrdemDeServico ordem3 = new OrdemDeServico( 1111, 1142);
        OrdemDeServico ordem4 = new OrdemDeServico( 1121, 1152);

        dao.create(ordem0);
        dao.create(ordem1);
        dao.create(ordem2);
        dao.create(ordem3);
        dao.create(ordem4);

        dao.updateStatus(1123, "Finalizado");
        dao.updateStatus(1133, "Cancelado");
        dao.updateStatus(1143, "Em andamento");

        assertTrue( dao.checkStatus(1111));

        dao.updateStatus(1143, "Em espera");

        assertFalse( dao.checkStatus(1111));
    }

    @Test
    void deleteMany () {

        OrdemDeServicoDAO dao = new OrdemDeServicoImpl();

        OrdemDeServico ordem0 = new OrdemDeServico( 1111, 1112);
        OrdemDeServico ordem1 = new OrdemDeServico( 1111, 1122);
        OrdemDeServico ordem2 = new OrdemDeServico( 1111, 1132);

        dao.create(ordem0);
        dao.create(ordem1);
        dao.create(ordem2);

        List<OrdemDeServico> lista = dao.findMany();

        assertEquals( 3, lista.size());

        dao.deleteMany();

        lista = dao.findMany();

        assertEquals( 0, lista.size());
    }

}
