package com.pbl.gerenciamentomicrocomputadores.dao.tecnico;

import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TecnicoListImplTest {
    @Test
    void create(){
        TecnicoDAO dao = new TecnicoImpl();

        //Declaracao
        Tecnico t1 = new Tecnico();
        Tecnico t2 = new Tecnico();
        //
        t1.setNome("Steve");
        t1.setCpf("99999999999");
        t1.setEndereco("Rua A num 1");
        t1.setTelefone("75777777777");
        t1.setId(1111);
        //
        t2.setNome("Steve");
        t2.setCpf("99999999999");
        t2.setEndereco("Rua A num 1");
        t2.setTelefone("75777777777");
        t2.setId(1111);
        //
        dao.create(t1);

        assertEquals(t1, t1);

    }

    @Test
    void findById(){
        TecnicoDAO dao = new TecnicoImpl();

        //Declaracao
        Tecnico t1 = new Tecnico();
        Tecnico t2 = new Tecnico();
        //
        t1.setNome("Steve");
        t1.setCpf("99999999999");
        t1.setEndereco("Rua A num 1");
        t1.setTelefone("75777777777");
        //
        t2.setNome("Alex");
        t2.setCpf("88888888888");
        t2.setEndereco("Rua B num 2");
        t2.setTelefone("71666666666");
        //



        dao.create(t1);
        dao.create(t2);


        assertEquals(1111, t1.getId());
        assertEquals(1121, t2.getId());
    }

    @Test
    void update(){
        TecnicoDAO dao = new TecnicoImpl();
        //Declaracao
        Tecnico t1 = new Tecnico();
        Tecnico t2 = new Tecnico();
        //
        t1.setNome("Steve");
        t1.setCpf("99999999999");
        t1.setEndereco("Rua A num 1");
        t1.setTelefone("75777777777");


        dao.create(t1);

        //
        t1.setNome("Alex");
        t1.setCpf("88888888888");
        t1.setEndereco("Rua B num 2");
        t1.setTelefone("71666666666");


        dao.update(t1);

        assertEquals(1111, t1.getId()); // para atualizado o Id conserva
        assertEquals("Alex", t1.getNome());
    }
    @Test
    void delete(){
        TecnicoDAO dao = new TecnicoImpl();
        //Declaracao
        Tecnico t1 = new Tecnico();
        Tecnico t2 = new Tecnico();
        Tecnico t3 = new Tecnico();
        // Tecnico 1
        t1.setNome("Steve");
        t1.setCpf("99999999999");
        t1.setEndereco("Rua A num 1");
        t1.setTelefone("75777777777");
        // Tecnico 2
        t2.setNome("Alex");
        t2.setCpf("88888888888");
        t2.setEndereco("Rua B num 2");
        t2.setTelefone("75666666666");
        // Tecnico 3
        t3.setNome("Herobrine");
        t3.setCpf("77777777777");
        t3.setEndereco("Rua C num 3");
        t3.setTelefone("75555555555");
        //
        dao.create(t1);
        dao.create(t2);
        dao.create(t3);


        dao.delete(1121);

        Tecnico ById = dao.findById(1121);
        assertNull(ById);

        List<Tecnico> many = dao.findMany();
        assertEquals(2, many.size());

    }

    @Test
    void fandMany() {
        TecnicoDAO dao = new TecnicoImpl();

        //Declaracao
        Tecnico t1 = new Tecnico();
        Tecnico t2 = new Tecnico();
        Tecnico t3 = new Tecnico();
        // Tecnico 1
        t1.setNome("Steve");
        t1.setCpf("99999999999");
        t1.setEndereco("Rua A num 1");
        t1.setTelefone("75777777777");
        // Tecnico 2
        t2.setNome("Alex");
        t2.setCpf("88888888888");
        t2.setEndereco("Rua B num 2");
        t2.setTelefone("75666666666");
        // Tecnico 3
        t3.setNome("Herobrine");
        t3.setCpf("77777777777");
        t3.setEndereco("Rua C num 3");
        t3.setTelefone("75555555555");
        //
        dao.create(t1);
        dao.create(t2);
        dao.create(t3);


        List<Tecnico> many = dao.findMany();
        int IdMany = many.get(1).getId();

        assertEquals(3, many.size());
        assertEquals(1121, IdMany);
    }

    @Test
    void checkId(){
        TecnicoDAO dao = new TecnicoImpl();
        //Declaracao
        Tecnico t1 = new Tecnico();
        //
        t1.setNome("Steve");
        t1.setCpf("99999999999");
        t1.setEndereco("Rua A num 1");
        t1.setTelefone("75777777777");

        dao.create(t1);

        boolean checkId =dao.checkById(1111);
        boolean checkIdFalse= dao.checkById(0);
        assertEquals(true, checkId);
        assertEquals(false, checkIdFalse);
    }

    @Test
    void deleteMany(){
        TecnicoDAO dao = new TecnicoImpl();

        //Declaracao
        Tecnico t1 = new Tecnico();
        Tecnico t2 = new Tecnico();
        Tecnico t3 = new Tecnico();
        // Tecnico 1
        t1.setNome("Steve");
        t1.setCpf("99999999999");
        t1.setEndereco("Rua A num 1");
        t1.setTelefone("75777777777");
        // Tecnico 2
        t2.setNome("Alex");
        t2.setCpf("88888888888");
        t2.setEndereco("Rua B num 2");
        t2.setTelefone("75666666666");
        // Tecnico 3
        t3.setNome("Herobrine");
        t3.setCpf("77777777777");
        t3.setEndereco("Rua C num 3");
        t3.setTelefone("75555555555");
        //
        dao.create(t1);
        dao.create(t2);
        dao.create(t3);



        assertEquals(3, dao.findMany().size());
        dao.deleteMany();
        assertEquals(0, dao.findMany().size());
    }
}
