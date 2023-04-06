package com.pbl.gerenciamentomicrocomputadores.dao.peca;

import com.pbl.gerenciamentomicrocomputadores.model.Peca;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class PecaImpl implements PecaDao {

    private Map<String, Peca> map;

    public PecaImpl () {

        this.map = new HashMap<String, Peca>();
    }

    @Override
    public void create (Peca peca) {

        map.put(peca.getNome(), peca);
    }

    @Override
    public void update (Peca peca) {

        map.put(peca.getNome(), peca);
    }

    @Override
    public void removeQuantity (String nome, int quantidade) {

        int novaQuantidade;

        novaQuantidade = this.map.get(nome).getQuantidade() - quantidade;
        this.map.get(nome).setQuantidade(novaQuantidade);
    }

    @Override
    public void addQuantity (String nome, int quantidade){

        int novaQuantidade;

        novaQuantidade = this.map.get(nome).getQuantidade() + quantidade;
        this.map.get(nome).setQuantidade(novaQuantidade);
    }

    @Override
    public List<Peca> findMany() {

        List<Peca> listPeca = new ArrayList<Peca>();

        for (String nome: this.map.keySet()) {

            listPeca.add(this.map.get(nome));
        }

        return listPeca;
    }

    @Override
    public boolean checkByName(String nome){

        for (String key: this.map.keySet()) {

            if (nome.equals(key)){
                return true;
            }
        }

        return false;
    }

    public boolean checkQuatity (String nome, int quantidade) {

        if (this.map.get(nome).getQuantidade() >= quantidade) {

            return true;
        }
        else {

            return false;
        }
    }

    @Override
    public Peca findById (int id) {
        return null;
    }

    @Override
    public void delete(int id) { }

    @Override
    public boolean checkById(int id) { return false; }

    @Override
    public void deleteMany() { }

}
