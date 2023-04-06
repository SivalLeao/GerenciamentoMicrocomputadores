package com.pbl.gerenciamentomicrocomputadores.dao.peca;

import com.pbl.gerenciamentomicrocomputadores.model.Peca;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class PecaImpl implements PecaDAO {

    private Map<String, Peca> map;

    public PecaImpl () {

        this.map = new HashMap<String, Peca>();

        Peca peca0 = new Peca("RAM", 10, 20, 20);
        this.map.put(peca0.getNome(), peca0);

        Peca peca1 = new Peca("placa mãe", 10, 100, 100);
        this.map.put(peca1.getNome(), peca1);

        Peca peca2 = new Peca("fonte", 10, 30, 30);
        this.map.put(peca2.getNome(), peca2);

        Peca peca3 = new Peca("placa de vídeo", 10, 100, 100);
        this.map.put(peca3.getNome(), peca3);

        Peca peca4 = new Peca("HD", 10, 30, 30);
        this.map.put(peca4.getNome(), peca4);

        Peca peca5 = new Peca("SSD", 10, 30, 30);
        this.map.put(peca5.getNome(), peca5);
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

    public List<Peca> quantityAlert () {

        List<Peca> listPeca = new ArrayList<Peca>();

        for (String nomePeca: this.map.keySet()) {

            if (this.map.get(nomePeca).getQuantidade() <= 5) {

                listPeca.add(this.map.get(nomePeca));
            }
        }

        return listPeca;
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
