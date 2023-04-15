package com.pbl.gerenciamentomicrocomputadores.dao.peca;

import com.pbl.gerenciamentomicrocomputadores.model.Peca;
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
        dao = new PecaImpl();

        peca0 = new Peca("Processador", 5, 50, 40);
        peca1 = new Peca("Cooler", 4, 40, 35);
        peca2 = new Peca("Teclado", 6, 20, 15);

        dao.create(peca0);
        dao.create(peca1);
        dao.create(peca2);
    }

    @Test
    void create() {
        Map<String, Peca> mapPeca = dao.findFullMap();

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
    void findFullMap () {
        Map<String, Peca> mapPeca = dao.findFullMap();

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
    void update () {

        Map<String, Peca> mapPeca = dao.findFullMap();

        assertEquals( mapPeca.get("cooler"), peca1);

        Peca newPeca1 = new Peca("Cooler", 1, 50, 40);

        dao.update(newPeca1);

        mapPeca = dao.findFullMap();

        assertEquals( mapPeca.get("cooler"), newPeca1);

        assertEquals("cooler", mapPeca.get("cooler").getNome());
        assertEquals(1, mapPeca.get("cooler").getQuantidade());
        assertEquals(50, mapPeca.get("cooler").getValor());
        assertEquals(40, mapPeca.get("cooler").getCusto());

        Peca newPeca2 = new Peca("HD", 5, 25.50, 20);

        dao.update(newPeca2);

        mapPeca = dao.findFullMap();

        assertEquals( mapPeca.get("hd"), newPeca2);

        assertEquals("hd", mapPeca.get("hd").getNome());
        assertEquals(5, mapPeca.get("hd").getQuantidade());
        assertEquals(25.50, mapPeca.get("hd").getValor());
        assertEquals(20, mapPeca.get("hd").getCusto());
    }

    @Test
    void findByName () {
        assertEquals( dao.findByName("Cooler"), peca1);
        assertNull( dao.findByName("Mouse"));
    }

    @Test
    void checkByName () {
        assertTrue(dao.checkByName("Cooler"));
        assertTrue(dao.checkByName("Processador"));
        assertTrue(dao.checkByName("SSD"));
        assertFalse(dao.checkByName("Mouse"));
    }

    @Test
    void checkQuantity () {
        assertTrue(dao.checkQuantity("Cooler", 2));
        assertTrue(dao.checkQuantity("SSD", 10));

        assertFalse(dao.checkQuantity("Processador", 6));
        assertFalse(dao.checkQuantity("SSD", 11));
    }

    @Test
    void removeQuantity () {
        Map<String, Peca> mapPeca = dao.findFullMap();

        assertEquals(4, mapPeca.get("cooler").getQuantidade());
        assertEquals(10, mapPeca.get("placa mae").getQuantidade());

        dao.removeQuantity("cooler", 2);
        dao.removeQuantity("placa mae", 6);

        mapPeca = dao.findFullMap();

        assertEquals(2, mapPeca.get("cooler").getQuantidade());
        assertEquals(4, mapPeca.get("placa mae").getQuantidade());
    }

    @Test
    void addQuantity () {
        Map<String, Peca> mapPeca = dao.findFullMap();

        assertEquals(4, mapPeca.get("cooler").getQuantidade());
        assertEquals(10, mapPeca.get("placa de video").getQuantidade());

        dao.addQuantity("cooler", 2);
        dao.addQuantity("placa de video", 6);

        mapPeca = dao.findFullMap();

        assertEquals(6, mapPeca.get("cooler").getQuantidade());
        assertEquals(16, mapPeca.get("placa de video").getQuantidade());
    }

    @Test
    void quantityAlert () {
        List<Peca> listPeca = dao.quantityAlert();

        assertEquals(2, listPeca.size());

        assertEquals(listPeca.get(0), peca0);
        assertEquals(listPeca.get(1), peca1);
    }

    @Test
    void refundQuantity () {
        Map<String, Integer> mapItensServico = new HashMap<String, Integer>();

        mapItensServico.put("ram", 5);
        mapItensServico.put("processador", 10);
        mapItensServico.put("cooler", 5);
        mapItensServico.put("mouse", 5);

        Map<String, Integer> mapItensRetornados = dao.refundQuantity( mapItensServico);

        assertEquals(1, mapItensRetornados.size());
        assertTrue( mapItensRetornados.containsKey("mouse"));

        Map<String, Peca> mapPeca = dao.findFullMap();

        assertEquals(15, mapPeca.get("ram").getQuantidade());
        assertEquals(15, mapPeca.get("processador").getQuantidade());
    }

    @Test
    void removePeca () {
        assertTrue( dao.checkByName("Processador"));

        dao.removePeca("Processador");
        dao.removePeca("Placa Mãe");

        assertFalse( dao.checkByName("Processador"));
        assertFalse( dao.checkByName("Placa Mãe"));
    }

    @Test
    void deleteMany () {
        Map<String, Peca> mapPeca = dao.findFullMap();

        assertEquals(9, mapPeca.size());
        assertTrue(dao.checkByName("Processador"));
        assertEquals(10, dao.findByName("ram").getQuantidade());

        dao.deleteMany();

        mapPeca = dao.findFullMap();

        assertEquals(6, mapPeca.size());
        assertFalse(dao.checkByName("Processador"));
        assertEquals(0, dao.findByName("ram").getQuantidade());
    }
}
