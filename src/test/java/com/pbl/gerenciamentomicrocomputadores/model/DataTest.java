package com.pbl.gerenciamentomicrocomputadores.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataTest {

    @Test
    void imprimirDatas () {

        LocalDateTime dataInicial = LocalDateTime.parse("2023-04-09 12:00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime dataFinal = LocalDateTime.parse("2023-04-14 17:00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Data data = new Data(dataInicial);
        data.setDataFim(dataFinal);

        assertEquals("""
                Data e horário de início: 2023-04-09 12:00
                Data e horário de finalização: 2023-04-14 17:00
                """, data.imprimirDatas());
    }

}
