package com.pbl.gerenciamentomicrocomputadores.dao.peca;

import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Peca;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PecaArquivoImplTest {
    private Peca peca0;
    private Peca peca1;
    private Peca peca2;

    @BeforeEach
    void setUp() {

        DAO.getPeca().diretorioTest();

        peca0 = new Peca("Processador", 5, 50, 40);
        peca1 = new Peca("Cooler", 4, 40, 35);
        peca2 = new Peca("Teclado", 6, 20, 15);

        DAO.getPeca().criar(peca0);
        DAO.getPeca().criar(peca1);
        DAO.getPeca().criar(peca2);
    }

    @AfterEach
    void reset() {

        DAO.getPeca().removerTodos();

        Peca pecaBasica0 = new Peca("ram", 10, 20, 20);
        DAO.getPeca().atualizar(pecaBasica0);

        Peca pecaBasica1 = new Peca("placa mae", 10, 100, 100);
        DAO.getPeca().atualizar(pecaBasica1);

        Peca pecaBasica2 = new Peca("fonte", 10, 30, 30);
        DAO.getPeca().atualizar(pecaBasica2);

        Peca pecaBasica3 = new Peca("placa de video", 10, 100, 100);
        DAO.getPeca().atualizar(pecaBasica3);

        Peca pecaBasica4 = new Peca("hd", 10, 30, 30);
        DAO.getPeca().atualizar(pecaBasica4);

        Peca pecaBasica5 = new Peca("ssd", 10, 30, 30);
        DAO.getPeca().atualizar(pecaBasica5);

        DAO.getPeca().diretorioPadrao();
    }

    @Test
    void criar() {

        File diretorio = new File("dados salvos");
        File pasta1 = new File(diretorio +"/"+ "Peca");
        File arquivoPeca = new File(pasta1,"peca.dat");

        assertTrue(arquivoPeca.exists());

        List<Peca> listPeca = DAO.getPeca().encontrarTodos();

        assertEquals("ram", listPeca.get(0).getNome());
        assertEquals(10, listPeca.get(0).getQuantidade());
        assertEquals(20, listPeca.get(0).getValor());
        assertEquals(20, listPeca.get(0).getCusto());

        assertEquals("processador", listPeca.get(6).getNome());
        assertEquals(5, listPeca.get(6).getQuantidade());
        assertEquals(50, listPeca.get(6).getValor());
        assertEquals(40, listPeca.get(6).getCusto());
    }

    @Test
    void encontrarTodos() {

        List<Peca> listPeca = DAO.getPeca().encontrarTodos();

        assertEquals( 9, listPeca.size());

        assertEquals( "ram", listPeca.get(0).getNome());
        assertEquals( peca0, listPeca.get(6));
    }

    @Test
    void encontrarTodosMap() {

        Map<String, Peca> mapPeca = DAO.getPeca().encontrarTodosMap();

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

        List<Peca> listPeca = DAO.getPeca().encontrarTodos();

        assertEquals( listPeca.get(7), peca1);

        Peca newPeca1 = new Peca("Cooler", 1, 50, 40);

        DAO.getPeca().atualizar(newPeca1);

        listPeca = DAO.getPeca().encontrarTodos();

        assertEquals( listPeca.get(7), newPeca1);

        assertEquals("cooler", listPeca.get(7).getNome());
        assertEquals(1, listPeca.get(7).getQuantidade());
        assertEquals(50, listPeca.get(7).getValor());
        assertEquals(40, listPeca.get(7).getCusto());

        Peca newPeca2 = new Peca("HD", 5, 25.50, 20);

        DAO.getPeca().atualizar(newPeca2);

        listPeca = DAO.getPeca().encontrarTodos();

        assertEquals( listPeca.get(4), newPeca2);

        assertEquals("hd", listPeca.get(4).getNome());
        assertEquals(5, listPeca.get(4).getQuantidade());
        assertEquals(25.50, listPeca.get(4).getValor());
        assertEquals(20, listPeca.get(4).getCusto());
    }

    @Test
    void encontrarPorNome() {

        assertEquals(DAO.getPeca().encontrarPorNome("Cooler"), peca1);
        assertNull(DAO.getPeca().encontrarPorNome("Mouse"));
    }

    @Test
    void checarPorNome() {

        assertTrue(DAO.getPeca().checarPorNome("Cooler"));
        assertTrue(DAO.getPeca().checarPorNome("Processador"));
        assertTrue(DAO.getPeca().checarPorNome("SSD"));
        assertFalse(DAO.getPeca().checarPorNome("Mouse"));
    }

    @Test
    void checarQuantidade() {

        assertTrue(DAO.getPeca().checarQuantidade("Cooler", 2));
        assertTrue(DAO.getPeca().checarQuantidade("SSD", 10));

        assertFalse(DAO.getPeca().checarQuantidade("Processador", 6));
        assertFalse(DAO.getPeca().checarQuantidade("SSD", 11));
    }

    @Test
    void removerQuantidade() {

        assertEquals(4, DAO.getPeca().encontrarPorNome("cooler").getQuantidade());
        assertEquals(10, DAO.getPeca().encontrarPorNome("placa mae").getQuantidade());

        DAO.getPeca().removerQuantidade("cooler", 2);
        DAO.getPeca().removerQuantidade("placa mae", 6);

        assertEquals(2, DAO.getPeca().encontrarPorNome("cooler").getQuantidade());
        assertEquals(4, DAO.getPeca().encontrarPorNome("placa mae").getQuantidade());
    }

    @Test
    void adicionarQuantidade() {

        assertEquals(4, DAO.getPeca().encontrarPorNome("cooler").getQuantidade());
        assertEquals(10, DAO.getPeca().encontrarPorNome("placa de video").getQuantidade());

        DAO.getPeca().adicionarQuantidade("cooler", 2);
        DAO.getPeca().adicionarQuantidade("placa de video", 6);

        assertEquals(6, DAO.getPeca().encontrarPorNome("cooler").getQuantidade());
        assertEquals(16, DAO.getPeca().encontrarPorNome("placa de video").getQuantidade());
    }

    @Test
    void alertaDeQuantidade() {

        List<Peca> listPeca = DAO.getPeca().alertaDeQuantidade();

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

        Map<String, Integer> mapItensRetornados = DAO.getPeca().devolverQuantidade( mapItensServico);

        assertEquals(1, mapItensRetornados.size());
        assertTrue( mapItensRetornados.containsKey("mouse"));

        assertEquals(15, DAO.getPeca().encontrarPorNome("ram").getQuantidade());
        assertEquals(15, DAO.getPeca().encontrarPorNome("processador").getQuantidade());
    }

    @Test
    void removerPeca() {

        assertTrue(DAO.getPeca().checarPorNome("Processador"));

        DAO.getPeca().removerPeca("Processador");
        DAO.getPeca().removerPeca("Placa Mãe");

        assertFalse(DAO.getPeca().checarPorNome("Processador"));
        assertFalse(DAO.getPeca().checarPorNome("Placa Mãe"));
    }

    @Test
    void removerTodos() {

        List<Peca> listPeca = DAO.getPeca().encontrarTodos();

        assertEquals(9, listPeca.size());
        assertTrue(DAO.getPeca().checarPorNome("Processador"));
        assertEquals(10, DAO.getPeca().encontrarPorNome("ram").getQuantidade());

        DAO.getPeca().removerTodos();

        listPeca = DAO.getPeca().encontrarTodos();

        assertEquals(6, listPeca.size());
        assertFalse(DAO.getPeca().checarPorNome("Processador"));
        assertEquals(0, DAO.getPeca().encontrarPorNome("ram").getQuantidade());
    }
}
