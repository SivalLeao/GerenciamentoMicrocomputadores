package com.pbl.gerenciamentomicrocomputadores.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PecaTest {

    @Test
    void imprimirPeca () {

        Peca peca0 = new Peca( "teclado", 2, 40, 30);

        assertEquals("""
                Nome: teclado
                Quantidade: 2
                Valor: 40,0
                Custo: 30,
                """, peca0.imprimirPeca());
    }

}
