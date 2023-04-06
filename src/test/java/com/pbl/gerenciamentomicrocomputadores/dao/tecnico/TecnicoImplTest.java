package com.pbl.gerenciamentomicrocomputadores.dao.tecnico;

import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TecnicoImplTest {

    @Test
    void findMany () {

        TecnicoDAO dao = new TecnicoImpl();

        Tecnico t1 = new Tecnico("Leonard","Nova Jérsei","44444444444","55555555555");
        Tecnico t2 = new Tecnico("Sheldon","Galveston, Texas","77777777777","33333333333");

        dao.create(t1);
        dao.create(t2);

        List<Tecnico> lista = dao.findMany();
        assertEquals(2,lista.size());

        assertEquals(lista.get(0), t1);
        assertEquals(lista.get(1), t2);
    }

    @Test
    void create () {

        TecnicoDAO dao = new TecnicoImpl();

        Tecnico t1 = new Tecnico("Leonard","Nova Jérsei","44444444444","55555555555");
        Tecnico t2 = new Tecnico("Sheldon","Galveston, Texas","77777777777","33333333333");

        dao.create(t1);
        dao.create(t2);

        List<Tecnico> lista = dao.findMany();

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
    void findById () {

        TecnicoDAO dao = new TecnicoImpl();

        Tecnico t1 = new Tecnico("Leonard","Nova Jérsei","44444444444","55555555555");
        Tecnico t2 = new Tecnico("Sheldon","Galveston, Texas","77777777777","33333333333");

        dao.create(t1);
        dao.create(t2);

        Tecnico ById1 = dao.findById(1111);
        assertEquals(t1, ById1);

        Tecnico ById2 = dao.findById(1121);
        assertEquals(t2, ById2);
    }

    @Test
    void findByCpf () {

        TecnicoDAO dao = new TecnicoImpl();

        Tecnico t1 = new Tecnico("Leonard","Nova Jérsei","44444444444","55555555555");
        Tecnico t2 = new Tecnico("Sheldon","Galveston, Texas","77777777777","33333333333");

        dao.create(t1);
        dao.create(t2);

        Tecnico ByCpf1 = dao.findByCpf("55555555555");
        assertEquals(t1, ByCpf1);

        Tecnico ByCpf2 = dao.findByCpf("33333333333");
        assertEquals(t2, ByCpf2);
    }

    @Test
    void update () {

        TecnicoDAO dao = new TecnicoImpl();

        Tecnico t1 = new Tecnico("Leonard","Nova Jérsei","44444444444","55555555555");
        Tecnico t2 = new Tecnico("Sheldon","Galveston, Texas","77777777777","33333333333");

        dao.create(t1);
        dao.create(t2);


        Tecnico newT1 = dao.findById(1111);
        assertEquals(t1, newT1);

        Tecnico newT2 = new Tecnico("Penny","Nebraska","99999999999","11111111111");
        newT2.setId(1111);

        dao.update(newT2);

        Tecnico newT3 = dao.findById(1111);

        assertEquals(newT3.getNome(),"Penny");
        assertEquals(newT3.getEndereco(),"Nebraska");
        assertEquals(newT3.getTelefone(),"99999999999");
        assertEquals(newT3.getCpf(),"11111111111");

        assertEquals(newT3, newT2);
    }

    @Test
    void delete () {

        TecnicoDAO dao = new TecnicoImpl();

        Tecnico t1 = new Tecnico("Leonard","Nova Jérsei","44444444444","55555555555");
        Tecnico t2 = new Tecnico("Sheldon","Galveston, Texas","77777777777","33333333333");
        Tecnico t3 = new Tecnico("Penny","Nebraska","99999999999","11111111111");
        Tecnico t4 = new Tecnico("Amy","Glendale","88888888888","22222222222");
        Tecnico t5 = new Tecnico("Bernadette","Califórnia","66666666666","00000000000");

        dao.create(t1);
        dao.create(t2);
        dao.create(t3);
        dao.create(t4);
        dao.create(t5);

        dao.delete(1111);
        dao.delete(1131);
        dao.delete(1151);

        Tecnico ById1 = dao.findById(1111);
        assertNull(ById1);

        Tecnico ById2 = dao.findById(1131);
        assertNull(ById2);

        Tecnico ById3 = dao.findById(1151);
        assertNull(ById3);

        List<Tecnico> lista = dao.findMany();
        assertEquals(2, lista.size());
    }

    @Test
    void checkId () {

        TecnicoDAO dao = new TecnicoImpl();

        Tecnico t1 = new Tecnico("Leonard","Nova Jérsei","44444444444","55555555555");
        Tecnico t2 = new Tecnico("Sheldon","Galveston, Texas","77777777777","33333333333");

        dao.create(t1);
        dao.create(t2);

        Boolean ById1 = dao.checkById(1111);
        assertTrue(ById1);

        Boolean ById2 = dao.checkById(1121);
        assertTrue(ById2);

        Boolean ById3 = dao.checkById(1131);
        assertFalse(ById3);
    }

    @Test
    void checkByCpf () {

        TecnicoDAO dao = new TecnicoImpl();

        Tecnico t1 = new Tecnico("Leonard","Nova Jérsei","44444444444","55555555555");
        Tecnico t2 = new Tecnico("Sheldon","Galveston, Texas","77777777777","33333333333");

        dao.create(t1);
        dao.create(t2);

        Boolean ByCpf1 = dao.checkByCpf("55555555555");
        assertTrue(ByCpf1);

        Boolean ByCpf2 = dao.checkByCpf("33333333333");
        assertTrue(ByCpf2);

        Boolean ByCpf3 = dao.checkByCpf("11111111111");
        assertFalse(ByCpf3);
    }

    @Test
    void deleteMany () {

        TecnicoDAO dao = new TecnicoImpl();

        Tecnico t1 = new Tecnico("Leonard","Nova Jérsei","44444444444","55555555555");
        Tecnico t2 = new Tecnico("Sheldon","Galveston, Texas","77777777777","33333333333");
        Tecnico t3 = new Tecnico("Penny","Nebraska","99999999999","11111111111");

        dao.create(t1);
        dao.create(t2);
        dao.create(t3);

        dao.deleteMany();

        List<Tecnico> lista = dao.findMany();

        assertEquals(0, lista.size());
    }

}