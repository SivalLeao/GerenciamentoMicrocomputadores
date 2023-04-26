package com.pbl.gerenciamentomicrocomputadores.dao.tecnico;

import java.util.ArrayList;
import java.util.List;

import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;

/** É responsável por armazenar todos os técnicos do sistema, e estruturar os métodos
 * necessários para inserir, consultar, alterar ou remover. Implementa a interface TecnicoDAO.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */

public class TecnicoImpl implements TecnicoDAO {

    private List<Tecnico> lista;
    private int id;

    /** Construtor que inicializa a lista de armazenamento de técnicos e o número de ID. O ID do
     * técnico possui o 1 como número fixo na casa da unidade, modificando apenas os valores nas
     * outras casas.*/

    public TecnicoImpl() {

        this.lista = new ArrayList<Tecnico>();
        this.id = 1111;
    }

    /** Método para adicionar um técnico na lista. O ID é inserido nos dados do técnico antes de
     * adicioná-lo na lista. O valor 10 é somado no atributo id para o próximo técnico.
     *
     * @param tecnico Tecnico - técnico que deve ser armazenado.*/

    @Override
    public void criar(Tecnico tecnico) {

        tecnico.setId(this.id);
        this.id += 10;
        lista.add(tecnico);
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
     * @param tecnico Tecnico - técnico que deve ser atualizado.*/

    @Override
    public void atualizar(Tecnico tecnico) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getId() == tecnico.getId()) {

                this.lista.set(i, tecnico);
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
     * limpar a lista. A contagem de ID é resetada para o valor inicial.*/

    @Override
    public void removerTodos() {

        this.lista.clear();
        this.id = 1111;
    }
    @Override
    public void diretorioTest() {}
    @Override
    public void diretorioPadrao() {}

}
