package com.pbl.gerenciamentomicrocomputadores.model;
/** Classe para objetos do tipo Peca. É responsavel por armazenar as informações de
 *  uma nova peças novas na assistência técnica.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */

import java.text.Normalizer;

public class Peca {


    private String nome;

    private int quantidade;

    private double valor;

    private double custo;

    /** Construtor responsável por criar uma peça.
     *
     * @param nome String - Nome da peça
     * @param quantidade int - Quantidade de peças a serem armazenadas
     * @param valor double - Valor a ser pago pelo os clientes
     * @param custo double - Valor pago pela assistência técnica
     */
    public Peca (String nome, int quantidade, double valor, double custo) {

        this.nome = Normalizer.normalize(nome, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
        this.quantidade = quantidade;
        this.valor = valor;
        this.custo = custo;
    }

    /** Método que recebe uma String contendo um nome de uma peça e armazena em uma variável.
     *
     * @param nome String - Nome da peça
     */
    public void setNome (String nome) {
        this.nome = Normalizer.normalize(nome, Normalizer.Form.NFD).toLowerCase();
    }

    /** Método que retorna o nome da peça.
     *
     * @return String - Nome da peça
     */
    public String getNome () {
        return nome;
    }

    /** Método que recebe um int contendo o valor referente a quantidade de peças e armazena em uma variável.
     *
     * @param quantidade int - Quantidade de peças a serem armazenadas
     */
    public void setQuantidade (int quantidade) {
        this.quantidade = quantidade;
    }

    /** Método que retorna a quantidade de peças.
     *
     * @return int - Quantidade de peças
     */
    public int getQuantidade () {
        return quantidade;
    }

    /** Método que recebe um double contendo o valor referente ao valor da peça a ser pago pelo cliente
     * e armazena em uma variável.
     *
     * @param valor double - Valor a ser pago pelo os clientes
     */
    public void setValor (double valor) {
        this.valor = valor;
    }

    /** Método que retorna o valor a ser pago pelo cliente pela peça.
     *
     * @return double - Valor a ser pago pelo os clientes
     */
    public double getValor () {
        return valor;
    }

    /** Método que recebe um double contendo o valor referente ao valor da peça a ser pago pela assistência técnica
     * e armazena em uma variável.
     *
     * @param custo double - Valor pago pela assistência técnica
     */
    public void setCusto(double custo) {
        this.custo = custo;
    }

    /** Método que retorna o valor a ser pago pela assistência técnica pela peça.
     *
     * @return double - Valor pago pela assistência técnica
     */
    public double getCusto() {
        return custo;
    }
}
