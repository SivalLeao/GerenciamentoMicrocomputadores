package com.pbl.gerenciamentomicrocomputadores.dao;

import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteImpl;
import com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico.OrdemDeServicoDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico.OrdemDeServicoImpl;
import com.pbl.gerenciamentomicrocomputadores.dao.tecnico.TecnicoDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.tecnico.TecnicoImpl;
import com.pbl.gerenciamentomicrocomputadores.dao.peca.PecaDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.peca.PecaImpl;

public class DAO {

    private static ClienteDAO clienteDAO;

    private static TecnicoDAO tecnicoDAO;

    private static OrdemDeServicoDAO ordemDeServicoDAO;

    private static PecaDAO pecaDAO;

    public static ClienteDAO getCliente() {

        if (clienteDAO == null) {
            clienteDAO = new ClienteImpl();
        }

        return clienteDAO;
    }

    public static TecnicoDAO getTecnico() {

        if (tecnicoDAO == null) {
            tecnicoDAO = new TecnicoImpl();
        }

        return tecnicoDAO;
    }

    public static OrdemDeServicoDAO getOrdemDeServico () {

        if (ordemDeServicoDAO == null) {
            ordemDeServicoDAO = new OrdemDeServicoImpl();
        }

        return ordemDeServicoDAO;
    }

    public static PecaDAO getPeca () {

        if (pecaDAO == null) {
            pecaDAO = new PecaImpl();
        }

        return pecaDAO;
    }

}
