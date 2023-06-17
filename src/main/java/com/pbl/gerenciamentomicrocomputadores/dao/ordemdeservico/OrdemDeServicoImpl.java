package com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico;

import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** É responsável por armazenar todas as ordem de serviço do sistema, e estruturar os métodos
 * necessários para inserir, consultar, alterar ou remover. Implementa a interface OrdemDeServicoDAO.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */

public class OrdemDeServicoImpl implements OrdemDeServicoDAO {

    private List<OrdemDeServico> lista;
    private int id;

    /** Construtor que inicializa a lista de armazenamento de ordens de serviço e o número de ID. O ID da
     * ordem possui o 3 como número fixo na casa da unidade, modificando apenas os valores nas
     * outras casas.*/

    public OrdemDeServicoImpl () {

        this.lista = new ArrayList<OrdemDeServico>();
        this.id = 1113;
    }

    /** Método para adicionar uma ordem de serviço na lista. O ID é inserido nos dados da ordem antes de
     *  adicioná-la na lista. O valor 10 é somado no atributo id para a próxima ordem de serviço.
     *
     * @param ordemDeServico OrdemDeServico - ordem de serviço que deve ser armazenada.*/

    @Override
    public void criar(OrdemDeServico ordemDeServico) {

        ordemDeServico.setIdOrdem(this.id);
        ordemDeServico.setStatusAtual("Em espera");
        this.id += 10;
        this.lista.add(ordemDeServico);
    }

    /** Método de retorno da ordem de serviço através da busca por ID.
     *
     * @param idOrdem int - número de ID da ordem de Serviço
     * @return OrdemDeServico - ordem de serviço encontrada após a busca*/

