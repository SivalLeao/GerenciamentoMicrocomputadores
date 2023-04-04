package com.pbl.gerenciamentomicrocomputadores.model;

import java.time.temporal.ChronoUnit;

public class OrdemDeServico {

    private int idOrdem;

    private int idCliente;

    private int idTecnico;

    private Data data;

    private String statusAtual;

    private TipoDeServico tipoDeServico;

    private RelatorioServico relatorioServico;

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

    public void setTipoDeServico (TipoDeServico tipoDeServico) { this.tipoDeServico = tipoDeServico; }

    public TipoDeServico getTipoDeServico () { return this.tipoDeServico; }

    public long calcularTempoDeServico () {

        return ChronoUnit.SECONDS.between(this.data.getDataInicio(), this.data.getDataFim());

    }

}
