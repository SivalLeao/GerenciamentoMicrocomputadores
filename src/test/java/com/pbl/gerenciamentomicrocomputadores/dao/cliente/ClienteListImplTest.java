package com.pbl.gerenciamentomicrocomputadores.dao.cliente;

import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Cliente;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClienteListImplTest {

    @Test
    void findMany() {

        Cliente cliente0 = new Cliente("Steve", "Rua A num 1", "75985085085", "71071071021");
        Cliente cliente1 = new Cliente("Camilla", "Rua Peixes num 14", "75995095095", "16016016021");
        Cliente cliente2 = new Cliente("Dayana", "Rua Pôr do Sol num 8", "75925025025", "32032032021");

        DAO.getCliente().create( cliente0);
        DAO.getCliente().create( cliente1);
        DAO.getCliente().create( cliente2);

        List<Cliente> lista = DAO.getCliente().findMany();

        assertEquals( lista.get(0), cliente1);
        assertEquals( lista.get(1), cliente1);
        assertEquals( lista.get(2), cliente2);
    }

    @Test
    void create(){

        Cliente cliente1 = new Cliente("Steve", "Rua A num 1", "75985085085", "71071071021");
        Cliente cliente2 = new Cliente("Camilla", "Rua Peixes num 14", "75995095095", "16016016021");
        Cliente cliente3 = new Cliente("Dayana", "Rua Pôr do Sol num 8", "75925025025", "32032032021");

        DAO.getCliente().create( cliente1);
        DAO.getCliente().create( cliente2);
        DAO.getCliente().create( cliente3);

        List<Cliente> lista = DAO.getCliente().findMany();

        assertEquals( lista.get(0).getNome(), "Steve");
        assertEquals( lista.get(0).getEndereco(), "Rua A num 1");
        assertEquals( lista.get(0).getTelefone(), "75985085085");
        assertEquals( lista.get(0).getCpf(), "71071071021");

        assertEquals( lista.get(1).getNome(), "Camilla");
        assertEquals( lista.get(1).getEndereco(), "Rua Peixes num 14");
        assertEquals( lista.get(1).getTelefone(), "75995095095");
        assertEquals( lista.get(1).getCpf(), "16016016021");

        assertEquals( lista.get(2).getNome(), "Dayana");
        assertEquals( lista.get(2).getEndereco(), "Rua Pôr do Sol num 8");
        assertEquals( lista.get(2).getTelefone(), "75925025025");
        assertEquals( lista.get(2).getCpf(), "32032032021");
    }

    @Test
    void findById(){
        /*
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
        */
    }

    @Test
    void update(){
        /*
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

         */

    }
    @Test
    void delete(){
        /*
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
        c3.setNome("Tony");
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
        */
    }

    @Test
    void checkId(){
        /*
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

         */
    }

    @Test
    void deleteMany(){
        /*
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
        c3.setNome("Tony");
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

         */
    }

}
