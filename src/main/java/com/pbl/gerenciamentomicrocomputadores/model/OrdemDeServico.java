package com.pbl.gerenciamentomicrocomputadores.model;

import java.time.temporal.ChronoUnit;

public class OrdemDeServico {

    private int idOrdem;

    private int idCliente;

    private int idTecnico;

    private Data data;

    private String statusAtual;

    private DescricaoServico descricaoServico;

    private RelatorioServico relatorioServico;

    private Fatura fatura;

    public OrdemDeServico () {

        this.data = new Data();
        this.descricaoServico = new DescricaoServico();
        this.relatorioServico = new RelatorioServico();
        this.fatura = new Fatura();
    }

    public void setIdOrdem (int id) { this.idOrdem = id; }

    public int getIdOrdem () { return this.idOrdem; }

    public void setIdCliente (int id) { this.idCliente = id; }

    public int getIdCliente () { return this.idCliente; }

    public void setIdTecnico (int id) { this.idTecnico = id; }

    public int getIdTecnico () { return this.idTecnico; }

    public void setData (Data data) { this.data = data; }

    public Data getData () { return this.data; }

    public void setStatusAtual (String statusAtual) { this.statusAtual = statusAtual; }

    public String getStatusAtual () { return this.statusAtual; }

    public void setDescricaoServico (DescricaoServico descricaoServico) { this.descricaoServico = descricaoServico; }

    public DescricaoServico getDescricaoServico () { return this.descricaoServico; }

    public void setRelatorioServico (RelatorioServico relatorioServico) { this.relatorioServico = relatorioServico; }

    public RelatorioServico getRelatorioServico () { return relatorioServico; }

    public void setFatura (Fatura fatura) { this.fatura = fatura; }

    public Fatura getFatura () { return fatura; }

    public long calcularTempoDeServico () {

        return ChronoUnit.SECONDS.between(this.data.getDataInicio(), this.data.getDataFim());
    }

}
