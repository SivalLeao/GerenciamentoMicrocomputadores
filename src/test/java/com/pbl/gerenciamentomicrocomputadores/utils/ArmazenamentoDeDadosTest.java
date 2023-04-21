package com.pbl.gerenciamentomicrocomputadores.utils;

import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Cliente;
import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArmazenamentoDeDadosTest {

        @BeforeEach
        void setUp() {

            Tecnico t1 = new Tecnico("Leonard", "Nova Jersei", "44444444444", "55555555555");
            Tecnico t2 = new Tecnico("Sheldon", "Galveston, Texas", "77777777777", "33333333333");
            Tecnico t3 = new Tecnico("Penny", "Nebraska", "99999999999", "11111111111");
            Tecnico t4 = new Tecnico("Amy", "Glendale", "88888888888", "22222222222");
            Tecnico t5 = new Tecnico("Bernadette", "California", "66666666666", "00000000000");

            DAO.getTecnico().criar(t1);
            DAO.getTecnico().criar(t2);
            DAO.getTecnico().criar(t3);
            DAO.getTecnico().criar(t4);
            DAO.getTecnico().criar(t5);

            Cliente c0 = new Cliente("Steve", "Rua Capricornio num 1", "66666666666", "11111111111");
            Cliente c1 = new Cliente("Dayana", "Rua Aquario num 2", "77777777777", "22222222222");
            Cliente c2 = new Cliente("Camilla", "Rua Libra num 3", "88888888888", "33333333333");
            Cliente c3 = new Cliente("Bernardo", "Rua Virgem num 4", "99999999999", "44444444444");
            Cliente c4 = new Cliente("Morgana", "Rua Leao num 5", "00000000000", "55555555555");

            DAO.getCliente().criar(c0);
            DAO.getCliente().criar(c1);
            DAO.getCliente().criar(c2);
            DAO.getCliente().criar(c3);
            DAO.getCliente().criar(c4);
        }
        @Test
        void guardarDados() throws IOException {
            List<Cliente> cliente =  DAO.getCliente().encontrarTodos();
            List<Tecnico> tecnico =  DAO.getTecnico().encontrarTodos();

            ArmazenamentoDeDados.guardarDados(cliente, "clientetest.bin");
            ArmazenamentoDeDados.guardarDados(tecnico, "tecnicotest.bin");
            File direttorio = new File("dados salvo");

            File arquivoCliente = new File(direttorio,"clientetest.bin");
            File arquivoTecnico = new File(direttorio,"tecnicotest.bin");

            assertTrue(arquivoCliente.exists());
            assertTrue(arquivoTecnico.exists());

        }
        @Test
        void resgatarDados() throws IOException, ClassNotFoundException {
            List<Cliente> clientes = new ArrayList<>();
            List<Tecnico> tecnico = new ArrayList<>();

            clientes = ArmazenamentoDeDados.resgatarDados("clientetest.bin");
            tecnico = ArmazenamentoDeDados.resgatarDados("tecnicotest.bin");

            assertEquals("Steve", clientes.get(0).getNome());
            assertEquals("Penny", tecnico.get(2).getNome());
        }
}

