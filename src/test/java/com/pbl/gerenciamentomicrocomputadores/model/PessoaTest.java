package com.pbl.gerenciamentomicrocomputadores.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    
}
