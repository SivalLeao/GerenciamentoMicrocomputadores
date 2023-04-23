package com.pbl.gerenciamentomicrocomputadores.dao.peca;

import com.pbl.gerenciamentomicrocomputadores.model.Peca;
import com.pbl.gerenciamentomicrocomputadores.utils.ArmazenamentoDeDados;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PecaArquivoImpl implements PecaDAO {
    private List<Peca> lista;

    public PecaArquivoImpl () {
        this.lista = new ArrayList<Peca>();

        Peca peca0 = new Peca("ram", 10, 20, 20);
        this.lista.add(peca0);

        Peca peca1 = new Peca("placa mae", 10, 100, 100);
        this.lista.add(peca1);

        Peca peca2 = new Peca("fonte", 10, 30, 30);
        this.lista.add(peca2);

        Peca peca3 = new Peca("placa de video", 10, 100, 100);
        this.lista.add(peca3);

        Peca peca4 = new Peca("hd", 10, 30, 30);
        this.lista.add(peca4);

        Peca peca5 = new Peca("ssd", 10, 30, 30);
        this.lista.add(peca5);

        this.lista = ArmazenamentoDeDados.resgatarDados("peca.dat","Peca");



    }

    @Override
    public void criar(Peca peca) {

        lista.add(peca);

        ArmazenamentoDeDados.guardarDados(lista, "peca.dat","Peca");

    }

    @Override
    public void atualizar(Peca peca) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getNome().equals(peca.getNome())) {

                this.lista.set(i, peca);

                ArmazenamentoDeDados.guardarDados(lista, "peca.dat","Peca");

                return;
            }
        }
    }


    @Override
    public void removerQuantidade(String nome, int quantidade) {

        int novaQuantidade;

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getNome().equals(nomeFormatado)) {

                novaQuantidade = this.lista.get(i).getQuantidade() - quantidade;
                this.lista.get(i).setQuantidade(novaQuantidade);

                ArmazenamentoDeDados.guardarDados(lista, "peca.dat","Peca");

                return;
            }
        }
    }

    @Override
    public void adicionarQuantidade(String nome, int quantidade){

        int novaQuantidade;

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getNome().equals(nomeFormatado)) {

                novaQuantidade = this.lista.get(i).getQuantidade() + quantidade;
                this.lista.get(i).setQuantidade(novaQuantidade);

                ArmazenamentoDeDados.guardarDados(lista, "peca.dat","Peca");

                return;
            }
        }
    }

    @Override
    public List<Peca> encontrarTodos() {

        List<Peca> listPeca = new ArrayList<Peca>();

        for (Peca peca: this.lista) {

            listPeca.add(peca);
        }

        return listPeca;
    }


    public Map<String,Peca> encontrarTodosMap() {

        Map<String,Peca> mapPeca = new HashMap<String,Peca>();

        for (Peca peca: this.lista) {

            mapPeca.put(peca.getNome(), peca);
        }

        return mapPeca;
    }

    @Override
    public Peca encontrarPorNome(String nome) {

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        for (Peca peca: this.lista) {

            if ( peca.getNome().equals(nomeFormatado)) {

                return peca;
            }
        }

        return null;
    }

    @Override
    public boolean checarPorNome(String nome){

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        for (Peca peca: this.lista) {

            if ( peca.getNome().equals(nomeFormatado)) {

                return true;
            }
        }

        return false;
    }


    public boolean checarQuantidade(String nome, int quantidade) {

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        for (Peca peca: this.lista) {

            if ( peca.getNome().equals(nomeFormatado)) {

                if ( peca.getQuantidade() >= quantidade) {

                    return true;
                }
                else {
                    return false;
                }
            }
        }

        return false;
    }


    public List<Peca> alertaDeQuantidade() {

        List<Peca> listPeca = new ArrayList<Peca>();

        for (Peca peca: this.lista) {

            if ( peca.getQuantidade() <= 5) {

                listPeca.add(peca);
            }
        }

        return listPeca;
    }


    public Map<String, Integer> devolverQuantidade(Map<String, Integer> mapItens) {

        int novaQuantidade;

        for (Peca peca: this.lista) {

            if ( mapItens.containsKey(peca.getNome())) {

                novaQuantidade = peca.getQuantidade() + mapItens.get(peca.getNome());
                mapItens.remove(peca.getNome());

                peca.setQuantidade(novaQuantidade);
            }
        }

        ArmazenamentoDeDados.guardarDados(lista, "peca.dat","Peca");

        return new HashMap<>(mapItens);
    }

    public void removerPeca(String nome) {

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getNome().equals(nomeFormatado)) {

                this.lista.remove(i);
            }
        }
        ArmazenamentoDeDados.guardarDados(lista, "peca.dat","Peca");

    }

    @Override
    public void removerTodos() {

        this.lista.clear();

        Peca peca0 = new Peca("ram", 0, 20, 20);
        this.lista.add(peca0);

        Peca peca1 = new Peca("placa mae", 0, 100, 100);
        this.lista.add(peca1);

        Peca peca2 = new Peca("fonte", 0, 30, 30);
        this.lista.add(peca2);

        Peca peca3 = new Peca("placa de video", 0, 100, 100);
        this.lista.add(peca3);

        Peca peca4 = new Peca("hd", 0, 30, 30);
        this.lista.add(peca4);

        Peca peca5 = new Peca("ssd", 0, 30, 30);
        this.lista.add(peca5);

        ArmazenamentoDeDados.guardarDados(lista, "peca.dat","Peca");

    }

    @Override
    public Peca encontrarPorId(int id) { return null;}
    @Override
    public void remover(int id) { }
    @Override
    public boolean checarPorId(int id) { return false; }
}
