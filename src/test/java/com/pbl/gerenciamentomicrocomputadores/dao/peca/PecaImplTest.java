package com.pbl.gerenciamentomicrocomputadores.dao.peca;

import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Peca;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PecaImplTest {

    private PecaDAO dao;
    private Peca peca0;
    private Peca peca1;
    private Peca peca2;

    @BeforeEach
    void setUp() {

        dao = DAO.getPeca();

        peca0 = new Peca("Processador", 5, 50, 40);
        peca1 = new Peca("Cooler", 4, 40, 35);
        peca2 = new Peca("Teclado", 6, 20, 15);

        dao.criar(peca0);
        dao.criar(peca1);
        dao.criar(peca2);
    }

    @AfterEach
    void reset() {

        dao.removerTodos();

        Peca pecaBasica0 = new Peca("ram", 10, 20, 20);
        dao.atualizar(pecaBasica0);

        Peca pecaBasica1 = new Peca("placa mae", 10, 100, 100);
        dao.atualizar(pecaBasica1);

        Peca pecaBasica2 = new Peca("fonte", 10, 30, 30);
        dao.atualizar(pecaBasica2);

        Peca pecaBasica3 = new Peca("placa de video", 10, 100, 100);
        dao.atualizar(pecaBasica3);

        Peca pecaBasica4 = new Peca("hd", 10, 30, 30);
        dao.atualizar(pecaBasica4);

        Peca pecaBasica5 = new Peca("ssd", 10, 30, 30);
        dao.atualizar(pecaBasica5);
    }

    @Test
    void criar() {

        Map<String, Peca> mapPeca = dao.encontrarTodoMap();

        assertEquals( mapPeca.get(peca0.getNome()), peca0);

        assertEquals("cooler", mapPeca.get("cooler").getNome());
        assertEquals(4, mapPeca.get("cooler").getQuantidade());
        assertEquals(40, mapPeca.get("cooler").getValor());
        assertEquals(35, mapPeca.get("cooler").getCusto());

        assertEquals("fonte", mapPeca.get("fonte").getNome());
        assertEquals(10, mapPeca.get("fonte").getQuantidade());
        assertEquals(30, mapPeca.get("fonte").getValor());
        assertEquals(30, mapPeca.get("fonte").getCusto());
    }

    @Test
    void encontrarTodoMap() {

        Map<String, Peca> mapPeca = dao.encontrarTodoMap();

        assertEquals(9, mapPeca.size());

        assertTrue( mapPeca.containsKey("ram"));
        assertTrue( mapPeca.containsKey("placa mae"));
        assertTrue( mapPeca.containsKey("fonte"));
        assertTrue( mapPeca.containsKey("placa de video"));
        assertTrue( mapPeca.containsKey("hd"));
        assertTrue( mapPeca.containsKey("ssd"));
        assertTrue( mapPeca.containsKey("cooler"));

        assertFalse( mapPeca.containsKey("mouse"));
    }

    @Test
    void atualizar() {

        Map<String, Peca> mapPeca = dao.encontrarTodoMap();

        assertEquals( mapPeca.get("cooler"), peca1);

        Peca newPeca1 = new Peca("Cooler", 1, 50, 40);

        dao.atualizar(newPeca1);

        mapPeca = dao.encontrarTodoMap();

        assertEquals( mapPeca.get("cooler"), newPeca1);

        assertEquals("cooler", mapPeca.get("cooler").getNome());
        assertEquals(1, mapPeca.get("cooler").getQuantidade());
        assertEquals(50, mapPeca.get("cooler").getValor());
        assertEquals(40, mapPeca.get("cooler").getCusto());

        Peca newPeca2 = new Peca("HD", 5, 25.50, 20);

        dao.atualizar(newPeca2);

        mapPeca = dao.encontrarTodoMap();

        assertEquals( mapPeca.get("hd"), newPeca2);

        assertEquals("hd", mapPeca.get("hd").getNome());
        assertEquals(5, mapPeca.get("hd").getQuantidade());
        assertEquals(25.50, mapPeca.get("hd").getValor());
        assertEquals(20, mapPeca.get("hd").getCusto());
    }

    @Test
    void encontrarPorNome() {

        assertEquals( dao.encontrarPorNome("Cooler"), peca1);
        assertNull( dao.encontrarPorNome("Mouse"));
    }

    @Test
    void checarPorNome() {

        assertTrue(dao.checarPorNome("Cooler"));
        assertTrue(dao.checarPorNome("Processador"));
        assertTrue(dao.checarPorNome("SSD"));
        assertFalse(dao.checarPorNome("Mouse"));
    }

    @Test
    void checarQuantidade() {

        assertTrue(dao.checarQuantidade("Cooler", 2));
        assertTrue(dao.checarQuantidade("SSD", 10));

        assertFalse(dao.checarQuantidade("Processador", 6));
        assertFalse(dao.checarQuantidade("SSD", 11));
    }

    @Test
    void removerQuantidade() {

        Map<String, Peca> mapPeca = dao.encontrarTodoMap();

        assertEquals(4, mapPeca.get("cooler").getQuantidade());
        assertEquals(10, mapPeca.get("placa mae").getQuantidade());

        dao.removerQuantidade("cooler", 2);
        dao.removerQuantidade("placa mae", 6);

        mapPeca = dao.encontrarTodoMap();

        assertEquals(2, mapPeca.get("cooler").getQuantidade());
        assertEquals(4, mapPeca.get("placa mae").getQuantidade());
    }

    @Test
    void adicionarQuantidade() {

        Map<String, Peca> mapPeca = dao.encontrarTodoMap();

        assertEquals(4, mapPeca.get("cooler").getQuantidade());
        assertEquals(10, mapPeca.get("placa de video").getQuantidade());

        dao.adicionarQuantidade("cooler", 2);
        dao.adicionarQuantidade("placa de video", 6);

        mapPeca = dao.encontrarTodoMap();

        assertEquals(6, mapPeca.get("cooler").getQuantidade());
        assertEquals(16, mapPeca.get("placa de video").getQuantidade());
    }

    @Test
    void alertaDeQuantidade() {

        List<Peca> listPeca = dao.alertaDeQuantidade();

        assertEquals(2, listPeca.size());

        assertEquals(listPeca.get(0), peca0);
        assertEquals(listPeca.get(1), peca1);
    }

    @Test
    void devolverQuantidade() {

        Map<String, Integer> mapItensServico = new HashMap<String, Integer>();

        mapItensServico.put("ram", 5);
        mapItensServico.put("processador", 10);
        mapItensServico.put("cooler", 5);
        mapItensServico.put("mouse", 5);

        Map<String, Integer> mapItensRetornados = dao.devolverQuantidade( mapItensServico);

        assertEquals(1, mapItensRetornados.size());
        assertTrue( mapItensRetornados.containsKey("mouse"));

        Map<String, Peca> mapPeca = dao.encontrarTodoMap();

        assertEquals(15, mapPeca.get("ram").getQuantidade());
        assertEquals(15, mapPeca.get("processador").getQuantidade());
    }

    @Test
    void removerPeca() {

        assertTrue( dao.checarPorNome("Processador"));

        dao.removerPeca("Processador");
        dao.removerPeca("Placa Mãe");

        assertFalse( dao.checarPorNome("Processador"));
        assertFalse( dao.checarPorNome("Placa Mãe"));
    }

    @Test
    void removerTodos() {

        Map<String, Peca> mapPeca = dao.encontrarTodoMap();

        assertEquals(9, mapPeca.size());
        assertTrue(dao.checarPorNome("Processador"));
        assertEquals(10, dao.encontrarPorNome("ram").getQuantidade());

        dao.removerTodos();

        mapPeca = dao.encontrarTodoMap();

        assertEquals(6, mapPeca.size());
        assertFalse(dao.checarPorNome("Processador"));
        assertEquals(0, dao.encontrarPorNome("ram").getQuantidade());
    }

}
