package com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico;

import com.pbl.gerenciamentomicrocomputadores.dao.CRUD;
import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;

import java.util.List;
import java.util.Map;

public interface OrdemDeServicoDAO extends CRUD<OrdemDeServico> {

    public List<OrdemDeServico> findByIdTecnico (int idTecnico);

    public Map<String, Integer> updateStatus (int idOrdem, String status);

    public boolean checkStatus (int idTecnico);



}
