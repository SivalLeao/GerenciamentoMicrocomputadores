package com.pbl.gerenciamentomicrocomputadores.model;

import java.io.Serializable;
import java.text.Normalizer;

/** Classe para objetos do tipo Peca. É responsável por armazenar as informações de
 *  uma peça da assistência técnica.
 *
 * @author Silvio Oliveira,  Sival Leão;
 * @version 1.0
 */

public class Peca implements Serializable {

    private String nome;
    private int quantidade;
    private double valor;
    private double custo;

    /** Construtor responsável por criar uma peça.
     *
     * @param nome String - Nome da peça
     * @param quantidade int - Quantidade de peças a serem armazenadas
     * @param valor double - Preço da peça para os clientes
     * @param custo double - Custo da peça para a assistência técnica*/

    public Peca (String nome, int quantidade, double valor, double custo) {

        this.nome = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
        this.quantidade = quantidade;
        this.valor = valor;
        this.custo = custo;
    }

    /** Método para inserir o nome da peça. Os acentos são retirados e todos os caracteres são
     * convertidos para minúsculo antes do nome ser inserido.
     *
     * @param nome String - Nome da peça*/

    public void setNome (String nome) { this.nome = Normalizer.normalize(nome, Normalizer.Form.NFD).toLowerCase(); }

    /** Método que retorna o nome da peça.
     *
     * @return String - Nome da peça*/

    public String getNome () { return nome; }

    /** Método de inserção da quantidade da peça.
     *
     * @param quantidade int - Quantidade de peças a serem armazenadas*/

    public void setQuantidade (int quantidade) { this.quantidade = quantidade; }

    /** Método que retorna a quantidade de peças.
     *
     * @return int - Quantidade de peças*/

    public int getQuantidade () { return quantidade; }

    /** Método de inserção do valor da peça.
     *
     * @param valor double - Valor a ser pago pelos clientes*/

    public void setValor (double valor) { this.valor = valor; }

    /** Método que retorna o valor a ser pago pelos clientes pela peça.
     *
     * @return double - Valor a ser pago pelos clientes*/

    public double getValor () { return valor; }

    /** Método de inserção do custo da peça para a assistência técnica.
     *
     * @param custo double - Custo da peça para a assistência técnica*/

    public void setCusto (double custo) { this.custo = custo; }

    /** Método que retorna o valor pago pela assistência técnica pela peça.
     *
     * @return double - Valor pago pela assistência técnica*/

    public double getCusto () { return custo; }

    /** Método para converter todos os dados da peça em um único objeto String e retornar ao usuário.
     *
     * @return String - informações da peça.*/

    public String imprimirPeca () {

        return String.format(
                """
                Nome: %s
                Quantidade: %d
                Valor: %.1f
                Custo: %.1f
                """,
                this.nome, this.quantidade, this.valor, this.custo);
    }

}
