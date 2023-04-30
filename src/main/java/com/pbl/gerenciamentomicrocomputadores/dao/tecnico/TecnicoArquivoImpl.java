package com.pbl.gerenciamentomicrocomputadores.dao.tecnico;

import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;
import com.pbl.gerenciamentomicrocomputadores.utils.ArmazenamentoDeDados;

import java.util.ArrayList;
import java.util.List;

/** É responsável por armazenar e todos os técnicos do sistema em um arquivo binário com extensão ".dat", e estruturar
 * os métodos necessários para inserir, consultar, alterar ou remover. Implementa a interface TecnicoDAO.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 3.0.
 */

public class TecnicoArquivoImpl implements TecnicoDAO {

    private List<Tecnico> lista;
    private String nomeArquivo = "tecnico.dat";
    private String nomePasta = "Tecnico";

    /** Construtor responsável por resgatar uma lista contendo os dados dos técnicos cadastrados no sistema
     * que foram previamente salvos em um arquivo binário. Caso não haja nenhum cadastro, o construtor
     * retorna uma lista vazia.*/
    public TecnicoArquivoImpl() {

        this.lista = ArmazenamentoDeDados.resgatarDados(nomeArquivo,nomePasta);

    }

    /** Método para adicionar um técnico na lista e em seguida é salvo em um arquivo binário. O ID é inserido nos
     * dados do técnico antes de adicioná-lo no arquivo. O ID do técnico inicialmente tem o valor 1111, o valor
     * 10 é somado  no atributo id para o próximo técnico.
     *
     * @param tecnico Tecnico - técnico que deve ser armazenado.*/
    @Override
    public void criar(Tecnico tecnico) {

        int id;

        if (lista.isEmpty()) {

            id = 1111;
        }
        else {

            id = lista.get(lista.size() - 1).getId() + 10;
        }

        tecnico.setId(id);

        this.lista.add(tecnico);

        ArmazenamentoDeDados.guardarDados(lista, nomeArquivo, nomePasta);

    }

    /** Método de retorno do técnico através da busca por ID.
     *
     * @param id int - número de ID do técnico.
     * @return Tecnico - técnico encontrado após a busca.*/
    @Override
    public Tecnico encontrarPorId(int id) {

        for (Tecnico tecnico: this.lista) {

            if (tecnico.getId() == id) {

                return tecnico;
            }
        }

        return null;
    }

    /** Método de retorno do técnico através da busca por CPF.
     *
     * @param cpf String - CPF do técnico.
     * @return Tecnico - técnico encontrado após a busca.*/
    @Override
    public Tecnico encontrarPorCpf(String cpf) {

        for (Tecnico tecnico: this.lista) {

            if ( tecnico.getCpf().equals(cpf)) {

                return tecnico;
            }
        }

        return null;
    }

    /** Método para atualizar os dados de um técnico já presente no armazenamento. O ID do técnico é
     * utilizado para encontrar seu equivalente na lista. Quando achado, o objeto antigo do técnico
     * é substituido pelo novo.
     *
     * @param tecnico Tecnico - técnico que deve ser atualizado.
     */
    @Override
    public void atualizar(Tecnico tecnico) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getId() == tecnico.getId()) {

                this.lista.set(i, tecnico);

                ArmazenamentoDeDados.guardarDados(lista, nomeArquivo, nomePasta);

                return;
            }
        }
    }

    /** Método para remover um técnico através da busca por ID.
     *
     * @param id int - ID do técnico que deve ser removido.*/
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

    /** Método de retorno de toda a lista de técnicos armazenada no sistema.
     *
     * @return List - lista de técnicos do sistema.*/
    @Override
    public List<Tecnico> encontrarTodos() {

        List<Tecnico> listTecnico = new ArrayList<Tecnico>();

        for ( Tecnico tecnico : this.lista) {

            listTecnico.add(tecnico);
        }

        return listTecnico;
    }

    /** Método para checar se um técnico está armazenado no sistema. Checagem feita através do número de ID.
     *
     * @param id int - número de ID do técnico.
     * @return boolean - resultado da busca pelo técnico. Se foi achado ou não.*/
    @Override
    public boolean checarPorId(int id) {

        for (Tecnico tecnico: this.lista) {

            if (tecnico.getId() == id) {

                return true;
            }
        }

        return false;
    }

    /** Método para checar se um técnico está armazenado no sistema. Checagem feita através do CPF.
     *
     * @param cpf String - CPF do técnico.
     * @return boolean - resultado da busca pelo técnico. Se foi achado ou não.*/
    @Override
    public boolean checarPorCpf(String cpf) {

        for (Tecnico tecnico: this.lista) {

            if ( tecnico.getCpf().equals(cpf)) {

                return true;
            }
        }

        return false;
    }

    /** Método para esvaziar todo o armazenamento de técnicos. A função clear é usada para
     * limpar a lista.*/
    @Override
    public void removerTodos() {

        this.lista.clear();
        ArmazenamentoDeDados.guardarDados(lista, nomeArquivo, nomePasta);
    }

    /** Método responsável por mudar o endereço do arquivo quando for necessário realizar testes de unidade.*/
    @Override
    public void diretorioTest() {

        this.nomeArquivo = "tecnicotest.dat";
        this.nomePasta = "Teste Tecnico";
    }

    /** Método responsável por mudar para o endereço padrão do arquivo após a conclusão dos testes de unidade.*/
    @Override
    public void diretorioPadrao() {

        this.nomeArquivo = "tecnico.dat";
        this.nomePasta = "Tecnico";
    }
}
