package com.pbl.gerenciamentomicrocomputadores.dao.peca;

import com.pbl.gerenciamentomicrocomputadores.model.Peca;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class PecaImpl implements PecaDao {

    private Map<String, Peca> lista;

    public PecaImpl () {

        this.lista = new HashMap<String, Peca>();
    }

    @Override
    public void create (Peca peca) {

        lista.put(peca.getNome(), peca);
    }

    @Override
    public void update (Peca peca) {

        lista.put(peca.getNome(), peca);
    }

    @Override
    public void removeQuantity (String nome, int quantidade) {

        int novaQuantidade;

        novaQuantidade = this.lista.get(nome).getQuantidade() - quantidade;

        this.lista.get(nome).setQuantidade(novaQuantidade);
    }

    @Override
    public List<Peca> findMany() {

        List<Peca> listPeca = new ArrayList<Peca>();

        for (String nome: this.lista.keySet()) {

            listPeca.add(this.lista.get(nome));
        }

        return listPeca;
    }

    public boolean checkQuatity (String nome, int quantidade) {

        if (this.lista.get(nome).getQuantidade() >= quantidade) {

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
    public boolean checkId(int id) { return false; }

    @Override
    public void deleteMany() { }
}
