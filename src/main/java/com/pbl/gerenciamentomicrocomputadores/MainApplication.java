package com.pbl.gerenciamentomicrocomputadores;

import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteDAO;
import com.pbl.gerenciamentomicrocomputadores.model.Cliente;
import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.dao.tecnico.TecnicoDAO;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static Tecnico cadastrar_tec(){
        TecnicoDAO tecnicoDAO = DAO.getTecnico();

        String nome;
        String endereco;
        String telefone;
        String cpf;
        Scanner scan = new Scanner(System.in);
        Tecnico tecnico = new Tecnico();

        System.out.print("\nNome: ");
        nome = scan.nextLine();
        while (tecnico.validarNome(nome) == false) {

            System.out.print("Dado inválido! Escreva um nome de pelo menos 3 caracteres, contendo apenas letras.\n\nNome: ");
            nome = scan.nextLine();
        }
        tecnico.setNome(nome);

        System.out.print("\nEndereço: ");
        endereco = scan.nextLine();
        while (tecnico.validarEndereco(endereco) == false) {

            System.out.print("Dado inválido! Escreva um endereço de pelo menos 3 caracteres.\n\nEndereço: ");
            endereco = scan.nextLine();
        }
        tecnico.setEndereco(endereco);

        System.out.print("\nNúmero de telefone: ");
        telefone = scan.nextLine();
        while (tecnico.validarTelefone(telefone) == false) {

            System.out.print("Dado inválido! Escreva um número de telefone com 11 números.\n\nTelefone: ");
            telefone = scan.nextLine();
        }

        tecnico.setTelefone(telefone);

        System.out.print("\nCPF: ");
        cpf = scan.nextLine();
        while (tecnico.validarCpf(cpf) == false) {

            System.out.print("Dado inválido! Escreva um CPF com 11 números.\n\nCPF: ");
            cpf = scan.nextLine();
        }
        tecnico.setCpf(cpf);

        return tecnico;
    }

    public static Cliente cadastrar_cli(){
        ClienteDAO clienteDAO = DAO.getCliente();

        String nome;
        String endereco;
        String telefone;
        String cpf;
        Scanner scan = new Scanner(System.in);
        Cliente cliente= new Cliente();

        System.out.print("\nNome: ");
        nome = scan.nextLine();
        while (cliente.validarNome(nome) == false) {

            System.out.print("Dado inválido! Escreva um nome de pelo menos 3 caracteres, contendo apenas letras.\n\nNome: ");
            nome = scan.nextLine();
        }
        cliente.setNome(nome);

        System.out.print("\nEndereço: ");
        endereco = scan.nextLine();
        while (cliente.validarEndereco(endereco) == false) {

            System.out.print("Dado inválido! Escreva um endereço de pelo menos 3 caracteres.\n\nEndereço: ");
            endereco = scan.nextLine();
        }
        cliente.setEndereco(endereco);

        System.out.print("\nNúmero de telefone: ");
        telefone = scan.nextLine();
        while (cliente.validarTelefone(telefone) == false) {

            System.out.print("Dado inválido! Escreva um número de telefone com 11 números.\n\nTelefone: ");
            telefone = scan.nextLine();
        }
        cliente.setTelefone(telefone);

        System.out.print("\nCPF: ");
        cpf = scan.nextLine();
        while (cliente.validarCpf(cpf) == false) {

            System.out.print("Dado inválido! Escreva um CPF com 11 números.\n\nCPF: ");
            cpf = scan.nextLine();
        }
        cliente.setCpf(cpf);

        return cliente;
    }


    public static void main(String[] args) {
        //launch();

        // Área de testes

        TecnicoDAO tecnicoDAO = DAO.getTecnico();
        ClienteDAO clienteDAO = DAO.getCliente();


        String nome;
        String endereco;
        String telefone;
        String cpf;
        int id;
        boolean cpfExiste;

        Scanner scan = new Scanner(System.in);
        int opcao;
        boolean on = true;

        do{

            System.out.printf("MENU\n" +
                    "[1] - Cadastrar Tecnico          [2] - Cadastrar Cliente\n" +
                    "[3] - Ver lista de Tecnicos      [4] - Ver lista de Clientes\n" +
                    "[5] - Deletar Tecnico            [6] - Deletar Cliente\n" +
                    "[7] - Atualizar Tecnico          [8] - Atualizar Cliente\n" +
                    "[9] - Encontrar Tecnico por Id   [10] - Encontrar Cliente por Id\n" +
                    "[11] - Encontrar Tecnico por CPF [12] - Encontrar Cliente por CPF\n"+
                    "[13] Deletar lista de Tecnicos   [14] - Deletar lista de Tecnicos" +
                    "\n[0] - Encerrar\n\n> ");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao){
                case 0:
                    on = false;
                    break;
                case 1:
                    tecnicoDAO.create(cadastrar_tec());
                    break;
                case 2:
                    clienteDAO.create(cadastrar_cli());
                    break;
                case 3:
                    List<Tecnico> listTecnicos = new ArrayList<Tecnico>();

                    listTecnicos = tecnicoDAO.findMany();

                    for (Tecnico tecnico : listTecnicos) {

                        System.out.println("\nID: " + tecnico.getId());
                        System.out.println("Nome: " + tecnico.getNome());
                        System.out.println("Endereço: " + tecnico.getEndereco());
                        System.out.println("Número de telefone: " + tecnico.getTelefone());
                        System.out.println("CPF: " + tecnico.getCpf());
                    }
                    break;
                case 4:
                    List<Cliente> listClientes = new ArrayList<Cliente>();

                    listClientes = clienteDAO.findMany();

                    for (Cliente cliente : listClientes) {

                        System.out.println("\nID: " + cliente.getId());
                        System.out.println("Nome: " + cliente.getNome());
                        System.out.println("Endereço: " + cliente.getEndereco());
                        System.out.println("Número de telefone: " + cliente.getTelefone());
                        System.out.println("CPF: " + cliente.getCpf());
                    }
                    break;
                case 5:

                    System.out.print("\nID do Tecnico: ");
                    id = scan.nextInt();
                    scan.nextLine();

                    boolean idExiste_tec = tecnicoDAO.checkId(id);

                    if (idExiste_tec == true) {

                        tecnicoDAO.delete(id);
                        System.out.print("\nO Tecnico foi apagado.\n");
                    } else {

                        System.out.printf("\nID não encontrado.\n");
                    }
                    break;
                case 6:

                    System.out.print("\nID do Tecnico: ");
                    id = scan.nextInt();
                    scan.nextLine();

                    boolean idExiste_cli = clienteDAO.checkId(id);

                    if (idExiste_cli == true) {

                        clienteDAO.delete(id);
                        System.out.print("\nO Tecnico foi apagado.\n");
                    } else {

                        System.out.printf("\nID não encontrado.\n");
                    }
                    break;
                case 7:
                    Tecnico tecnico = new Tecnico();

                    System.out.print("\nID do Tecnico: ");
                    id = scan.nextInt();
                    scan.nextLine();

                    if (tecnicoDAO.checkId(id)) {

                        tecnico = cadastrar_tec();
                        tecnico.setId(id);

                        tecnicoDAO.update(tecnico);
                    } else {

                        System.out.printf("\nID não encontrado.\n");
                    }
                    break;
                case 8:
                    Cliente cliente = new Cliente();

                    System.out.print("\nID do Cliente: ");
                    id = scan.nextInt();
                    scan.nextLine();

                    if (clienteDAO.checkId(id)) {

                        cliente = cadastrar_cli();
                        cliente.setId(id);

                        clienteDAO.update(cliente);
                    } else {

                        System.out.printf("\nID não encontrado.\n");
                    }
                    break;
                case 9:
                    System.out.print("\nID do Tecnico: ");
                    id = scan.nextInt();
                    scan.nextLine();

                    tecnico = tecnicoDAO.findById(id);

                    if (tecnico == null) {

                        System.out.println("\nID não encontrado.");
                    } else {

                        System.out.println("Nome: " + tecnico.getNome());
                        System.out.println("Endereço: " + tecnico.getEndereco());
                        System.out.println("Número de telefone: " + tecnico.getTelefone());
                        System.out.println("CPF: " + tecnico.getCpf());
                    }
                    break;
                case 10:
                    System.out.print("\nID do Cliente: ");
                    id = scan.nextInt();
                    scan.nextLine();

                    cliente = clienteDAO.findById(id);

                    if (cliente == null) {

                        System.out.println("\nID não encontrado.");
                    } else {

                        System.out.println("Nome: " + cliente.getNome());
                        System.out.println("Endereço: " + cliente.getEndereco());
                        System.out.println("Número de telefone: " + cliente.getTelefone());
                        System.out.println("CPF: " + cliente.getCpf());
                    }
                    break;
                case 11:
                    Tecnico tecnico_cpf = new Tecnico();

                    System.out.print("\nCPF: ");
                    cpf = scan.nextLine();
                    while (tecnico_cpf.validarCpf(cpf) == false) {

                        System.out.print("Dado inválido! Escreva um CPF com 11 números.\n\nCPF: ");
                        cpf = scan.nextLine();
                    }

                    cpfExiste = tecnicoDAO.checkCpf(cpf);

                    if (cpfExiste == true) {

                        System.out.printf("\nO tecnico existe.\n");
                    } else {

                        System.out.printf("\nCpf não existe.\n");
                    }
                    break;
                case 12:
                    Tecnico cliente_cpf = new Tecnico();

                    System.out.print("\nCPF: ");
                    cpf = scan.nextLine();
                    while (cliente_cpf.validarCpf(cpf) == false) {

                        System.out.print("Dado inválido! Escreva um CPF com 11 números.\n\nCPF: ");
                        cpf = scan.nextLine();
                    }

                    cpfExiste = clienteDAO.checkCpf(cpf);

                    if (cpfExiste == true) {

                        System.out.printf("\nO tecnico existe.\n");
                    } else {

                        System.out.printf("\nCpf não existe.\n");
                    }
                    break;
                case 13:
                    tecnicoDAO.deleteMany();
                    System.out.println("\nLista apagada.");
                    break;
                case 14:
                    clienteDAO.deleteMany();
                    System.out.println("\nLista apagada.");
            }
        } while(on);

    }
}