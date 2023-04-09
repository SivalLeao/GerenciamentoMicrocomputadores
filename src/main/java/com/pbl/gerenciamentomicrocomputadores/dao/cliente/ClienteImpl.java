package com.pbl.gerenciamentomicrocomputadores.dao.cliente;

import java.util.ArrayList;
import java.util.List;

import com.pbl.gerenciamentomicrocomputadores.model.Cliente;

/** É responsável por armazenar todos os clientes do sistema, e estruturar os métodos
 * necessários para inserir, consultar, alterar ou remover. Implementa a interface ClienteDAO.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */

public class ClienteImpl implements ClienteDAO {

    private List<Cliente> lista;
    private int id;

    /** Construtor que inicializa a lista de armazenamento de clientes e o número de ID. O ID do
     * cliente possui o 2 como número fixo na casa da unidade, modificando apenas os valores nas
     * outras casas.*/

    public ClienteImpl () {

        this.lista = new ArrayList<Cliente>();
        this.id = 1112;
    }

    /** Método para adicionar um cliente na lista. O ID é inserido nos dados do cliente antes de
     * adicioná-lo na lista. O valor 10 é somado no atributo id para o próximo cliente.
     *
     * @param cliente Cliente - cliente que deve ser armazenado.*/

    @Override
    public void create (Cliente cliente) {

        cliente.setId(this.id);
        this.id += 10;
        lista.add(cliente);
    }

    /** Método de retorno do cliente através da busca por ID.
     *
     * @param id int - número de ID do cliente.
     * @return Cliente - cliente encontrado após a busca.*/

    @Override
    public Cliente findById (int id) {

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
    public Cliente findByCpf (String cpf) {

        for (Cliente cliente: this.lista) {

            if ( cliente.getCpf().equals(cpf)) {

                return cliente;
            }
        }

        return null;
    }

    /** Método para atualizar os dados de um cliente já presente no armazenamento. O ID do cliente é
     * utilizado para encontrar seu equivalente na lista. Quando achado, o objeto antigo do cliente
     * é substituido pelo novo.
     *
     * @param cliente Cliente - cliente que deve ser atualizado.*/

    @Override
    public void update (Cliente cliente) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getId() == cliente.getId()) {

                this.lista.set(i, cliente);
                return;
            }
        }
    }

    /** Método para remover um cliente através da busca por ID.
     *
     * @param id int - ID do cliente que deve ser removido.*/

    @Override
    public void delete (int id) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getId() == id) {

                this.lista.remove(i);
                return;
            }
        }
    }

    /** Método de retorno de toda a lista de clientes armazenada no sistema.
     *
     * @return List<Cliente> - lista de clientes do sistema.*/

    @Override
    public List<Cliente> findMany () {

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
    public boolean checkById (int id) {

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
    public boolean checkByCpf (String cpf) {

        for (Cliente cliente: this.lista) {

            if ( cliente.getCpf().equals(cpf)) {

                return true;
            }
        }

        return false;
    }

    /** Método para esvaziar todo o armazenamento de clientes. A função clear é usada para
     * limpar a lista. A contagem de ID é resetada para o valor inicial.*/

    @Override
    public void deleteMany () {

        this.lista.clear();
        this.id = 1112;
    }

}
