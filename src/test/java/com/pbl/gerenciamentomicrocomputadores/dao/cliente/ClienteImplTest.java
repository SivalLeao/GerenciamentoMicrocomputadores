package com.pbl.gerenciamentomicrocomputadores.dao.cliente;

import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Cliente;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteImplTest {

    private ClienteDAO dao;
    private Cliente c0;
    private Cliente c1;
    private Cliente c2;
    private Cliente c3;
    private Cliente c4;

    @BeforeEach
    void setUp() {
        dao = DAO.getCliente();

        c0 = new Cliente("Steve", "Rua Capricórnio num 1", "66666666666", "11111111111");
        c1 = new Cliente("Dayana", "Rua Aquário num 2", "77777777777", "22222222222");
        c2 = new Cliente("Camilla", "Rua Libra num 3", "88888888888", "33333333333");
        c3 = new Cliente("Bernardo", "Rua Virgem num 4", "99999999999", "44444444444");
        c4 = new Cliente("Morgana", "Rua Leão num 5", "00000000000", "55555555555");

        dao.criar( c0);
        dao.criar( c1);
        dao.criar( c2);
        dao.criar( c3);
        dao.criar( c4);
    }

    @AfterEach
    void reset() {

        dao.removerTodos();
    }

    @Test
    void criar () {
        List<Cliente> lista = dao.encontrarTodos();

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
    void encontrarTodos () {
        List<Cliente> lista = dao.encontrarTodos();

        assertEquals( 5, lista.size());

        assertEquals( lista.get(0), c0);
        assertEquals( lista.get(1), c1);
    }

    @Test
    void encontrarPorId () {
        Cliente ById0 = dao.encontrarPorId(1112);
        assertEquals( c0, ById0);

        Cliente ById1 = dao.encontrarPorId(1122);
        assertEquals( c1, ById1);
    }

    @Test
    void encontrarPorCpf () {
        Cliente ByCpf0 = dao.encontrarPorCpf("11111111111");
        assertEquals( c0, ByCpf0);

        Cliente ByCpf1 = dao.encontrarPorCpf("22222222222");
        assertEquals( c1, ByCpf1);
    }

    @Test
    void atualizar () {
        Cliente newC0 = dao.encontrarPorId(1112);
        assertEquals( newC0, c0);

        Cliente newC1 = new Cliente("Camilla", "Rua Libra num 3", "88888888888", "33333333333");
        newC1.setId(1112);

        dao.atualizar(newC1);

        Cliente newC2 = dao.encontrarPorId(1112);

        assertEquals( newC2.getNome(), "Camilla");
        assertEquals( newC2.getEndereco(), "Rua Libra num 3");
        assertEquals( newC2.getTelefone(), "88888888888");
        assertEquals( newC2.getCpf(), "33333333333");

        assertEquals( newC2, newC1);
    }

    @Test
    void remover(){
        dao.remover(1112);
        dao.remover(1132);
        dao.remover(1152);

        Cliente ById0 = dao.encontrarPorId(1112);
        assertNull(ById0);

        Cliente ById1 = dao.encontrarPorId(1132);
        assertNull(ById1);

        Cliente ById2 = dao.encontrarPorId(1152);
        assertNull(ById2);

        List<Cliente> lista = dao.encontrarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void checarPorId(){
        boolean ById0 = dao.checarPorId(1112);
        assertTrue(ById0);

        boolean ById1 = dao.checarPorId(1122);
        assertTrue(ById1);

        boolean ById2 = dao.checarPorId(1162);
        assertFalse(ById2);
    }

    @Test
    void checarPorCpf(){
        boolean ByCpf0 = dao.checarPorCpf("44444444444");
        assertTrue(ByCpf0);

        boolean ByCpf1 = dao.checarPorCpf("55555555555");
        assertTrue(ByCpf1);

        boolean ByCpf2 = dao.checarPorCpf("66666666666");
        assertFalse(ByCpf2);
    }

    @Test
    void removerTodos(){
        dao.removerTodos();

        List<Cliente> lista = dao.encontrarTodos();

        assertEquals(0, lista.size());
    }
}
