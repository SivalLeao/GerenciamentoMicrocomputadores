package com.pbl.gerenciamentomicrocomputadores.dao.peca;

import com.pbl.gerenciamentomicrocomputadores.model.Peca;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class PecaImpl implements PecaDAO {

    private Map<String, Peca> map;

    public PecaImpl () {

        this.map = new HashMap<String, Peca>();

        Peca peca0 = new Peca("ram", 10, 20, 20);
        this.map.put(peca0.getNome(), peca0);

        Peca peca1 = new Peca("placa mae", 10, 100, 100);
        this.map.put(peca1.getNome(), peca1);

        Peca peca2 = new Peca("fonte", 10, 30, 30);
        this.map.put(peca2.getNome(), peca2);

        Peca peca3 = new Peca("placa de video", 10, 100, 100);
        this.map.put(peca3.getNome(), peca3);

        Peca peca4 = new Peca("hd", 10, 30, 30);
        this.map.put(peca4.getNome(), peca4);

        Peca peca5 = new Peca("ssd", 10, 30, 30);
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

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        novaQuantidade = this.map.get(nomeFormatado).getQuantidade() - quantidade;

        this.map.get(nomeFormatado).setQuantidade(novaQuantidade);
    }

    @Override
    public void addQuantity (String nome, int quantidade){

        int novaQuantidade;

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        novaQuantidade = this.map.get(nomeFormatado).getQuantidade() + quantidade;

        this.map.get(nomeFormatado).setQuantidade(novaQuantidade);
    }

    @Override
    public Map<String, Peca> findFullMap () {

        Map<String, Peca> mapPeca = new HashMap<String, Peca>();

        for (String nomePeca: this.map.keySet()) {

            mapPeca.put(nomePeca, this.map.get(nomePeca));
        }

        return mapPeca;
    }

    @Override
    public Peca findByName (String nome) {

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        return this.map.get(nomeFormatado);
    }

    @Override
    public boolean checkByName(String nome){

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        for (String key: this.map.keySet()) {

            if (nomeFormatado.equals(key)){
                return true;
            }
        }

        return false;
    }

    public boolean checkQuantity (String nome, int quantidade) {

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        if (this.map.get(nomeFormatado).getQuantidade() >= quantidade) {

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

    public Map<String, Integer> refundQuantity (Map<String, Integer> mapItens) {

        int novaQuantidade;

        Map<String, Integer> mapItensNaoDevolvidos = new HashMap<String, Integer>();

        for (String nomePeca: mapItens.keySet()) {

            if (this.map.containsKey(nomePeca)) {

                novaQuantidade = this.map.get(nomePeca).getQuantidade() + mapItens.get(nomePeca);

                this.map.get(nomePeca).setQuantidade(novaQuantidade);
            }
            else {

                mapItensNaoDevolvidos.put(nomePeca, mapItens.get(nomePeca));
            }
        }

        return mapItensNaoDevolvidos;
    }

    public void removePeca (String nomePeca) {

        String nomeFormatado = Normalizer.normalize(nomePeca, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        this.map.remove(nomeFormatado);
    }

    @Override
    public void deleteMany () {

        this.map.clear();

        Peca peca0 = new Peca("ram", 0, 20, 20);
        this.map.put(peca0.getNome(), peca0);

        Peca peca1 = new Peca("placa mae", 0, 100, 100);
        this.map.put(peca1.getNome(), peca1);

        Peca peca2 = new Peca("fonte", 0, 30, 30);
        this.map.put(peca2.getNome(), peca2);

        Peca peca3 = new Peca("placa de video", 0, 100, 100);
        this.map.put(peca3.getNome(), peca3);

        Peca peca4 = new Peca("hd", 0, 30, 30);
        this.map.put(peca4.getNome(), peca4);

        Peca peca5 = new Peca("ssd", 0, 30, 30);
        this.map.put(peca5.getNome(), peca5);
    }

    @Override
    public List<Peca> findMany () { return null;};

    @Override
    public Peca findById (int id) { return null;}

    @Override
    public void delete(int id) { }

    @Override
    public boolean checkById(int id) { return false; }

}
