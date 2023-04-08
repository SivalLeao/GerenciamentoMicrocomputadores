package com.pbl.gerenciamentomicrocomputadores.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

public class OrdemDeServico {

    private int idOrdem;

    private int idCliente;

    private int idTecnico;

    private Data data;

    private DescricaoServico descricaoServico;

    private String statusAtual;

    private String formaPagamento;

    private String satisfacaoCliente;

    private double valorTotalFatura;

    public OrdemDeServico () {

        this.data = new Data( LocalDateTime.now());
        this.descricaoServico = new DescricaoServico();
    }

    public void setIdOrdem (int id) { this.idOrdem = id; }

    public int getIdOrdem () { return this.idOrdem; }

    public void setIdCliente (int id) { this.idCliente = id; }

    public int getIdCliente () { return this.idCliente; }

    public void setIdTecnico (int id) { this.idTecnico = id; }

    public int getIdTecnico () { return this.idTecnico; }

    public void setData (Data data) { this.data = data; }

    public Data getData () { return this.data; }

    public void setDescricaoServico (DescricaoServico descricaoServico) { this.descricaoServico = descricaoServico; }

    public DescricaoServico getDescricaoServico () { return this.descricaoServico; }

    public void setStatusAtual (String statusAtual) { this.statusAtual = statusAtual; }

    public String getStatusAtual () { return this.statusAtual; }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getFormaPagamento() {
        return this.formaPagamento;
    }

    public void setSatisfacaoCliente (String satisfacaoCliente) {
        this.satisfacaoCliente = satisfacaoCliente;
    }

    public String getSatisfacaoCliente () { return this.satisfacaoCliente; }

    public void setValorTotalFatura(double valorTotalFatura) {
        this.valorTotalFatura = valorTotalFatura;
    }

    public double getValorTotalFatura() {
        return this.valorTotalFatura;
    }

    public long calcularTempoDeServico () {

        return ChronoUnit.SECONDS.between(this.data.getDataInicio(), this.data.getDataFim());
    }

    public double calcularValorServico (Map<String, Peca> mapPeca) {

        Map<String, Integer> mapPecaServico = this.descricaoServico.getMapItens();

        double valorPecas = 0.0;

        if (this.descricaoServico.getTipoDeServico().equals("Montagem/Instalação")) {

            for (String nomePeca : mapPecaServico.keySet()) {

                valorPecas += mapPeca.get(nomePeca).getValor() * mapPecaServico.get(nomePeca);
            }

        }
        else if (this.descricaoServico.getTipoDeServico().equals("Sistema operacional")) {

            valorPecas = 50;
        }
        else if (this.descricaoServico.getTipoDeServico().equals("Programas")) {

            valorPecas = 10;
        }
        else if (this.descricaoServico.getTipoDeServico().equals("Limpeza")) {

            valorPecas = 70;
        }

        return valorPecas;
    }

}
