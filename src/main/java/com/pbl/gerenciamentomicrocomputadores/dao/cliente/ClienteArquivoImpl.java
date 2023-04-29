package com.pbl.gerenciamentomicrocomputadores.dao.cliente;

import com.pbl.gerenciamentomicrocomputadores.model.Cliente;
import com.pbl.gerenciamentomicrocomputadores.utils.ArmazenamentoDeDados;

import java.util.ArrayList;
import java.util.List;

public class ClienteArquivoImpl implements ClienteDAO {

    private List<Cliente> lista;
    private String nomeArquivo = "cliente.dat";
    private String nomePasta = "Cliente";

    public ClienteArquivoImpl() {

        this.lista = ArmazenamentoDeDados.resgatarDados(nomeArquivo,nomePasta);

    }

    @Override
    public void criar(Cliente cliente) {

        int id;

        if (lista.isEmpty()) {

            id = 1112;
        }
        else {

            id = lista.get(lista.size() - 1).getId() + 10;
        }

        cliente.setId(id);

        this.lista.add(cliente);

        ArmazenamentoDeDados.guardarDados(lista, nomeArquivo, nomePasta);
    }

    @Override
    public Cliente encontrarPorId(int id) {

        for (Cliente cliente: this.lista) {

            if (cliente.getId() == id) {

                return cliente;
            }
        }

        return null;
    }

    @Override
    public Cliente encontrarPorCpf(String cpf) {

        for (Cliente cliente: this.lista) {

            if ( cliente.getCpf().equals(cpf)) {

                return cliente;
            }
        }

        return null;
    }

    @Override
    public void atualizar(Cliente cliente) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getId() == cliente.getId()) {

                this.lista.set(i, cliente);

                ArmazenamentoDeDados.guardarDados(lista, nomeArquivo, nomePasta);

                return;
            }
        }
    }

    @Override
    public void remover(int id) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getId() == id) {

                this.lista.remove(i);

                ArmazenamentoDeDados.guardarDados(lista, nomeArquivo, nomePasta);

                return;
            }
        }
    }

    @Override
    public List<Cliente> encontrarTodos() {

        List<Cliente> listCliente = new ArrayList<Cliente>();

        for ( Cliente cliente : this.lista) {

            listCliente.add(cliente);
        }

        return listCliente;
    }

    @Override
    public boolean checarPorId(int id) {

        for (Cliente cliente: this.lista) {

            if (cliente.getId() == id) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checarPorCpf(String cpf) {

        for (Cliente cliente: this.lista) {

            if ( cliente.getCpf().equals(cpf)) {

                return true;
            }
        }

        return false;
    }

    @Override
    public void removerTodos() {

        this.lista.clear();
        ArmazenamentoDeDados.guardarDados(lista, nomeArquivo, nomePasta);
    }

    @Override
    public void diretorioTest() {

        this.nomeArquivo = "clienteTest.dat";
        this.nomePasta = "Test Cliente";
    }

    @Override
    public void diretorioPadrao() {

        this.nomeArquivo = "cliente.dat";
        this.nomePasta = "Cliente";
    }

}
