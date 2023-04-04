package com.pbl.gerenciamentomicrocomputadores.dao.cliente;

import com.pbl.gerenciamentomicrocomputadores.dao.CRUD;
import com.pbl.gerenciamentomicrocomputadores.model.Cliente;

public interface ClienteDAO extends CRUD<Cliente> {

    public Cliente findByCpf (String cpf);

    public boolean checkByCpf (String cpf);

}
