package com.pbl.gerenciamentomicrocomputadores.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescricaoServicoTest {
    private OrdemDeServico ordem0;

    @BeforeEach
    void setUp(){
        ordem0 = new OrdemDeServico( 1111, 1112);
    }

    @Test
    void imprimirDescricaoServico () {
        ordem0.getDescricaoServico().setTipoDeServico("Montagem/Instalação");
        ordem0.getDescricaoServico().setMapItens("ram", 3);
        ordem0.getDescricaoServico().setMapItens("hd", 1);

        assertEquals("""
                Descrição: Montagem/Instalação de peças
                Peças:
                
                Peça 1 - nome: hd ; quantidade: 1
                Peça 2 - nome: ram ; quantidade: 3
                """, ordem0.getDescricaoServico().imprimirDescricaoServico());

        OrdemDeServico ordem1 = new OrdemDeServico( 1121, 1122);

        ordem1.getDescricaoServico().setTipoDeServico("Programas");

        assertEquals("""
                Descrição: Formatação/Instalação de programas
                """, ordem1.getDescricaoServico().imprimirDescricaoServico());

    }

}
