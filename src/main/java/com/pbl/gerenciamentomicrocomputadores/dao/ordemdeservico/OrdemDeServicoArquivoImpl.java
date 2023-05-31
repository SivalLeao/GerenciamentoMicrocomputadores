package com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico;

import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import com.pbl.gerenciamentomicrocomputadores.utils.ArmazenamentoDeDados;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** É responsável por armazenar todas as ordens de serviço do sistema em um arquivo binário
 * com extensão ".dat", e estruturar os métodos necessários para inserir, consultar, alterar
 * ou remover. Implementa a interface OrdemDeServicoDAO.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 3.0.
 */

public class OrdemDeServicoArquivoImpl implements OrdemDeServicoDAO {

    private List<OrdemDeServico> lista;
    private String nomeArquivo = "ordemdeservico.dat";
    private String nomePasta = "Ordem De Servico";

    /** Construtor responsável por resgatar a lista contendo os dados das ordens de serviço
     * cadastradas no sistema, que foram previamente salvas em um arquivo binário.*/

    public OrdemDeServicoArquivoImpl () {

        this.lista =  ArmazenamentoDeDados.resgatarDados(nomeArquivo,nomePasta);
    }

    /** Método para adicionar uma ordem de serviço na lista e em seguida salvar em um arquivo binário.
     * O ID da última ordem de serviço cadastrada é usado como base para a próxima, somando o valor 10 ao ID
     * encontrado e inserindo nos dados da ordem de serviço que deve ser adicionada. Se a lista de ordens de
     * serviço estiver vazia, o ID inicial é 1113. O status da ordem é colocado como "Em espera".
     *
     * @param ordemDeServico OrdemDeServico - ordem de serviço que deve ser armazenada.*/

    @Override
    public void criar(OrdemDeServico ordemDeServico) {

        int id;

        if (lista.isEmpty()) {

            id = 1113;
        }
        else {

            id = lista.get(lista.size() - 1).getIdOrdem() + 10;
        }

        ordemDeServico.setIdOrdem(id);
        ordemDeServico.setStatusAtual("Em espera");
        this.lista.add(ordemDeServico);

        ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);
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

    /** Método para atualizar os dados de uma ordem de serviço já presente no armazenamento. O ID da ordem
     * é utilizado para encontrar seu equivalente na lista. Quando achado, o objeto antigo da ordem é
     * substituido pelo novo, e o arquivo binário é reescrito com os novos dados.
     *
     * @param ordemDeServico OrdemDeServico - ordem de serviço que deve ser atualizada.*/

    @Override
    public void atualizar(OrdemDeServico ordemDeServico) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getIdOrdem() == ordemDeServico.getIdOrdem()) {

                this.lista.set(i, ordemDeServico);

                ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);

                return;
            }
        }
    }

    /** Método para atualizar os status de uma ordem de serviço. O ID da ordem é utilizado para encontrar
     * seu equivalente na lista. Quando achado, o valor do status é atualizado. Se o novo status for de
     * "Finalizado", a data final de realização do serviço é inserida no objeto da ordem. Após as mudanças,
     * o arquivo binário é reescrito com os novos dados. Caso o novo status seja de "Cancelado", a lista
     * de peças utilizadas é retornada para ser devolvida ao estoque.
     *
     * @param idOrdem int - número de ID da ordem de serviço
     * @param status String - novo status da ordem de serviço
     * @return Map - estrutura HashMap contendo as peças utilizadas no serviço que devem
     * ser devolvidas ao estoque, caso a ordem de serviço seja cancelada*/

    public Map<String, Integer> atualizarStatus(int idOrdem, String status) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getIdOrdem() == idOrdem) {

                this.lista.get(i).setStatusAtual(status);

                ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);

                if (status.equals("Finalizado")) {

                    this.lista.get(i).getData().setDataFim(LocalDateTime.now());

                    ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);

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

    /** Método para remover uma ordem de serviço através da busca por ID. Após a remoção, o arquivo
     * binário de armazenamento é reescrito com os novos dados.
     *
     * @param idOrdem int - ID da ordem de servico que deve ser removida.*/

    @Override
    public void remover(int idOrdem) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getIdOrdem() == idOrdem) {

                this.lista.remove(i);

                ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);

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

    /** Método de retorno da lista de ordens de serviço que devem ser realizadas por determinado técnico.
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

    /** Método para checar se uma ordem de serviço está armazenada no sistema. Checagem feita através
     * do número de ID da ordem.
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

    /** Método para esvaziar toda a lista de ordens de serviço, usando a função clear. O arquivo binário de
     * armazenamento é reescrito com a lista vazia.*/

    @Override
    public void removerTodos() {

        this.lista.clear();

        ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);
    }

    /** Método responsável por mudar o endereço do arquivo e da pasta de armazenamneto da lista
     *  quando for necessário realizar testes de unidade.*/

    public void diretorioTest() {

        this.nomeArquivo = "ordemdeservicotest.dat";
        this.nomePasta = "Teste Ordem De Servico";
    }

    /** Método responsável por mudar o arquivo e a pasta de armazenamento para os endereços padrões
     * após a conclusão dos testes de unidade.*/

    @Override
    public void diretorioPadrao() {

        this.nomeArquivo = "ordemdeservico.dat";
        this.nomePasta = "Ordem De Servico";
    }

}