    @Override
    public OrdemDeServico encontrarPorId(int idOrdem) {

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdOrdem() == idOrdem) {

                return ordemDeServico;
            }
        }

        return null;
    }

    /** Método para atualizar os dados de uma ordem de serviço já presente no armazenamento. O ID da ordem é
     * utilizado para encontrar seu equivalente na lista. Quando achado, o objeto antigo da ordem de serviço
     * é substituído pelo novo.
     *
     * @param ordemDeServico OrdemDeServico - ordem de serviço que deve ser atualizada.*/

    @Override
    public void atualizar(OrdemDeServico ordemDeServico) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getIdOrdem() == ordemDeServico.getIdOrdem()) {

                this.lista.set(i, ordemDeServico);
                return;
            }
        }
    }

    /** Método para atualizar os status de uma ordem de serviço. O ID da ordem é utilizado para encontrar
     * seu equivalente na lista. Quando achado, o valor do status é atualizado. Se o novo status for de
     * "Finalizado", a data final de realização do serviço é inserida no objeto da ordem. Caso o novo
     * status seja de "Cancelado", a lista de peças utilizadas é retornada para ser devolvida ao estoque.
     *
     * @param idOrdem int - número de ID da ordem de serviço.
     * @param status String - novo status da ordem de serviço
     * @return Map - estrutura HashMap contendo as peças utilizadas no serviço que devem
     * ser devolvidas ao estoque, caso a ordem de serviço seja cancelada*/

    public Map<String, Integer> atualizarStatus(int idOrdem, String status) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getIdOrdem() == idOrdem) {

                this.lista.get(i).setStatusAtual(status);

                if (status.equals("Finalizado")) {

                    this.lista.get(i).getData().setDataFim(LocalDateTime.now());
                    return null;
                }
                else if (status.equals("Cancelado")) {

                    OrdemDeServico ordemDeServico = encontrarPorId(idOrdem);

                    return ordemDeServico.getDescricaoServico().getMapItens();
                }
            }
        }

        return null;
    }

    /** Método para remover uma ordem de serviço através da busca por ID.
     *
     * @param idOrdem int - ID da ordem de serviço que deve ser removida.*/

    @Override
    public void remover(int idOrdem) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getIdOrdem() == idOrdem) {

                this.lista.remove(i);
                return;
            }
        }
    }

    /** Método de retorno de todas as ordens de serviço armazenadas no sistema.
     *
     * @return List - lista de ordens de serviço do sistema*/

    @Override
    public List<OrdemDeServico> encontrarTodos() {

        List<OrdemDeServico> listOrdemDeServico = new ArrayList<OrdemDeServico>();

        for (OrdemDeServico ordemDeServico: this.lista) {

            listOrdemDeServico.add(ordemDeServico);
        }

        return listOrdemDeServico;
    }

    /** Método de retorno da lista de ordens de serviços que devem ser realizadas por determinado técnico.
     * A busca pelas ordens é feita através do ID do técnico.
     *
     * @param idTecnico int - ID do técnico
     * @return List - lista de ordens de serviço atribuídas a um técnico específico*/

    @Override
    public List<OrdemDeServico> encontrarPorIdTecnico(int idTecnico) {

        List<OrdemDeServico> listOrdemDeServico = new ArrayList<OrdemDeServico>();

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdTecnico() == idTecnico) {

                listOrdemDeServico.add(ordemDeServico);
            }
        }

        return listOrdemDeServico;
    }

    /** Método de retorno da lista de ordens de serviço pedidas por determinado cliente.
     * A busca pelas ordens é feita através do ID do cliente.
     *
     * @param idCliente int - ID do cliente
     * @return List - lista de ordens de serviço pedidas por um cliente específico*/

    @Override
    public List<OrdemDeServico> encontrarPorIdCliente(int idCliente) {

        List<OrdemDeServico> listOrdemDeServico = new ArrayList<OrdemDeServico>();

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdCliente() == idCliente) {

                listOrdemDeServico.add(ordemDeServico);
            }
        }

        return listOrdemDeServico;
    }

    /** Método de retorno da lista de ordens de serviço em aberto de um determinado técnico. A busca pelas
     * ordens é feita através do ID do técnico. As ordens em aberto são indicadas se estiverem com o
     * status "Em andamento" ou "Em espera".
     *
     * @param idTecnico int - ID do técnico
     * @return List - lista de ordens de serviço em aberto atribuídas a um técnico específico*/

    @Override
    public List<OrdemDeServico> listaEmAbertoTecnico(int idTecnico) {

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

    /** Método para checar se uma ordem de serviço está armazenado no sistema. Checagem feita através do número de ID
     * da ordem.
     *
     * @param idOrdem int - número de ID da ordem de serviço.
     * @return boolean - resultado da busca pela ordem de serviço. Se foi achada ou não.*/

    @Override
    public boolean checarPorId(int idOrdem) {

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdOrdem() == idOrdem) {

                return true;
            }
        }

        return false;
    }

    /** Método para checar se existe alguma ordem de serviço, atribuída a um técnico específico,
     * com status "Em andamento". Checagem feita através do ID do técnico.
     *
     * @param idTecnico int - ID do técnico
     * @return boolean - resultado da busca por uma ordem de serviço, com status "Em andamento",
     * atribuída a um técnico. Se foi achada ou não*/

    @Override
    public boolean checarStatusEmAndamento(int idTecnico) {

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdTecnico() == idTecnico && ordemDeServico.getStatusAtual().equals("Em andamento")) {

                return true;
            }
        }

        return false;
    }

    /** Método para coletar a próxima ordem de serviço da lista para o técnico realizar. Caso não tenha
     * serviço disponível, ele retorna null.
     *
     * @return OrdemDeServico - retorna a ordem de serviço que deve ser realizada pelo técnico*/

    public OrdemDeServico coletarOrdem() {

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getStatusAtual().equals("Em espera")) {

                return ordemDeServico;
            }
        }

        return null;

    }

    /** Método para retornar a ordem de serviço que determinado técnico está trabalhando. Busca feita
     * pelo ID do técnico
     *
     * @return OrdemDeServico - retorna a ordem de serviço que o técnico está realizando*/

    public OrdemDeServico conseguirOrdemTecnico(int idTecnico) {

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdTecnico() == idTecnico &&
                    ordemDeServico.getStatusAtual().equals("Em andamento")) {

                return ordemDeServico;
            }
        }

        return null;
    }

    /** Método para retornar os IDs das ordens que estão utilizando determinada peça e a sua
     * quantidade. A busca é feita pelo nome da peça. A ordens checadas são as que ainda estão
     * sendo feitas.
     *
     * @param nomePeca String - nome da peça.
     * @return Map - Mapa com os IDs das ordens que estão utilizando determinada peça e a sua
     * quantidade.*/

    public Map<Integer, Integer> ordensUtilizandoPeca(String nomePeca) {

        Map<Integer, Integer> mapOrdemQtd = new HashMap<>();

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getStatusAtual().equals("Em espera") ||
                    ordemDeServico.getStatusAtual().equals("Em andamento")) {

                Map<String, Integer> mapItens = ordemDeServico.getDescricaoServico().getMapItens();

                if (mapItens.containsKey(nomePeca)) {

                    mapOrdemQtd.put(ordemDeServico.getIdOrdem(), mapItens.get(nomePeca));
                }

            }
        }

        return mapOrdemQtd;
    }

    /** Método para retornar as ordens com status em andamento ou em espera.
     *
     * @return List - lista com as ordens em aberto.*/

    public List<OrdemDeServico> ordensEmAberto() {

        List<OrdemDeServico> listaEmAberto = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getStatusAtual().equals("Em espera") ||
                    lista.get(i).getStatusAtual().equals("Em andamento")) {

                listaEmAberto.add(lista.get(i));
            }
        }

        return listaEmAberto;
    }

    /** Método para retornar as ordens finalizadas.
     *
     * @return List - lista com as ordens finalizadas.*/

    public List<OrdemDeServico> ordensFinalizadas() {

        List<OrdemDeServico> listaFinalizados = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).getStatusAtual().equals("Finalizado")) {

                listaFinalizados.add(lista.get(i));
            }
        }

        return listaFinalizados;
    }

    /** Método para esvaziar o armazenamento de ordens de serviço. A função clear é usada para
     * limpar a lista. A contagem de ID é resetada para o valor inicial*/

    @Override
    public void removerTodos() {

        this.lista.clear();
        this.id = 1113;
    }

    @Override
    public void diretorioTest() {}

    @Override
    public void diretorioPadrao() {}

}
