package com.pbl.gerenciamentomicrocomputadores.dao.tecnico;

import com.pbl.gerenciamentomicrocomputadores.dao.CRUD;
import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;

public interface TecnicoDAO extends CRUD<Tecnico>{

    public Tecnico findByCpf (String cpf);

    public boolean checkByCpf (String cpf);

}
