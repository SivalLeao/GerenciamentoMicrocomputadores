package com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico;

import com.pbl.gerenciamentomicrocomputadores.dao.CRUD;
import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;

import java.util.List;
import java.util.Map;

/** Interface para as implementações de armazenamento dos objetos da ordem de serviço. É uma
 * extensão da interface CRUD.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */
public interface OrdemDeServicoDAO extends CRUD<OrdemDeServico> {
    /** Método de retorno de uma lista de ordem de serviço. Busca feita por ID.
     *
     * @param idTecnico int - id do técnico
     * @return List<OrdemDeServico> - lista de ordens de serviço atribuídas a um técnico específico
     */
    public List<OrdemDeServico> findByIdTecnico (int idTecnico);

    /** Método de retorno de uma lista de ordem de serviço em aberto. Busca feita por ID.
     *
     * @param idTecnico int - id do técnico
     * @return lista de ordens de serviço em aberto atribuídas a um técnico específico
     */
    public List<OrdemDeServico> openListTecnico (int idTecnico);

    /** Método para atualizar os dados dos status de uma ordem de serviço. Busca feita por ID.
     *
     * @param idOrdem int - id da ordem de serviço
     * @param status String - status do serviço
     * @return Map<String, Integer> - mapa contendo itens de peça
     */
    public Map<String, Integer> updateStatus (int idOrdem, String status);

    /** Método para checar os status de uma ordem de serviço.
     *
     * @param idTecnico int - id do técnico
     * @return retorna verdadeiro se a ordem de serviço estiver em andamento
     */
    public boolean checkStatus (int idTecnico);



}
