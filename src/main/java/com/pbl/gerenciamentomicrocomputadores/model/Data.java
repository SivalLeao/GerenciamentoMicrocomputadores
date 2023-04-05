package com.pbl.gerenciamentomicrocomputadores.model;

import java.time.LocalDateTime;

public class Data {

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    public Data (LocalDateTime dataInicio) {

        this.dataInicio = dataInicio;
    }

    public void setDataInicio (LocalDateTime dataInicio) { this.dataInicio = dataInicio; }

    public LocalDateTime getDataInicio () { return this.dataInicio; }

    public void setDataFim (LocalDateTime dataFim) { this.dataFim = dataFim; }

    public LocalDateTime getDataFim () { return this.dataFim; }

}
