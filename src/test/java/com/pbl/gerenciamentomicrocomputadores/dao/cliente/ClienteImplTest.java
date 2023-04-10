package com.pbl.gerenciamentomicrocomputadores.dao.cliente;

import com.pbl.gerenciamentomicrocomputadores.model.Cliente;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteImplTest {

    @Test
    void create () {

        ClienteDAO dao = new ClienteImpl();

        Cliente c0 = new Cliente("Steve", "Rua Capricórnio num 1", "66666666666", "11111111111");
        Cliente c1 = new Cliente("Dayana", "Rua Aquário num 2", "77777777777", "22222222222");

        dao.create( c0);
        dao.create( c1);

        List<Cliente> lista = dao.findMany();

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
    void findMany () {

        ClienteDAO dao = new ClienteImpl();

        Cliente c0 = new Cliente("Steve", "Rua Capricórnio num 1", "66666666666", "11111111111");
        Cliente c1 = new Cliente("Dayana", "Rua Aquário num 2", "77777777777", "22222222222");

        dao.create( c0);
        dao.create( c1);

        List<Cliente> lista = dao.findMany();

        assertEquals( 2, lista.size());

        assertEquals( lista.get(0), c0);
        assertEquals( lista.get(1), c1);
    }

    @Test
    void findById () {

        ClienteDAO dao = new ClienteImpl();

        Cliente c0 = new Cliente("Steve", "Rua Capricórnio num 1", "66666666666", "11111111111");
        Cliente c1 = new Cliente("Dayana", "Rua Aquário num 2", "77777777777", "22222222222");

        dao.create( c0);
        dao.create( c1);

        Cliente ById0 = dao.findById(1112);
        assertEquals( c0, ById0);

        Cliente ById1 = dao.findById(1122);
        assertEquals( c1, ById1);
    }

    @Test
    void findByCpf () {

        ClienteDAO dao = new ClienteImpl();

        Cliente c0 = new Cliente("Steve", "Rua Capricórnio num 1", "66666666666", "11111111111");
        Cliente c1 = new Cliente("Dayana", "Rua Aquário num 2", "77777777777", "22222222222");

        dao.create( c0);
        dao.create( c1);

        Cliente ByCpf0 = dao.findByCpf("11111111111");
        assertEquals( c0, ByCpf0);

        Cliente ByCpf1 = dao.findByCpf("22222222222");
        assertEquals( c1, ByCpf1);
    }

    @Test
    void update () {

        ClienteDAO dao = new ClienteImpl();

        Cliente c0 = new Cliente("Steve", "Rua Capricórnio num 1", "66666666666", "11111111111");
        Cliente c1 = new Cliente("Dayana", "Rua Aquário num 2", "77777777777", "22222222222");

        dao.create( c0);
        dao.create( c1);

        Cliente newC0 = dao.findById(1112);
        assertEquals( newC0, c0);

        Cliente newC1 = new Cliente("Camilla", "Rua Libra num 3", "88888888888", "33333333333");
        newC1.setId(1112);

        dao.update(newC1);

        Cliente newC2 = dao.findById(1112);

        assertEquals( newC2.getNome(), "Camilla");
        assertEquals( newC2.getEndereco(), "Rua Libra num 3");
        assertEquals( newC2.getTelefone(), "88888888888");
        assertEquals( newC2.getCpf(), "33333333333");

        assertEquals( newC2, newC1);
    }

    @Test
    void delete(){

        ClienteDAO dao = new ClienteImpl();

        Cliente c0 = new Cliente("Steve", "Rua Capricórnio num 1", "66666666666", "11111111111");
        Cliente c1 = new Cliente("Dayana", "Rua Aquário num 2", "77777777777", "22222222222");
        Cliente c2 = new Cliente("Camilla", "Rua Libra num 3", "88888888888", "33333333333");
        Cliente c3 = new Cliente("Bernardo", "Rua Virgem num 4", "99999999999", "44444444444");
        Cliente c4 = new Cliente("Morgana", "Rua Leão num 5", "00000000000", "55555555555");

        dao.create( c0);
        dao.create( c1);
        dao.create( c2);
        dao.create( c3);
        dao.create( c4);

        dao.delete(1112);
        dao.delete(1132);
        dao.delete(1152);

        Cliente ById0 = dao.findById(1112);
        assertNull(ById0);

        Cliente ById1 = dao.findById(1132);
        assertNull(ById1);

        Cliente ById2 = dao.findById(1152);
        assertNull(ById2);

        List<Cliente> lista = dao.findMany();
        assertEquals(2, lista.size());
    }

    @Test
    void checkById(){

        ClienteDAO dao = new ClienteImpl();

        Cliente c0 = new Cliente("Bernardo", "Rua Virgem num 4", "99999999999", "44444444444");
        Cliente c1 = new Cliente("Morgana", "Rua Leão num 5", "00000000000", "55555555555");

        dao.create( c0);
        dao.create( c1);

        boolean ById0 = dao.checkById(1112);
        assertTrue(ById0);

        boolean ById1 = dao.checkById(1122);
        assertTrue(ById1);

        boolean ById2 = dao.checkById(1132);
        assertFalse(ById2);
    }

    @Test
    void checkByCpf(){

        ClienteDAO dao = new ClienteImpl();

        Cliente c0 = new Cliente("Bernardo", "Rua Virgem num 4", "99999999999", "44444444444");
        Cliente c1 = new Cliente("Morgana", "Rua Leão num 5", "00000000000", "55555555555");

        dao.create( c0);
        dao.create( c1);

        boolean ByCpf0 = dao.checkByCpf("44444444444");
        assertTrue(ByCpf0);

        boolean ByCpf1 = dao.checkByCpf("55555555555");
        assertTrue(ByCpf1);

        boolean ByCpf2 = dao.checkByCpf("66666666666");
        assertFalse(ByCpf2);
    }

    @Test
    void deleteMany(){

        ClienteDAO dao = new ClienteImpl();

        Cliente c0 = new Cliente("Camilla", "Rua Libra num 3", "88888888888", "33333333333");
        Cliente c1 = new Cliente("Bernardo", "Rua Virgem num 4", "99999999999", "44444444444");
        Cliente c2 = new Cliente("Morgana", "Rua Leão num 5", "00000000000", "55555555555");

        dao.create( c0);
        dao.create( c1);
        dao.create( c2);

        dao.deleteMany();

        List<Cliente> lista = dao.findMany();

        assertEquals(0, lista.size());
    }

}
