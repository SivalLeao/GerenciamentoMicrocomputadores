package com.pbl.gerenciamentomicrocomputadores;

import com.pbl.gerenciamentomicrocomputadores.model.Cliente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteDAO;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();

        // Área de testes

        ClienteDAO clienteDAO = DAO.getCliente();

        String nome;
        String endereco;
        String telefone;
        String cpf;

        Scanner scan = new Scanner(System.in);
        int opcao;

        System.out.printf("MENU\n[1] Cadastrar Cliente\n[2] Ver lista de Clientes\n" +
                "[3] Deletar Cliente\n[4] Atualizar Cliente\n[5] Encontrar Cliente por " +
                "ID\n[6] Encontrar Cliente por CPF\n[7] Deletar lista de Clientes\n[8] Encerrar\n\n> ");
        opcao = scan.nextInt();
        scan.nextLine();

        while ( opcao != 8) {

            if (opcao == 1) {

                Cliente cliente = new Cliente();

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

                clienteDAO.create(cliente);
            }
            else if (opcao == 2) {

                List<Cliente> listClientes = new ArrayList<Cliente>();

                listClientes = clienteDAO.findMany();

                for ( Cliente cliente: listClientes) {

                    System.out.println("\nID: " + cliente.getId());
                    System.out.println("Nome: " + cliente.getNome());
                    System.out.println("Endereço: " + cliente.getEndereco());
                    System.out.println("Número de telefone: " + cliente.getTelefone());
                    System.out.println("CPF: " + cliente.getCpf());
                }
            }
            else if (opcao == 3) {

                int id;
                System.out.print("\nID do Cliente: ");
                id = scan.nextInt();
                scan.nextLine();

                boolean idExiste = clienteDAO.checkId(id);

                if (idExiste == true) {

                    clienteDAO.delete(id);
                    System.out.print("\nO Cliente foi apagado.\n");
                } else {

                    System.out.printf("\nID não encontrado.\n");
                }

            }
            else if (opcao == 4) {

                Cliente cliente = new Cliente();

                int id;
                System.out.print("\nID do Cliente: ");
                id = scan.nextInt();
                scan.nextLine();

                if (clienteDAO.checkId(id)) {

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

                    cliente.setId(id);

                    clienteDAO.update(cliente);
                }
                else {

                    System.out.printf("\nID não encontrado.\n");
                }

            }
            else if (opcao == 5) {

                Cliente cliente = new Cliente();

                int id;
                System.out.print("\nID do Cliente: ");
                id = scan.nextInt();
                scan.nextLine();

                cliente = clienteDAO.findById(id);

                if (cliente == null) {

                    System.out.println("\nID não encontrado.");
                }
                else {

                    System.out.println("Nome: " + cliente.getNome());
                    System.out.println("Endereço: " + cliente.getEndereco());
                    System.out.println("Número de telefone: " + cliente.getTelefone());
                    System.out.println("CPF: " + cliente.getCpf());
                }

            }
            else if (opcao == 6) {

                Cliente cliente = new Cliente();

                System.out.print("\nCPF: ");
                cpf = scan.nextLine();
                while (cliente.validarCpf(cpf) == false) {

                    System.out.print("Dado inválido! Escreva um CPF com 11 números.\n\nCPF: ");
                    cpf = scan.nextLine();
                }

                boolean cpfExiste = clienteDAO.checkCpf(cpf);

                if (cpfExiste == true) {

                    System.out.printf("\nO cliente existe.\n");
                } else {

                    System.out.printf("\nCpf não existe.\n");
                }
            }
            else if (opcao == 7) {

                clienteDAO.deleteMany();
                System.out.println("\nLista apagada.");
            }

            System.out.printf("\nMENU\n[1] Cadastrar Cliente\n[2] Ver lista de Clientes\n" +
                    "[3] Deletar Cliente\n[4] Atualizar Cliente\n[5] Encontrar Cliente por " +
                    "ID\n[6] Encontrar Cliente por CPF\n[7] Deletar lista de Clientes\n[8] Encerrar\n\n> ");
            opcao = scan.nextInt();
            scan.nextLine();
        }
    }
}