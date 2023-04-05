package com.pbl.gerenciamentomicrocomputadores.model;

public class Peca {

    private String nome;

    private int quantidade;

    private double valor;

    private double custo;

    public Peca (String nome, int quantidade, double valor, double custo) {

        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.custo = custo;
    }

    public void setNome (String nome) {
        this.nome = nome;
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

    public void setCusto(double custo) { this.custo = custo; }

    public double getCusto() { return custo; }

}
