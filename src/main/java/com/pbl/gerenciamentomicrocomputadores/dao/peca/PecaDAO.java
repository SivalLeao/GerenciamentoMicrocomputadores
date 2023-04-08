package com.pbl.gerenciamentomicrocomputadores.dao.peca;

import com.pbl.gerenciamentomicrocomputadores.dao.CRUD;
import com.pbl.gerenciamentomicrocomputadores.model.Peca;

import java.util.List;
import java.util.Map;

public interface PecaDAO extends CRUD<Peca> {

    public void removeQuantity (String nome, int quantidade);

    public void addQuantity (String nome, int quatidade);

    public Map<String, Peca> findFullMap ();

    public Peca findByName (String nome);

    boolean checkByName (String nome);

    public boolean checkQuantity (String nome, int quantidade);

    public List<Peca> quantityAlert ();

    public Map<String, Integer> refundQuantity (Map<String, Integer> mapItens);

    public void removePeca (String nomePeca);

}
