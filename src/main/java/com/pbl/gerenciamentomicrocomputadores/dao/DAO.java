package com.pbl.gerenciamentomicrocomputadores.dao;

import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteDAO;
import com.pbl.gerenciamentomicrocomputadores.dao.cliente.ClienteListImpl;

public class DAO {

    private static ClienteDAO clienteDAO;

    public static ClienteDAO getCliente() {
        if (clienteDAO == null) {
            clienteDAO = new ClienteListImpl();
        }
        return clienteDAO;
    }

}
