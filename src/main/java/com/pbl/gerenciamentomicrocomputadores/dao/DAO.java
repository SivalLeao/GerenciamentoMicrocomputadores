package com.pbl.gerenciamentomicrocomputadores.dao;

import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteListImpl;
import com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico.OrdemDeServicoDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico.OrdemDeServicoListImpl;
import com.pbl.gerenciamentomicrocomputadores.dao.tecnico.TecnicoDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.tecnico.TecnicoListImpl;

public class DAO {

    private static ClienteDAO clienteDAO;

    private static TecnicoDAO tecnicoDAO;

    private static OrdemDeServicoDAO ordemDeServicoDAO;

    public static ClienteDAO getCliente() {
        if (clienteDAO == null) {
            clienteDAO = new ClienteListImpl();
        }
        return clienteDAO;
    }

    public static TecnicoDAO getTecnico() {
        if (tecnicoDAO == null) {
            tecnicoDAO = new TecnicoListImpl();
        }
        return tecnicoDAO;
    }

    public static OrdemDeServicoDAO getOrdemDeServico () {
        if (ordemDeServicoDAO == null) {
            ordemDeServicoDAO = new OrdemDeServicoListImpl();
        }
        return ordemDeServicoDAO;
    }

}
