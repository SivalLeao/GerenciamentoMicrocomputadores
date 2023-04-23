package com.pbl.gerenciamentomicrocomputadores.dao;

import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteArquivoImpl;
import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteImpl;
import com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico.OrdemDeServicoArquivoImpl;
import com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico.OrdemDeServicoDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico.OrdemDeServicoImpl;
import com.pbl.gerenciamentomicrocomputadores.dao.tecnico.TecnicoArquivoImpl;
import com.pbl.gerenciamentomicrocomputadores.dao.tecnico.TecnicoDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.tecnico.TecnicoImpl;
import com.pbl.gerenciamentomicrocomputadores.dao.peca.PecaDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.peca.PecaImpl;

/** Classe usada para acessar as extensões do DAO no projeto. Os dados armazenados são divididos em quatro
 *  entidades, que são: cliente, técnico, ordem de serviço e peça. Os atributos e métodos estão instanciados
 *  como static, pois um objeto não precisa ser instanciado para acessar essas funções.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */

public class DAO {

    private static ClienteDAO clienteDAO;
    private static TecnicoDAO tecnicoDAO;
    private static OrdemDeServicoDAO ordemDeServicoDAO;
    private static PecaDAO pecaDAO;

    /** Método de retorno do atributo clienteDAO, que é responsável por armazenar os dados
     * de uma lista de clientes e acessar os seus métodos de manuseio. O objeto é inicializado
     * como uma instância da classe ClienteImpl, caso não tenha sido anteriormente.
     *
     * @return ClienteDAO - objeto da classe ClienteImpl.*/

    public static ClienteDAO getCliente() {

        if (clienteDAO == null) {
            clienteDAO = new ClienteArquivoImpl();
        }

        return clienteDAO;
    }

    /** Método de retorno do atributo tecnicoDAO, que é responsável por armazenar os dados
     * de uma lista de técnicos e acessar os seus métodos de manuseio. O objeto é inicializado
     * como uma instância da classe TecnicoImpl, caso não tenha sido anteriormente.
     *
     * @return TecnicoDAO - objeto da classe TecnicoImpl.*/

    public static TecnicoDAO getTecnico() {

        if (tecnicoDAO == null) {
            tecnicoDAO = new TecnicoArquivoImpl();
        }

        return tecnicoDAO;
    }

    /** Método de retorno do atributo ordemDeServicoDAO, que é responsável por armazenar os dados
     * de uma lista de ordens de serviço e acessar os seus métodos de manuseio. O objeto é inicializado
     * como uma instância da classe OrdemDeServicoImpl, caso não tenha sido anteriormente.
     *
     * @return OrdemDeServicoDAO - objeto da classe OrdemDeServicoImpl.*/

    public static OrdemDeServicoDAO getOrdemDeServico () {

        if (ordemDeServicoDAO == null) {
            ordemDeServicoDAO = new OrdemDeServicoArquivoImpl();
        }

        return ordemDeServicoDAO;
    }

    /** Método de retorno do atributo pecaDAO, que é responsável por armazenar os dados
     * de uma estrutura de armazenamento de peças e acessar os seus métodos de manuseio. O objeto
     * é inicializado como uma instância da classe PecaImpl, caso não tenha sido anteriormente.
     *
     * @return PecaDAO - objeto da classe PecaImpl.*/

    public static PecaDAO getPeca () {

        if (pecaDAO == null) {
            pecaDAO = new PecaImpl();
        }

        return pecaDAO;
    }

}
