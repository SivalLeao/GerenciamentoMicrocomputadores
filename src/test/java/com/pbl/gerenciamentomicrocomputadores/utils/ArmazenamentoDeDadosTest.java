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

            Cliente c1 = new Cliente("Steve", "Rua Capricornio num 1", "66666666666", "11111111111");
            Cliente c2 = new Cliente("Dayana", "Rua Aquario num 2", "77777777777", "22222222222");
            Cliente c3 = new Cliente("Camilla", "Rua Libra num 3", "88888888888", "33333333333");
            Cliente c4 = new Cliente("Bernardo", "Rua Virgem num 4", "99999999999", "44444444444");
            Cliente c5 = new Cliente("Morgana", "Rua Leao num 5", "00000000000", "55555555555");

            DAO.getCliente().criar(c1);
            DAO.getCliente().criar(c2);
            DAO.getCliente().criar(c3);
            DAO.getCliente().criar(c4);
            DAO.getCliente().criar(c5);
        }
        @Test
        void guardarDados() {
            List<Cliente> listCliente =  DAO.getCliente().encontrarTodos();
            List<Tecnico> listTecnico =  DAO.getTecnico().encontrarTodos();

            ArmazenamentoDeDados.guardarDados(listCliente, "clienteArmazenamentoTest.dat", "Test Armazenamento");
            ArmazenamentoDeDados.guardarDados(listTecnico, "tecnicoArmazenamentoTest.dat", "Test Armazenamento");

            File diretorio = new File("dados salvos");
            File pasta1 = new File(diretorio +"/"+ "Test Armazenamento");
            File pasta2 = new File(diretorio +"/"+ "Test Armazenamento");


            File arquivoCliente = new File(pasta1,"clienteArmazenamentoTest.dat");
            File arquivoTecnico = new File(pasta2,"tecnicoArmazenamentoTest.dat");

            assertTrue(arquivoCliente.exists());
            assertTrue(arquivoTecnico.exists());

        }
        @Test
        void resgatarDados() {
            List<Cliente> listCliente;
            List<Tecnico> listTecnico;

            listCliente = ArmazenamentoDeDados.resgatarDados("clienteArmazenamentoTest.dat","Test Armazenamento");
            listTecnico = ArmazenamentoDeDados.resgatarDados("tecnicoArmazenamentoTest.dat", "Test Armazenamento");

            assertEquals("Steve", listCliente.get(0).getNome());
            assertEquals("Penny", listTecnico.get(2).getNome());
        }
}

