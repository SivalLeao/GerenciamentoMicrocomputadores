package com.pbl.gerenciamentomicrocomputadores.model;

public class RelatorioServico {

    private int valorTotal;

    private String satisfacaoCliente;

    private long tempoDeEspera;

    public void setValorTotal (int valorTotal) { this.valorTotal = valorTotal; }

    public int getValorTotal () { return valorTotal; }

    public void setSatisfacaoCliente (String satisfacaoCliente) { this.satisfacaoCliente = satisfacaoCliente; }

    public String getSatisfacaoCliente () { return satisfacaoCliente; }

    public void setTempoDeEspera (long tempoDeEspera) { this.tempoDeEspera = tempoDeEspera; }

    public long getTempoDeEspera () { return tempoDeEspera; }

}
