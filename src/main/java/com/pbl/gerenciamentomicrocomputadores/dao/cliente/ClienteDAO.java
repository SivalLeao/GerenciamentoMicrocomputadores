package com.pbl.gerenciamentomicrocomputadores.dao.cliente;

import com.pbl.gerenciamentomicrocomputadores.dao.CRUD;
import com.pbl.gerenciamentomicrocomputadores.model.Cliente;

/** Interface para as implementações de armazenamento dos objetos da classe Cliente. É uma
 * extensão da interface CRUD.
 */

public interface ClienteDAO extends CRUD<Cliente> {

    /** Método de retorno de um cliente através do CPF.
     *
     * @param cpf String - CPF do cliente.
     * @return Cliente - cliente encontrado após a busca por CPF.*/

    public Cliente findByCpf (String cpf);

    /** Método para checar se um cliente está armazenado no sistema, através da busca por CPF.
     *
     * @param cpf String - CPF do cliente.
     * @return boolean - resultado da busca pelo cliente. Se foi achado ou não.*/

    public boolean checkByCpf (String cpf);

}
