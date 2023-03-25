package com.pbl.gerenciamentomicrocomputadores.dao;

import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteListImpl;
import com.pbl.gerenciamentomicrocomputadores.dao.tecnico.TecnicoDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.tecnico.TecnicoListImpl;
import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;

public class DAO {

    private static ClienteDAO clienteDAO;
    private static TecnicoDAO tecnicoDAO;

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

}
