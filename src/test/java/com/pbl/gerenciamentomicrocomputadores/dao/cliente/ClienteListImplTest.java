package com.pbl.gerenciamentomicrocomputadores.dao.cliente;

import com.pbl.gerenciamentomicrocomputadores.model.Cliente;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClienteListImplTest {
    @Test
    void create(){
        ClienteDAO dao = new ClienteImpl();

        //Declaracao
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();
        //
        c1.setNome("Steve");
        c1.setCpf("99999999999");
        c1.setEndereco("Rua A num 1");
        c1.setTelefone("75777777777");
        c1.setId(1112);
        //
        c2.setNome("Steve");
        c2.setCpf("99999999999");
        c2.setEndereco("Rua A num 1");
        c2.setTelefone("75777777777");
        c2.setId(1112);
        //
        dao.create(c1);

        assertEquals(c1, c1);

    }

    @Test
    void findById(){
        ClienteDAO dao = new ClienteImpl();

        //Declaracao
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();
        //
        c1.setNome("Steve");
        c1.setCpf("99999999999");
        c1.setEndereco("Rua A num 1");
        c1.setTelefone("75777777777");
        //
        c2.setNome("Alex");
        c2.setCpf("88888888888");
        c2.setEndereco("Rua B num 2");
        c2.setTelefone("71666666666");
        //



        dao.create(c1);
        dao.create(c2);


        assertEquals(1112, c1.getId());
        assertEquals(1122, c2.getId());
    }

    @Test
    void update(){
        ClienteDAO dao = new ClienteImpl();
        //Declaracao
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();
        //
        c1.setNome("Steve");
        c1.setCpf("99999999999");
        c1.setEndereco("Rua A num 1");
        c1.setTelefone("75777777777");


        dao.create(c1);

        //
        c1.setNome("Alex");
        c1.setCpf("88888888888");
        c1.setEndereco("Rua B num 2");
        c1.setTelefone("71666666666");


        dao.update(c1);

        assertEquals(1112, c1.getId()); // para atualizado o Id conserva
        assertEquals("Alex", c1.getNome());
    }
    @Test
    void delete(){
        ClienteDAO dao = new ClienteImpl();
        //Declaracao
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();
        Cliente c3 = new Cliente();
        // Cliente 1
        c1.setNome("Steve");
        c1.setCpf("99999999999");
        c1.setEndereco("Rua A num 1");
        c1.setTelefone("75777777777");
        // Cliente 2
        c2.setNome("Alex");
        c2.setCpf("88888888888");
        c2.setEndereco("Rua B num 2");
        c2.setTelefone("75666666666");
        // Cliente 3
        c3.setNome("Herobrine");
        c3.setCpf("77777777777");
        c3.setEndereco("Rua C num 3");
        c3.setTelefone("75555555555");
        //
        dao.create(c1);
        dao.create(c2);
        dao.create(c3);


        dao.delete(1122);

        Cliente ById = dao.findById(1122);
        assertNull(ById);

        List<Cliente> many = dao.findMany();
        assertEquals(2, many.size());

    }

    @Test
    void fandMany() {
        ClienteDAO dao = new ClienteImpl();

        //Declaracao
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();
        Cliente c3 = new Cliente();
        // Cliente 1
        c1.setNome("Steve");
        c1.setCpf("99999999999");
        c1.setEndereco("Rua A num 1");
        c1.setTelefone("75777777777");
        // Cliente 2
        c2.setNome("Alex");
        c2.setCpf("88888888888");
        c2.setEndereco("Rua B num 2");
        c2.setTelefone("75666666666");
        // Cliente 3
        c3.setNome("Herobrine");
        c3.setCpf("77777777777");
        c3.setEndereco("Rua C num 3");
        c3.setTelefone("75555555555");
        //
        dao.create(c1);
        dao.create(c2);
        dao.create(c3);


        List<Cliente> many = dao.findMany();
        int IdMany = many.get(1).getId();

        assertEquals(3, many.size());
        assertEquals(1122, IdMany);
    }

    @Test
    void checkId(){
        ClienteDAO dao = new ClienteImpl();
        //Declaracao
        Cliente c1 = new Cliente();
        //
        c1.setNome("Steve");
        c1.setCpf("99999999999");
        c1.setEndereco("Rua A num 1");
        c1.setTelefone("75777777777");

        dao.create(c1);

        boolean checkId =dao.checkById(1112);
        boolean checkIdFalse= dao.checkById(0);
        assertEquals(true, checkId);
        assertEquals(false, checkIdFalse);
    }

    @Test
    void deleteMany(){
        ClienteDAO dao = new ClienteImpl();

        //Declaracao
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();
        Cliente c3 = new Cliente();
        // Cliente 1
        c1.setNome("Steve");
        c1.setCpf("99999999999");
        c1.setEndereco("Rua A num 1");
        c1.setTelefone("75777777777");
        // Cliente 2
        c2.setNome("Alex");
        c2.setCpf("88888888888");
        c2.setEndereco("Rua B num 2");
        c2.setTelefone("75666666666");
        // Cliente 3
        c3.setNome("Herobrine");
        c3.setCpf("77777777777");
        c3.setEndereco("Rua C num 3");
        c3.setTelefone("75555555555");
        //
        dao.create(c1);
        dao.create(c2);
        dao.create(c3);



        assertEquals(3, dao.findMany().size());
        dao.deleteMany();
        assertEquals(0, dao.findMany().size());
    }

}
