package com.pbl.gerenciamentomicrocomputadores.dao.peca;

import com.pbl.gerenciamentomicrocomputadores.model.Peca;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PecaImplTest {

    @Test
    void create () {

        PecaDAO dao = new PecaImpl();

        Peca peca0 = new Peca("Cooler", 3, 40, 35);

        dao.create(peca0);

        Map<String, Peca> mapPeca = dao.findFullMap();

        assertEquals( mapPeca.get(peca0.getNome()), peca0);

        assertEquals( mapPeca.get("cooler").getNome(), "cooler");
        assertEquals( mapPeca.get("cooler").getQuantidade(), 3);
        assertEquals( mapPeca.get("cooler").getValor(), 40);
        assertEquals( mapPeca.get("cooler").getCusto(), 35);

        assertEquals( mapPeca.get("fonte").getNome(), "fonte");
        assertEquals( mapPeca.get("fonte").getQuantidade(), 10);
        assertEquals( mapPeca.get("fonte").getValor(), 30);
        assertEquals( mapPeca.get("fonte").getCusto(), 30);
    }

    @Test
    void findFullMap () {

        PecaDAO dao = new PecaImpl();

        Peca peca0 = new Peca("Cooler", 3, 40, 35);

        dao.create( peca0);

        Map<String, Peca> mapPeca = dao.findFullMap();

        assertEquals( mapPeca.size(), 7);

        assertTrue( mapPeca.containsKey("ram"));
        assertTrue( mapPeca.containsKey("placa mae"));
        assertTrue( mapPeca.containsKey("fonte"));
        assertTrue( mapPeca.containsKey("placa de video"));
        assertTrue( mapPeca.containsKey("hd"));
        assertTrue( mapPeca.containsKey("ssd"));
        assertTrue( mapPeca.containsKey("cooler"));

        assertFalse( mapPeca.containsKey("processador"));
    }

    @Test
    void update () {

        PecaDAO dao = new PecaImpl();

        Peca peca0 = new Peca("Cooler", 3, 40, 35);

        dao.create(peca0);

        Map<String, Peca> mapPeca = dao.findFullMap();

        assertEquals( mapPeca.get("cooler"), peca0);

        Peca peca1 = new Peca("Cooler", 1, 50, 40);

        dao.update(peca1);

        mapPeca = dao.findFullMap();

        assertEquals( mapPeca.get("cooler"), peca1);

        assertEquals( mapPeca.get("cooler").getNome(), "cooler");
        assertEquals( mapPeca.get("cooler").getQuantidade(), 1);
        assertEquals( mapPeca.get("cooler").getValor(), 50);
        assertEquals( mapPeca.get("cooler").getCusto(), 40);

        Peca peca2 = new Peca("HD", 5, 25.50, 20);

        dao.update(peca2);

        mapPeca = dao.findFullMap();

        assertEquals( mapPeca.get("hd"), peca2);

        assertEquals( mapPeca.get("hd").getNome(), "hd");
        assertEquals( mapPeca.get("hd").getQuantidade(), 5);
        assertEquals( mapPeca.get("hd").getValor(), 25.50);
        assertEquals( mapPeca.get("hd").getCusto(), 20);
    }

    @Test
    void findByName () {

        PecaDAO dao = new PecaImpl();

        Peca peca0 = new Peca("Cooler", 3, 40, 35);

        dao.create(peca0);

        assertEquals( dao.findByName("Cooler"), peca0);
        assertNull( dao.findByName("Processador"));
    }

    @Test
    void checkByName () {

        PecaDAO dao = new PecaImpl();

        Peca peca0 = new Peca("Cooler", 3, 40, 35);
        Peca peca1 = new Peca("Processador", 5, 50, 40);

        dao.create(peca0);
        dao.create(peca1);

        assertTrue(dao.checkByName("Cooler"));
        assertTrue(dao.checkByName("Processador"));
        assertTrue(dao.checkByName("SSD"));
        assertFalse(dao.checkByName("Teclado"));
    }

    @Test
    void checkQuantity () {

        PecaDAO dao = new PecaImpl();

        Peca peca0 = new Peca("Cooler", 3, 40, 35);
        Peca peca1 = new Peca("Processador", 5, 50, 40);

        dao.create(peca0);
        dao.create(peca1);

        assertTrue(dao.checkQuantity("Cooler", 2));
        assertTrue(dao.checkQuantity("SSD", 10));

        assertFalse(dao.checkQuantity("Processador", 6));
        assertFalse(dao.checkQuantity("SSD", 11));
    }

    @Test
    void removeQuantity () {

        PecaDAO dao = new PecaImpl();

        Peca peca0 = new Peca("Cooler", 3, 40, 35);

        dao.create(peca0);

        Map<String, Peca> mapPeca = dao.findFullMap();

        assertEquals(mapPeca.get("cooler").getQuantidade(), 3);
        assertEquals(mapPeca.get("placa mae").getQuantidade(), 10);

        dao.removeQuantity("cooler", 2);
        dao.removeQuantity("placa mae", 6);

        mapPeca = dao.findFullMap();

        assertEquals(mapPeca.get("cooler").getQuantidade(), 1);
        assertEquals(mapPeca.get("placa mae").getQuantidade(), 4);
    }

    @Test
    void addQuantity () {

        PecaDAO dao = new PecaImpl();

        Peca peca0 = new Peca("Cooler", 3, 40, 35);

        dao.create(peca0);

        Map<String, Peca> mapPeca = dao.findFullMap();

        assertEquals(mapPeca.get("cooler").getQuantidade(), 3);
        assertEquals(mapPeca.get("placa de video").getQuantidade(), 10);

        dao.addQuantity("cooler", 2);
        dao.addQuantity("placa de video", 6);

        mapPeca = dao.findFullMap();

        assertEquals(mapPeca.get("cooler").getQuantidade(), 5);
        assertEquals(mapPeca.get("placa de video").getQuantidade(), 16);
    }

    @Test
    void quantityAlert () {

        PecaDAO dao = new PecaImpl();

        Peca peca0 = new Peca("Processador", 5, 50, 40);
        Peca peca1 = new Peca("Cooler", 4, 40, 35);
        Peca peca2 = new Peca("Teclado", 6, 20, 15);

        dao.create(peca0);
        dao.create(peca1);
        dao.create(peca2);

        List<Peca> listPeca = dao.quantityAlert();

        assertEquals(listPeca.size(), 2);

        assertEquals(listPeca.get(0), peca0);
        assertEquals(listPeca.get(1), peca1);
    }

    @Test
    void refundQuantity () {

        PecaDAO dao = new PecaImpl();

        Peca peca0 = new Peca("Processador", 10, 50, 40);

        dao.create(peca0);

        Map<String, Integer> mapItensServico = new HashMap<String, Integer>();

        mapItensServico.put("ram", 5);
        mapItensServico.put("processador", 10);
        mapItensServico.put("cooler", 5);

        Map<String, Integer> mapItensRetornados = dao.refundQuantity( mapItensServico);

        assertEquals( mapItensRetornados.size(), 1);
        assertTrue( mapItensRetornados.containsKey("cooler"));

        Map<String, Peca> mapPeca = dao.findFullMap();

        assertEquals( mapPeca.get("ram").getQuantidade(), 15);
        assertEquals( mapPeca.get("processador").getQuantidade(), 20);
    }

    @Test
    void removePeca () {

        PecaDAO dao = new PecaImpl();

        Peca peca0 = new Peca("Processador", 10, 50, 40);

        dao.create(peca0);

        assertTrue( dao.checkByName("Processador"));

        dao.removePeca("Processador");
        dao.removePeca("Placa Mãe");

        assertFalse( dao.checkByName("Processador"));
        assertFalse( dao.checkByName("Placa Mãe"));
    }

    @Test
    void deleteMany () {

        PecaDAO dao = new PecaImpl();

        Peca peca0 = new Peca("Processador", 10, 50, 40);

        dao.create(peca0);

        Map<String, Peca> mapPeca = dao.findFullMap();

        assertEquals(mapPeca.size(), 7);
        assertTrue(dao.checkByName("Processador"));
        assertEquals(dao.findByName("ram").getQuantidade(), 10);

        dao.deleteMany();

        mapPeca = dao.findFullMap();

        assertEquals(mapPeca.size(), 6);
        assertFalse(dao.checkByName("Processador"));
        assertEquals(dao.findByName("ram").getQuantidade(), 0);
    }

}
