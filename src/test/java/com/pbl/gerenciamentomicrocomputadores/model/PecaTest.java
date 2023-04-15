package com.pbl.gerenciamentomicrocomputadores.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PecaTest {
    private Peca peca0;
    @BeforeEach
    void setUp(){
        peca0 = new Peca( "teclado", 2, 40.0, 30.0);
    }
    @Test
    void imprimirPeca () {
        assertEquals("""
                Nome: teclado
                Quantidade: 2
                Valor: 40,0
                Custo: 30,0
                """, peca0.imprimirPeca());
    }
}
