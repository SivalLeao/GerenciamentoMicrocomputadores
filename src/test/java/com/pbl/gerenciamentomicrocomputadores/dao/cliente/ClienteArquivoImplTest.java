package com.pbl.gerenciamentomicrocomputadores.dao.cliente;

import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClienteArquivoImplTest {

    private Cliente c0;
    private Cliente c1;
    private Cliente c2;
    private Cliente c3;
    private Cliente c4;

    @BeforeEach
    void setUp() {

        DAO.getCliente().diretorioTest();

        c0 = new Cliente("Steve", "Rua Capricórnio num 1", "66666666666", "11111111111");
        c1 = new Cliente("Dayana", "Rua Aquário num 2", "77777777777", "22222222222");
        c2 = new Cliente("Camilla", "Rua Libra num 3", "88888888888", "33333333333");
        c3 = new Cliente("Bernardo", "Rua Virgem num 4", "99999999999", "44444444444");
        c4 = new Cliente("Morgana", "Rua Leão num 5", "00000000000", "55555555555");

        DAO.getCliente().criar( c0);
        DAO.getCliente().criar( c1);
        DAO.getCliente().criar( c2);
        DAO.getCliente().criar( c3);
        DAO.getCliente().criar( c4);
    }

    @AfterEach
    void reset() {

        DAO.getCliente().removerTodos();
        
        DAO.getCliente().diretorioPadrao();
    }

    @Test
    void criar() {

        File diretorio = new File("dados salvos");
        File pasta1 = new File(diretorio +"/"+ "Test Cliente");
        File arquivoCliente = new File(pasta1,"clienteTest.dat");

        assertTrue(arquivoCliente.exists());

        List<Cliente> lista = DAO.getCliente().encontrarTodos();

        assertEquals( lista.get(0).getNome(), "Steve");
        assertEquals( lista.get(0).getEndereco(), "Rua Capricórnio num 1");
        assertEquals( lista.get(0).getTelefone(), "66666666666");
        assertEquals( lista.get(0).getCpf(), "11111111111");
        assertEquals( lista.get(0).getId(), 1112);

        assertEquals( lista.get(1).getNome(), "Dayana");
        assertEquals( lista.get(1).getEndereco(), "Rua Aquário num 2");
        assertEquals( lista.get(1).getTelefone(), "77777777777");
        assertEquals( lista.get(1).getCpf(), "22222222222");
        assertEquals( lista.get(1).getId(), 1122);
    }

    @Test
    void encontrarTodos() {

        List<Cliente> lista = DAO.getCliente().encontrarTodos();

        assertEquals( 5, lista.size());

        assertEquals( lista.get(0), c0);
        assertEquals( lista.get(1), c1);
    }

    @Test
    void encontrarPorId() {

        Cliente ById0 = DAO.getCliente().encontrarPorId(1112);
        assertEquals( c0, ById0);

        Cliente ById1 = DAO.getCliente().encontrarPorId(1122);
        assertEquals( c1, ById1);
    }

    @Test
    void encontrarPorCpf() {

        Cliente ByCpf0 = DAO.getCliente().encontrarPorCpf("11111111111");
        assertEquals( c0, ByCpf0);

        Cliente ByCpf1 = DAO.getCliente().encontrarPorCpf("22222222222");
        assertEquals( c1, ByCpf1);
    }

    @Test
    void atualizar() {

        Cliente newC0 = DAO.getCliente().encontrarPorId(1112);
        assertEquals( newC0, c0);

        Cliente newC1 = new Cliente("Camilla", "Rua Libra num 3", "88888888888", "33333333333");
        newC1.setId(1112);

        DAO.getCliente().atualizar(newC1);

        Cliente newC2 = DAO.getCliente().encontrarPorId(1112);

        assertEquals( newC2.getNome(), "Camilla");
        assertEquals( newC2.getEndereco(), "Rua Libra num 3");
        assertEquals( newC2.getTelefone(), "88888888888");
        assertEquals( newC2.getCpf(), "33333333333");

        assertEquals( newC2, newC1);
    }

    @Test
    void remover() {

        DAO.getCliente().remover(1112);
        DAO.getCliente().remover(1132);
        DAO.getCliente().remover(1152);

        Cliente ById0 = DAO.getCliente().encontrarPorId(1112);
        assertNull(ById0);

        Cliente ById1 = DAO.getCliente().encontrarPorId(1132);
        assertNull(ById1);

        Cliente ById2 = DAO.getCliente().encontrarPorId(1152);
        assertNull(ById2);

        List<Cliente> lista = DAO.getCliente().encontrarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void checarPorId() {

        boolean ById0 = DAO.getCliente().checarPorId(1112);
        assertTrue(ById0);

        boolean ById1 = DAO.getCliente().checarPorId(1122);
        assertTrue(ById1);

        boolean ById2 = DAO.getCliente().checarPorId(1162);
        assertFalse(ById2);
    }

    @Test
    void checarPorCpf() {

        boolean ByCpf0 = DAO.getCliente().checarPorCpf("44444444444");
        assertTrue(ByCpf0);

        boolean ByCpf1 = DAO.getCliente().checarPorCpf("55555555555");
        assertTrue(ByCpf1);

        boolean ByCpf2 = DAO.getCliente().checarPorCpf("66666666666");
        assertFalse(ByCpf2);
    }

    @Test
    void removerTodos() {

        DAO.getCliente().removerTodos();

        List<Cliente> lista = DAO.getCliente().encontrarTodos();

        assertEquals(0, lista.size());
    }

}
