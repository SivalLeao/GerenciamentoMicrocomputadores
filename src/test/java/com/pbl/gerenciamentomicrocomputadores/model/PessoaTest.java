package com.pbl.gerenciamentomicrocomputadores.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PessoaTest {
    private Cliente c0;
    private Tecnico t0;

    @BeforeEach
    public void setUp() {
        c0 = new Cliente("Dayana", "Rua Aquário num 2", "77777777777", "22222222222");
        t0 = new Tecnico("Samire", "Rua Libra, 23", "88888888888", "11111111111");

    }
    @Test
    void validarNome () {
        assertTrue( c0.validarNome("Day"));
        assertFalse( c0.validarNome("Day 10"));
        assertFalse( c0.validarNome("Da"));

        assertTrue( t0.validarNome("Sam"));
        assertFalse( t0.validarNome("Sam 10"));
        assertFalse( t0.validarNome("Sa"));
    }

    @Test
    void validarEndereco () {
        assertTrue( c0.validarEndereco("Rua"));
        assertFalse( c0.validarNome("Ru"));

        assertTrue( t0.validarEndereco("Rua"));
        assertFalse( t0.validarNome("Ru"));
    }

    @Test
    void validarTelefone () {
        assertTrue( c0.validarTelefone( "(13) 53476-9012"));
        assertTrue( c0.validarTelefone( "13534769012"));
        assertFalse( c0.validarTelefone( "1567562096"));

        assertTrue( t0.validarTelefone( "(12) 34567-8901"));
        assertTrue( t0.validarTelefone( "12345678901"));
        assertFalse( t0.validarTelefone( "1234567890"));
    }

    @Test
    void validarCpf () {
        assertTrue( c0.validarCpf( "321.654.987-10"));
        assertTrue( c0.validarCpf( "32165498710"));
        assertFalse( c0.validarCpf( "0987654321"));

        assertTrue( t0.validarCpf( "123.456.789-01"));
        assertTrue( t0.validarCpf( "12345678901"));
        assertFalse( t0.validarCpf( "1234567890"));
    }

    @Test
    void imprimirPessoa(){
        c0.setId(1112);
        assertEquals("""
                             NOME: Dayana
                             CPF: 22222222222           TELEFONE: 77777777777      ID: 1112
                             ENDEREÇO: Rua Aquário num 2     
                            """,c0.imprimirPessoa());

        t0.setId(1111);
        assertEquals("""
                             NOME: Samire
                             CPF: 11111111111           TELEFONE: 88888888888      ID: 1111
                             ENDEREÇO: Rua Libra, 23    
                            """,t0.imprimirPessoa());
    }
}
