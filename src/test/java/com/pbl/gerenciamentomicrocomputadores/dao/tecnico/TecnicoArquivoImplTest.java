package com.pbl.gerenciamentomicrocomputadores.dao.tecnico;

import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TecnicoArquivoImplTest {

    private Tecnico t1;
    private Tecnico t2;
    private Tecnico t3;
    private Tecnico t4;
    private Tecnico t5;

    @BeforeEach
    void setUp() {

        DAO.getTecnico().diretorioTest();

        t1 = new Tecnico("Leonard","Nova Jérsei","44444444444","55555555555");
        t2 = new Tecnico("Sheldon","Galveston, Texas","77777777777","33333333333");
        t3 = new Tecnico("Penny","Nebraska","99999999999","11111111111");
        t4 = new Tecnico("Amy","Glendale","88888888888","22222222222");
        t5 = new Tecnico("Bernadette","Califórnia","66666666666","00000000000");

        DAO.getTecnico().criar(t1);
        DAO.getTecnico().criar(t2);
        DAO.getTecnico().criar(t3);
        DAO.getTecnico().criar(t4);
        DAO.getTecnico().criar(t5);
    }

    @AfterEach
    void reset() {

        DAO.getTecnico().removerTodos();

        DAO.getTecnico().diretorioPadrao();
    }

    @Test
    void criar() {
        File diretorio = new File("dados salvos");
        File pasta1 = new File(diretorio +"/"+ "Teste Tecnico");
        File arquivoTecnico = new File(pasta1,"tecnicotest.dat");

        assertTrue(arquivoTecnico.exists());

        List<Tecnico> lista = DAO.getTecnico().encontrarTodos();

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
    void encontrarTodos() {

        List<Tecnico> lista = DAO.getTecnico().encontrarTodos();
        assertEquals(5,lista.size());

        assertEquals(lista.get(0), t1);
        assertEquals(lista.get(1), t2);
    }

    @Test
    void encontrarPorId() {

        Tecnico ById1 = DAO.getTecnico().encontrarPorId(1111);
        assertEquals(t1, ById1);

        Tecnico ById2 = DAO.getTecnico().encontrarPorId(1121);
        assertEquals(t2, ById2);
    }

    @Test
    void encontrarPorCpf() {

        Tecnico ByCpf1 = DAO.getTecnico().encontrarPorCpf("55555555555");
        assertEquals(t1, ByCpf1);

        Tecnico ByCpf2 = DAO.getTecnico().encontrarPorCpf("33333333333");
        assertEquals(t2, ByCpf2);
    }

    @Test
    void atualizar() {

        Tecnico newT1 = DAO.getTecnico().encontrarPorId(1111);
        assertEquals(t1, newT1);

        Tecnico newT2 = new Tecnico("Penny","Nebraska","99999999999","11111111111");
        newT2.setId(1111);

        DAO.getTecnico().atualizar(newT2);

        Tecnico newT3 = DAO.getTecnico().encontrarPorId(1111);

        assertEquals(newT3.getNome(),"Penny");
        assertEquals(newT3.getEndereco(),"Nebraska");
        assertEquals(newT3.getTelefone(),"99999999999");
        assertEquals(newT3.getCpf(),"11111111111");

        assertEquals(newT3, newT2);
    }

    @Test
    void remover() {

        DAO.getTecnico().remover(1111);
        DAO.getTecnico().remover(1131);
        DAO.getTecnico().remover(1151);

        Tecnico ById1 = DAO.getTecnico().encontrarPorId(1111);
        assertNull(ById1);

        Tecnico ById2 = DAO.getTecnico().encontrarPorId(1131);
        assertNull(ById2);

        Tecnico ById3 = DAO.getTecnico().encontrarPorId(1151);
        assertNull(ById3);

        List<Tecnico> lista = DAO.getTecnico().encontrarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void checarPorId() {

        boolean ById1 = DAO.getTecnico().checarPorId(1111);
        assertTrue(ById1);

        boolean ById2 = DAO.getTecnico().checarPorId(1121);
        assertTrue(ById2);

        boolean ById3 = DAO.getTecnico().checarPorId(1161);
        assertFalse(ById3);
    }

    @Test
    void checarPorCpf() {

        boolean ByCpf1 = DAO.getTecnico().checarPorCpf("55555555555");
        assertTrue(ByCpf1);

        boolean ByCpf2 = DAO.getTecnico().checarPorCpf("33333333333");
        assertTrue(ByCpf2);

        boolean ByCpf3 = DAO.getTecnico().checarPorCpf("11118888888");
        assertFalse(ByCpf3);
    }

    @Test
    void removerTodos() {

        List<Tecnico> lista = DAO.getTecnico().encontrarTodos();
        assertEquals(5, lista.size());

        DAO.getTecnico().removerTodos();

        lista = DAO.getTecnico().encontrarTodos();
        assertEquals(0, lista.size());
    }
}
