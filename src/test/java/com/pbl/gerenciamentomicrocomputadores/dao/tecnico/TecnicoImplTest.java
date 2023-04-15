package com.pbl.gerenciamentomicrocomputadores.dao.tecnico;

import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TecnicoImplTest {

    private TecnicoDAO dao;
    private Tecnico t1;
    private Tecnico t2;
    private Tecnico t3;
    private Tecnico t4;
    private Tecnico t5;

    @BeforeEach
    void setUp () {
        dao = new TecnicoImpl();

        t1 = new Tecnico("Leonard","Nova Jérsei","44444444444","55555555555");
        t2 = new Tecnico("Sheldon","Galveston, Texas","77777777777","33333333333");
        t3 = new Tecnico("Penny","Nebraska","99999999999","11111111111");
        t4 = new Tecnico("Amy","Glendale","88888888888","22222222222");
        t5 = new Tecnico("Bernadette","Califórnia","66666666666","00000000000");

        dao.criar(t1);
        dao.criar(t2);
        dao.criar(t3);
        dao.criar(t4);
        dao.criar(t5);
    }

    @Test
    void criar () {
        List<Tecnico> lista = dao.encontrarTodos();

        assertEquals(lista.get(0).getNome(),"Leonard");
        assertEquals(lista.get(0).getEndereco(),"Nova Jérsei");
        assertEquals(lista.get(0).getTelefone(),"44444444444");
        assertEquals(lista.get(0).getCpf(),"55555555555");
        assertEquals(lista.get(0).getId(), 1111);

        assertEquals(lista.get(1).getNome(),"Sheldon");
        assertEquals(lista.get(1).getEndereco(), "Galveston, Texas");
        assertEquals(lista.get(1).getTelefone(),"77777777777");
        assertEquals(lista.get(1).getCpf(),"33333333333");
        assertEquals(lista.get(1).getId(),1121);
    }

    @Test
    void encontrarTodos () {
        List<Tecnico> lista = dao.encontrarTodos();
        assertEquals(5,lista.size());

        assertEquals(lista.get(0), t1);
        assertEquals(lista.get(1), t2);
    }

    @Test
    void encontrarPorId () {
        Tecnico ById1 = dao.encontrarPorId(1111);
        assertEquals(t1, ById1);

        Tecnico ById2 = dao.encontrarPorId(1121);
        assertEquals(t2, ById2);
    }

    @Test
    void encontrarPorCpf () {
        Tecnico ByCpf1 = dao.encontrarPorCpf("55555555555");
        assertEquals(t1, ByCpf1);

        Tecnico ByCpf2 = dao.encontrarPorCpf("33333333333");
        assertEquals(t2, ByCpf2);
    }

    @Test
    void atualizar () {
        Tecnico newT1 = dao.encontrarPorId(1111);
        assertEquals(t1, newT1);

        Tecnico newT2 = new Tecnico("Penny","Nebraska","99999999999","11111111111");
        newT2.setId(1111);

        dao.atualizar(newT2);

        Tecnico newT3 = dao.encontrarPorId(1111);

        assertEquals(newT3.getNome(),"Penny");
        assertEquals(newT3.getEndereco(),"Nebraska");
        assertEquals(newT3.getTelefone(),"99999999999");
        assertEquals(newT3.getCpf(),"11111111111");

        assertEquals(newT3, newT2);
    }

    @Test
    void remover () {

        dao.remover(1111);
        dao.remover(1131);
        dao.remover(1151);

        Tecnico ById1 = dao.encontrarPorId(1111);
        assertNull(ById1);

        Tecnico ById2 = dao.encontrarPorId(1131);
        assertNull(ById2);

        Tecnico ById3 = dao.encontrarPorId(1151);
        assertNull(ById3);

        List<Tecnico> lista = dao.encontrarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void checarPorId () {

        boolean ById1 = dao.checarPorId(1111);
        assertTrue(ById1);

        boolean ById2 = dao.checarPorId(1121);
        assertTrue(ById2);

        boolean ById3 = dao.checarPorId(1161);
        assertFalse(ById3);
    }

    @Test
    void checarPorCpf () {

        boolean ByCpf1 = dao.checarPorCpf("55555555555");
        assertTrue(ByCpf1);

        boolean ByCpf2 = dao.checarPorCpf("33333333333");
        assertTrue(ByCpf2);

        boolean ByCpf3 = dao.checarPorCpf("11118888888");
        assertFalse(ByCpf3);
    }

    @Test
    void removerTodos () {

        List<Tecnico> lista = dao.encontrarTodos();
        assertEquals(5, lista.size());

        dao.removerTodos();

        lista = dao.encontrarTodos();
        assertEquals(0, lista.size());
    }
}