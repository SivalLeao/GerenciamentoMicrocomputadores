package com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico;

import com.pbl.gerenciamentomicrocomputadores.dao.CRUD;
import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;

import java.util.List;
import java.util.Map;

/** Interface para as implementações de armazenamento dos objetos da classe OrdemDeServico. É uma
 * extensão da interface CRUD.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */

public interface OrdemDeServicoDAO extends CRUD<OrdemDeServico> {

    /** Método de retorno da lista de ordens de serviços que devem ser realizadas por determinado técnico.
     * A busca pelas ordens é feita através do ID do técnico.
     *
     * @param idTecnico int - ID do técnico
     * @return List<OrdemDeServico> - lista de ordens de serviço atribuídas a um técnico específico*/

    public List<OrdemDeServico> findByIdTecnico (int idTecnico);

    /** Método de retorno da lista de ordens de serviço em aberto de um determinado técnico. A busca pelas
     * ordens é feita através do ID do técnico.
     *
     * @param idTecnico int - id do técnico
     * @return List<OrdemDeServico> - lista de ordens de serviço em aberto atribuídas a um técnico específico*/

    public List<OrdemDeServico> openListTecnico (int idTecnico);

    /** Método para atualizar os status de uma ordem de serviço. Busca feita pelo ID da ordem.
     *
     * @param idOrdem int - ID da ordem de serviço
     * @param status String - novo status da ordem de serviço
     * @return Map<String, Integer> - estrutura HashMap contendo as peças utilizadas no serviço que devem
     * ser devolvidas ao estoque, caso a ordem de serviço seja cancelada*/

    public Map<String, Integer> updateStatus (int idOrdem, String status);

    /** Método para checar se existe alguma ordem de serviço, atribuída a um técnico específico,
     * com status "Em andamento". Checagem feita através do ID do técnico.
     *
     * @param idTecnico int - ID do técnico
     * @return boolean - retorna verdadeiro se existir uma ordem de serviço do técnico com status
     * "Em andamento"*/

    public boolean checkStatus (int idTecnico);

}
