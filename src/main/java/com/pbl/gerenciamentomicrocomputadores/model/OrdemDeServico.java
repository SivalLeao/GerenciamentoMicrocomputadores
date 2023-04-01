package com.pbl.gerenciamentomicrocomputadores.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class OrdemDeServico {

    private int idOrdem;

    private int idCliente;

    private int idTecnico;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private String statusAtual;

    private String tipoDeServico;

    private List<String> listItens;

    private RelatorioServico relatorioServico;

    public OrdemDeServico () {

        this.listItens = new ArrayList<String>();
    }

    public void setIdOrdem (int id) { this.idOrdem = id; }

    public int getIdOrdem () { return this.idOrdem; }

    public void setIdCliente (int id) { this.idCliente = id; }

    public int getIdCliente () { return this.idCliente; }

    public void setIdTecnico (int id) { this.idTecnico = id; }

    public int getIdTecnico () { return this.idTecnico; }

    public void setDataInicio (LocalDateTime dataInicio) { this.dataInicio = dataInicio; }

    public LocalDateTime getDataInicio () { return this.dataInicio; }

    public void setDataFim (LocalDateTime dataFim) { this.dataFim = dataFim; }

    public LocalDateTime getDataFim () { return this.dataFim; }

    public void setStatusAtual (String statusAtual) { this.statusAtual = statusAtual; }

    public String getStatusAtual () { return this.statusAtual; }

    public void setTipoDeServico (String tipoDeServico) { this.tipoDeServico = tipoDeServico; }

    public String getTipoDeServico () { return this.tipoDeServico; }

    public void setListItens (String item) { this.listItens.add(item); }

    public List<String> getListItens () {

        List<String> listItens = new ArrayList<String>();

        for (String item: this.listItens) {

            listItens.add(item);
        }

        return listItens;
    }

    public long calcularTempoDeServico () {

        return ChronoUnit.SECONDS.between(this.dataInicio, this.dataFim);

    }

}
