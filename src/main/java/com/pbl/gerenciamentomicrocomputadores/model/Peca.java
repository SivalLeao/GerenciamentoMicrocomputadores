package com.pbl.gerenciamentomicrocomputadores.model;

public class Peca {
    private String nome;
    private int quantidade;
    private double valor;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() { return quantidade; }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() { return valor; }

}
