package com.pbl.gerenciamentomicrocomputadores.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Classe para objetos do tipo Data. Possui a data de início e a de finalização do serviço como atributos.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
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

    public String imprimirDatas () {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String dataInicial = this.dataInicio.format(dateTimeFormatter);
        String dataFinal = this.dataFim.format(dateTimeFormatter);

        return String.format(
                """
                Data e horário de início: %s
                Data e horário de finalização: %s
                """,
                dataInicial, dataFinal);
    }

}
