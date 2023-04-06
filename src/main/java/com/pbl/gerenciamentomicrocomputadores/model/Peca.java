package com.pbl.gerenciamentomicrocomputadores.model;

import java.text.Normalizer;

public class Peca {

    private String nome;

    private int quantidade;

    private double valor;

    private double custo;

    public Peca (String nome, int quantidade, double valor, double custo) {

        this.nome = Normalizer.normalize(nome, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
        this.quantidade = quantidade;
        this.valor = valor;
        this.custo = custo;
    }

    public void setNome (String nome) {
        this.nome = Normalizer.normalize(nome, Normalizer.Form.NFD).toLowerCase();
    }

    public String getNome () {
        return nome;
    }

    public void setQuantidade (int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade () {
        return quantidade;
    }

    public void setValor (double valor) {
        this.valor = valor;
    }

    public double getValor () {
        return valor;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getCusto() {
        return custo;
    }

}
