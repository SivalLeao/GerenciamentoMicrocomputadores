package com.pbl.gerenciamentomicrocomputadores.model;

import java.time.LocalDateTime;

/** Classe para objetos do tipo Data. Possui a data de início e a de finalização do serviço como atributos.
 *
 * @author Silvio Oliveira
 * @author Sival Leão
 */

public class Data {

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    /** Construtor que recebe como parâmetro um objeto do tipo LocalDateTime, que é inserido diretamente
     * como o atributo dataInicio.
     *
     * @param dataInicio LocalDateTime - data de início do serviço.*/

    public Data (LocalDateTime dataInicio) {

        this.dataInicio = dataInicio;
    }


    /** Método para inserir a data de início do serviço no atributo dataInicio.
     *
     * @param dataInicio LocalDateTime - data de início do serviço.*/

    public void setDataInicio (LocalDateTime dataInicio) { this.dataInicio = dataInicio; }

    /** Método de retorno da data de início do serviço.
     *
     * @return LocalDateTime - data de início do serviço.*/

    public LocalDateTime getDataInicio () { return this.dataInicio; }

    /** Método para inserir a data de finalização do serviço no atributo dataFim.
     *
     * @param dataFim LocalDateTime - data de finalização do serviço.*/

    public void setDataFim (LocalDateTime dataFim) { this.dataFim = dataFim; }

    /** Método de retorno da data de finalização do serviço.
     *
     * @return LocalDateTime - data de finalização do serviço.*/

    public LocalDateTime getDataFim () { return this.dataFim; }

}
