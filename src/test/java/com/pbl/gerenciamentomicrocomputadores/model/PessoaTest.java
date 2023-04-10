package com.pbl.gerenciamentomicrocomputadores.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PessoaTest {

    @Test
    void validarNome () {

        Cliente c0 = new Cliente("Dayana", "Rua Aquário num 2", "77777777777", "22222222222");

        assertTrue( c0.validarNome("Day"));
        assertFalse( c0.validarNome("Day 10"));
        assertFalse( c0.validarNome("Da"));
    }

    @Test
    void validarEndereco () {

        Cliente c0 = new Cliente("Dayana", "Rua Aquário num 2", "77777777777", "22222222222");

        assertTrue( c0.validarEndereco("Rua"));
        assertFalse( c0.validarNome("Ru"));
    }

    @Test
    void validarTelefone () {

        Tecnico t0 = new Tecnico("Dayana", "Rua Aquário num 2", "77777777777", "22222222222");

        assertTrue( t0.validarTelefone( "(12) 34567-8901"));
        assertTrue( t0.validarTelefone( "12345678901"));
        assertFalse( t0.validarTelefone( "1234567890"));
    }

    @Test
    void validarCpf () {

        Tecnico t0 = new Tecnico("Dayana", "Rua Aquário num 2", "77777777777", "22222222222");

        assertTrue( t0.validarCpf( "123.456.789-01"));
        assertTrue( t0.validarCpf( "12345678901"));
        assertFalse( t0.validarCpf( "1234567890"));
    }
    @Test
    void imprimirPessoa(){
        Cliente c0 = new Cliente("Dayana", "Rua Aquário num 2", "77777777777", "22222222222");
        c0.setId(1112);

        assertEquals("""
                             NOME: Dayana
                             CPF: 22222222222           TELEFONE: 77777777777      ID: 1112
                             ENDEREÇO: Rua Aquário num 2     
                            """,c0.imprimirPessoa());

        Tecnico t0 = new Tecnico("Joana", "Rua Libra, 22", "88888888888", "11111111111");
        t0.setId(1111);

        assertEquals("""
                             NOME: Joana
                             CPF: 11111111111           TELEFONE: 88888888888      ID: 1111
                             ENDEREÇO: Rua Libra, 22    
                            """,t0.imprimirPessoa());
    }
}
