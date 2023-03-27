package com.pbl.gerenciamentomicrocomputadores;

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

    public static void main(String[] args) {
        //launch();

        // Área de testes

        TecnicoDAO tecnicoDAO = DAO.getTecnico();

        String nome;
        String endereco;
        String telefone;
        String cpf;

        Scanner scan = new Scanner(System.in);
        int opcao;



        System.out.printf("MENU\n" +
                "[1] Cadastrar Tecnico     [] " +
                "[2] Ver lista de Tecnicos\n" +
                "[3] Deletar Tecnico\n[4] Atualizar Tecnico\n[5] Encontrar Tecnico por " +
                "ID\n[6] Encontrar Tecnico por CPF\n[7] Deletar lista de Tecnicos\n[8] Encerrar\n\n> ");



        opcao = scan.nextInt();
        scan.nextLine();

        while ( opcao != 8) {

            if (opcao == 1) {

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

                tecnicoDAO.create(tecnico);
            }
            else if (opcao == 2) {

                List<Tecnico> listTecnicos = new ArrayList<Tecnico>();

                listTecnicos = tecnicoDAO.findMany();

                for ( Tecnico tecnico: listTecnicos) {

                    System.out.println("\nID: " + tecnico.getId());
                    System.out.println("Nome: " + tecnico.getNome());
                    System.out.println("Endereço: " + tecnico.getEndereco());
                    System.out.println("Número de telefone: " + tecnico.getTelefone());
                    System.out.println("CPF: " + tecnico.getCpf());
                }
            }
            else if (opcao == 3) {

                int id;
                System.out.print("\nID do Tecnico: ");
                id = scan.nextInt();
                scan.nextLine();

                boolean idExiste = tecnicoDAO.checkId(id);

                if (idExiste == true) {

                    tecnicoDAO.delete(id);
                    System.out.print("\nO Tecnico foi apagado.\n");
                } else {

                    System.out.printf("\nID não encontrado.\n");
                }

            }
            else if (opcao == 4) {

                Tecnico tecnico = new Tecnico();

                int id;
                System.out.print("\nID do Tecnico: ");
                id = scan.nextInt();
                scan.nextLine();

                if (tecnicoDAO.checkId(id)) {

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

                    tecnico.setId(id);

                    tecnicoDAO.update(tecnico);
                }
                else {

                    System.out.printf("\nID não encontrado.\n");
                }

            }
            else if (opcao == 5) {

                Tecnico tecnico = new Tecnico();

                int id;
                System.out.print("\nID do Tecnico: ");
                id = scan.nextInt();
                scan.nextLine();

                tecnico = tecnicoDAO.findById(id);

                if (tecnico == null) {

                    System.out.println("\nID não encontrado.");
                }
                else {

                    System.out.println("Nome: " + tecnico.getNome());
                    System.out.println("Endereço: " + tecnico.getEndereco());
                    System.out.println("Número de telefone: " + tecnico.getTelefone());
                    System.out.println("CPF: " + tecnico.getCpf());
                }

            }
            else if (opcao == 6) {

                Tecnico tecnico = new Tecnico();

                System.out.print("\nCPF: ");
                cpf = scan.nextLine();
                while (tecnico.validarCpf(cpf) == false) {

                    System.out.print("Dado inválido! Escreva um CPF com 11 números.\n\nCPF: ");
                    cpf = scan.nextLine();
                }

                boolean cpfExiste = tecnicoDAO.checkCpf(cpf);

                if (cpfExiste == true) {

                    System.out.printf("\nO tecnico existe.\n");
                } else {

                    System.out.printf("\nCpf não existe.\n");
                }
            }
            else if (opcao == 7) {

                tecnicoDAO.deleteMany();
                System.out.println("\nLista apagada.");
            }

            System.out.printf("\nMENU\n[1] Cadastrar Tecnico\n[2] Ver lista de Tecnicos\n" +
                    "[3] Deletar Tecnico\n[4] Atualizar Tecnico\n[5] Encontrar Tecnico por " +
                    "ID\n[6] Encontrar Tecnico por CPF\n[7] Deletar lista de Tecnicos\n[8] Encerrar\n\n> ");
            opcao = scan.nextInt();
            scan.nextLine();
        }
    }
}