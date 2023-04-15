package com.pbl.gerenciamentomicrocomputadores.dao.tecnico;

import com.pbl.gerenciamentomicrocomputadores.dao.CRUD;
import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;

/** Interface para as implementações de armazenamento dos objetos da classe Tecnico. É uma
 * extensão da interface CRUD.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */

public interface TecnicoDAO extends CRUD<Tecnico>{

    /** Método de retorno de um técnico. Busca feita por CPF.
     *
     * @param cpf String - CPF do tecnico.
     * @return Tecnico - técnico encontrado após a busca por CPF.*/

    public Tecnico encontrarPorCpf(String cpf);

    /** Método para checar se um técnico está armazenado no sistema. Busca feita por CPF.
     *
     * @param cpf String - CPF do técnico.
     * @return boolean - resultado da busca pelo técnico. Se foi achado ou não.*/

    public boolean checarPorCpf(String cpf);

}
