package com.pbl.gerenciamentomicrocomputadores.dao.cliente;

import com.pbl.gerenciamentomicrocomputadores.model.Cliente;
import com.pbl.gerenciamentomicrocomputadores.utils.ArmazenamentoDeDados;

import java.util.ArrayList;
import java.util.List;

/** É responsável por armazenar todos os clientes do sistema em um arquivo binário com
 * extensão ".dat", e estruturar os métodos necessários para inserir, consultar, alterar
 * ou remover. Implementa a interface ClienteDAO.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 3.0.
 */

public class ClienteArquivoImpl implements ClienteDAO {

    private List<Cliente> lista;
    private String nomeArquivo = "cliente.dat";
    private String nomePasta = "Cliente";

    /** Construtor responsável por resgatar a lista contendo os dados dos clientes cadastrados no sistema,
     * que foram previamente salvos em um arquivo binário.*/

    public ClienteArquivoImpl() {

        this.lista = ArmazenamentoDeDados.resgatarDados(nomeArquivo,nomePasta);

    }

    /** Método para adicionar um cliente na lista e em seguida salvar em um arquivo binário. O ID do último
     * cliente cadastrado é usado como base para o próximo, somando o valor 10 ao ID encontrado e inserindo
     * nos dados do cliente que deve ser adicionado. Se a lista de clientes estiver vazia, o ID inicial é 1112.
     *
     * @param cliente Cliente - cliente que deve ser armazenado.*/

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

    /** Método de retorno do cliente através da busca por ID.
     *
     * @param id int - número de ID do cliente.
     * @return Cliente - cliente encontrado após a busca.*/

    @Override
    public Cliente encontrarPorId(int id) {

        for (Cliente cliente: this.lista) {

            if (cliente.getId() == id) {

                return cliente;
            }
        }

        return null;
    }

    /** Método de retorno do cliente através da busca por CPF.
     *
     * @param cpf String - CPF do cliente.
     * @return Cliente - cliente encontrado após a busca.*/

    @Override
    public Cliente encontrarPorCpf(String cpf) {

        for (Cliente cliente: this.lista) {

            if ( cliente.getCpf().equals(cpf)) {

                return cliente;
            }
        }

        return null;
    }

    /** Método para atualizar os dados de um cliente já presente no armazenamento. O ID do cliente é
     * utilizado para encontrar seu equivalente na lista. Quando achado, o objeto antigo do cliente
     * é substituido pelo novo, e o arquivo binário é reescrito com os novos dados.
     *
     * @param cliente Cliente - cliente que deve ser atualizado.*/

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

    /** Método para remover um cliente através da busca por ID. Após a remoção, o arquivo binário de
     * armazenamento é reescrito com os novos dados
     *
     * @param id int - ID do cliente que deve ser removido.*/

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

    /** Método de retorno de toda a lista de clientes armazenada no sistema.
     *
     * @return List - lista de clientes do sistema.*/

    @Override
    public List<Cliente> encontrarTodos() {

        List<Cliente> listCliente = new ArrayList<Cliente>();

        for ( Cliente cliente : this.lista) {

            listCliente.add(cliente);
        }

        return listCliente;
    }

    /** Método para checar se um cliente está armazenado no sistema. Checagem feita através do número de ID.
     *
     * @param id int - número de ID do cliente.
     * @return boolean - resultado da busca pelo cliente. Se foi achado ou não.*/

    @Override
    public boolean checarPorId(int id) {

        for (Cliente cliente: this.lista) {

            if (cliente.getId() == id) {

                return true;
            }
        }

        return false;
    }

    /** Método para checar se um cliente está armazenado no sistema. Checagem feita através do CPF.
     *
     * @param cpf String - CPF do cliente.
     * @return boolean - resultado da busca pelo cliente. Se foi achado ou não.*/

    @Override
    public boolean checarPorCpf(String cpf) {

        for (Cliente cliente: this.lista) {

            if ( cliente.getCpf().equals(cpf)) {

                return true;
            }
        }

        return false;
    }

    /** Método para esvaziar toda a lista de clientes, usando a função clear. O arquivo binário de
     * armazenamento é reescrito com a lista vazia.*/

    @Override
    public void removerTodos() {

        this.lista.clear();
        ArmazenamentoDeDados.guardarDados(lista, nomeArquivo, nomePasta);
    }

    /** Método responsável por mudar o endereço do arquivo e da pasta de armazenamneto da lista
     *  quando for necessário realizar testes de unidade.*/

    @Override
    public void diretorioTest() {

        this.nomeArquivo = "clientetest.dat";
        this.nomePasta = "Teste Cliente";
    }

    /** Método responsável por mudar o arquivo e a pasta de armazenamento para os endereços padrões
     * após a conclusão dos testes de unidade.*/

    @Override
    public void diretorioPadrao() {

        this.nomeArquivo = "cliente.dat";
        this.nomePasta = "Cliente";
    }

}
