package com.pbl.gerenciamentomicrocomputadores.dao.peca;

import com.pbl.gerenciamentomicrocomputadores.dao.CRUD;
import com.pbl.gerenciamentomicrocomputadores.model.Peca;

public interface PecaDao extends CRUD<Peca> {

    public void removeQuantity (String nome, int quantidade);
    public void addQuantity (String nome, int quatidade);

    boolean checkByName(String nome);

    public boolean checkQuatity (String nome, int quantidade);



}
