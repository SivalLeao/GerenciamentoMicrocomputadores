package com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico;

import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** É responsável por armazenar todas as ordem de serviço do sistema, e estruturar os métodos
 * necessários para inserir, consultar, alterar ou remover. Implementa a interface OrdemDeServico.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */
public class OrdemDeServicoImpl implements OrdemDeServicoDAO {

    private List<OrdemDeServico> lista;

    private int id;

    /** Construtor que inicializa a lista de armazenamento da ordem de serviço e o número de ID. O ID da
     * ordem possui o 3 como número fixo na casa da unidade, modificando apenas os valores nas
     * outras casas.
     * */
    public OrdemDeServicoImpl () {

        this.lista = new ArrayList<OrdemDeServico>();
        this.id = 1113;
    }

    /** Método para adicionar uma ordem de serviço na lista. O ID é inserido nos dados da ordem antes de
     *  adicioná-lo na lista. O valor 10 é somado no atributo id para a próxima ordem de serviço.
     *
     * @param ordemDeServico OrdemDeServico - ordem de serviço que deve ser armazenado.
     */
    @Override
    public void create (OrdemDeServico ordemDeServico) {

        ordemDeServico.setIdOrdem(this.id);
        ordemDeServico.setStatusAtual("Em espera");
        this.id += 10;
        this.lista.add(ordemDeServico);
    }

    /** Método de retorno da ordem de serviço através da busca por ID.
     *
     * @param idOrdem int - número de ID.
     * @return OrdemDeServico - ordem encontrada após a busca
     */
    @Override
    public OrdemDeServico findById (int idOrdem) {

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdOrdem() == idOrdem) {

                return ordemDeServico;
            }
        }

        return null;
    }

    /** Método para atualizar os dados de uma ordem de serviço já presente no armazenamento. O ID da ordem é
     * utilizado para encontrar seu equivalente na lista. Quando achado, o objeto antigo da ordem de serviço
     * é substituido pelo novo.
     *
     * @param ordemDeServico OrdemDeServico - ordem de serviço que deve ser atualizado.
     */
    @Override
    public void update (OrdemDeServico ordemDeServico) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getIdOrdem() == ordemDeServico.getIdOrdem()) {

                this.lista.set(i, ordemDeServico);
                return;
            }
        }
    }

    /** Método para atualizar os dados dos status de uma ordem de serviço já presente no armazenamento. O ID da ordem é
     *  utilizado para encontrar seu equivalente na lista. Quando achado, o valor do status é atualizado.
     *
     * @param idOrdem int - número de ID.
     * @param status String - status do serviço
     * @return Map<String, Integer> - mapa contendo itens de peça
     */
    public Map<String, Integer> updateStatus (int idOrdem, String status) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getIdOrdem() == idOrdem) {

                this.lista.get(i).setStatusAtual(status);

                if (status.equals("Finalizado")) {

                    this.lista.get(i).getData().setDataFim(LocalDateTime.now());
                    return null;
                }
                else if (status.equals("Cancelado")) {

                    OrdemDeServico ordemDeServico = findById(idOrdem);

                    return ordemDeServico.getDescricaoServico().getMapItens();
                }
            }
        }

        return null;
    }

    /** Método para remover uma ordem de serviço através da busca por ID.
     *
     * @param idOrdem int - ID do objeto que deve ser removido.
     */
    @Override
    public void delete (int idOrdem) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getIdOrdem() == idOrdem) {

                this.lista.remove(i);
                return;
            }
        }
    }

    /** Método de retorno de toda a lista de ordem de serviço armazenada no sistema.
     *
     * @return List<OrdemDeServico> - lista de ordem de serviço do sistema
     */
    @Override
    public List<OrdemDeServico> findMany () {

        List<OrdemDeServico> listOrdemDeServico = new ArrayList<OrdemDeServico>();

        for (OrdemDeServico ordemDeServico: this.lista) {

            listOrdemDeServico.add(ordemDeServico);
        }

        return listOrdemDeServico;
    }

    /** Método que retorna uma lista de ordens de serviço atribuídas a um técnico específico
     * armazenadas no sistema.
     *
     * @param idTecnico int - id do técnico
     * @return List<OrdemDeServico> - lista de ordens de serviço atribuídas a um técnico específico
     */
    @Override
    public List<OrdemDeServico> findByIdTecnico (int idTecnico) {

        List<OrdemDeServico> listOrdemDeServico = new ArrayList<OrdemDeServico>();

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdTecnico() == idTecnico) {

                listOrdemDeServico.add(ordemDeServico);
            }
        }

        return listOrdemDeServico;
    }

    /** Método que retorna uma lista de ordens de serviço em aberto atribuídas a um técnico específico
     * armazenadas no sistema.
     *
     * @param idTecnico int - id do técnico
     * @return lista de ordens de serviço em aberto atribuídas a um técnico específico
     */
    @Override
    public List<OrdemDeServico> openListTecnico (int idTecnico) {

        List<OrdemDeServico> listOrdemDeServico = new ArrayList<OrdemDeServico>();

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdTecnico() == idTecnico) {

                if (ordemDeServico.getStatusAtual().equals("Em andamento") || ordemDeServico.getStatusAtual().equals("Em espera")) {

                    listOrdemDeServico.add(ordemDeServico);
                }
            }
        }

        return listOrdemDeServico;
    }

    /** Método para checar se uma ordem de serviço está armazenado no sistema. Checagem feita através do número de ID.
     *
     * @param idOrdem int - número do ID da ordem de serviço.
     * @return boolean - resultado da busca pelo cliente. Se foi achado ou não.
     */
    @Override
    public boolean checkById (int idOrdem) {

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdOrdem() == idOrdem) {

                return true;
            }
        }

        return false;
    }

    /** Método para checar os status de uma ordem de serviço está armazenado no sistema.
     * Checagem feita através do número de ID do técnico.
     *
     * @param idTecnico int - id do técnico
     * @return boolean - retorna verdadeiro se a ordem de serviço estiver em andamento
     */
    @Override
    public boolean checkStatus (int idTecnico) {

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdTecnico() == idTecnico && ordemDeServico.getStatusAtual().equals("Em andamento")) {

                return true;
            }
        }

        return false;
    }

    /** Método para esvaziar o armazenamento de ordem de serviço. A função clear é usada para
     * limpar a lista. A contagem de ID é resetada para o valor inicial
     */
    @Override
    public void deleteMany () {

        this.lista.clear();
        this.id = 1113;
    }
}
