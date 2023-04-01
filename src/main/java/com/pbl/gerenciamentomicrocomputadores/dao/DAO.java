package com.pbl.gerenciamentomicrocomputadores.dao;

import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteImpl;
import com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico.OrdemDeServicoDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico.OrdemDeServicoImpl;
import com.pbl.gerenciamentomicrocomputadores.dao.tecnico.TecnicoDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.tecnico.TecnicoImpl;

public class DAO {

    private static ClienteDAO clienteDAO;

    private static TecnicoDAO tecnicoDAO;

    private static OrdemDeServicoDAO ordemDeServicoDAO;

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

}
