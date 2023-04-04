package com.pbl.gerenciamentomicrocomputadores.model;

public class RelatorioServico {

    private String satisfacaoCliente;

    private long tempoDeEspera;

    public void setSatisfacaoCliente (String satisfacaoCliente) { this.satisfacaoCliente = satisfacaoCliente; }

    public String getSatisfacaoCliente () { return satisfacaoCliente; }

    public void setTempoDeEspera (long tempoDeEspera) { this.tempoDeEspera = tempoDeEspera; }

    public long getTempoDeEspera () { return tempoDeEspera; }

}
