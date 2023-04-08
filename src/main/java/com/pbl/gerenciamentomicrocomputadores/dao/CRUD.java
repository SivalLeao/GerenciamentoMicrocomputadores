package com.pbl.gerenciamentomicrocomputadores.dao;

import java.util.List;

/** Interface com os principais métodos que poderão ser implementados nas outras extensões do DAO.
 *
 * @param <T> especificação do tipo de objeto que terá foco central na classe que implementar a interface CRUD.
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */

public interface CRUD<T>  {

    /** Método de inserção do objeto na estrutura de armazenamento da implementação.
     *
     * @param obj objeto que deve ser inserido.*/

    public void create (T obj);

    /** Método de retorno do objeto através da busca por ID.
     *
     * @param id int - número de ID.
     * @return T - objeto encontrado após a busca através do ID.*/

    public T findById (int id);

    /** Método de atualização dos dados de um objeto na estrutura de armazenamento.
     *
     * @param T obj objeto que deve ser atualizado.*/

    public void update (T obj);

    /** Método para remover determinado objeto da estrutura de armazenamento.
     *
     * @param id int - ID do objeto que deve ser removido.*/

    public void delete (int id);

    /** Método de retorno da lista completa de dados armazenados.
     *
     * @return T - lista de objetos armazenados.*/

    public List<T> findMany ();

    /** Método para checar se determinado objeto existe no armazenamento através do ID.
     *
     * @param id int - ID do objeto.
     * @return boolean - resultado da checagem se o objeto está contido no armazenamento ou não.*/

    public boolean checkById (int id);

    /** Método para esvaziar todo o armazenamento.
     */

    public void deleteMany ();

}
