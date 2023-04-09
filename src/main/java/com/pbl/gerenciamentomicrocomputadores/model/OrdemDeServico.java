package com.pbl.gerenciamentomicrocomputadores.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/** Classe para objetos do tipo OrdemDeServico. É responsável por armazenar as informações de gerenciamento do
 *  pedido de serviço que o cliente solicitar à assistência técnica, contendo informações como o ID da própria
 *  ordem, o ID do técnico responsável e do cliente, além da descrição e outras informações importantes do serviço
 *  solicitado.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */
public class OrdemDeServico {

    private int idOrdem;

    private int idCliente;

    private int idTecnico;

    private Data data;

    private DescricaoServico descricaoServico;

    private String statusAtual;

    private String formaPagamento;

    private String satisfacaoCliente;

    private double valorTotalFatura;

    /** Construtor responsável por definir a hora atual da criação da ordem de serviço e criar um objeto vazio
     * para armazenar as informações do serviço solicitado pelo cliente.
     */
    public OrdemDeServico () {

        this.data = new Data( LocalDateTime.now());
        this.descricaoServico = new DescricaoServico();
    }

    /** Método para inserir o dado no atributo idOrdem.
     *
     * @param id int - Id da ordem de serviço
     */
    public void setIdOrdem (int id) {
        this.idOrdem = id;
    }

    /** Método que retorna o id da ordem de serviço.
     *
     * @return int - Id da ordem de serviço
     */
    public int getIdOrdem () {
        return this.idOrdem;
    }

    /** Método para inserir o dado no atributo idCliente.
     *
     * @param id int - Id do cliente
     */
    public void setIdCliente (int id) {
        this.idCliente = id;
    }

    /** Método que retorna o id do cliente.
     *
     * @return int - Id do cliente
     */
    public int getIdCliente () {
        return this.idCliente;
    }

    /** Método para inserir o dado no atributo idTecnico.
     *
     * @param id int - Id do técnico
     */
    public void setIdTecnico (int id) {
        this.idTecnico = id;
    }

    /** Método que retorna o id do técnico.
     *
     * @return int - Id do técnico
     */
    public int getIdTecnico () {
        return this.idTecnico;
    }

    /** Método para inserir o dado no atributo data.
     *
     * @param data Data - data
     */
    public void setData (Data data) {
        this.data = data;
    }

    /** Método que retorna a data.
     *
     * @return Data - data
     */
    public Data getData () {
        return this.data;
    }

    /** Método para inserir o dado no atributo descricaoServico.
     *
     * @param descricaoServico DescricaoServico - descricaoServico
     */
    public void setDescricaoServico (DescricaoServico descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    /** Método que retorna descricaoServico.
     *
     * @return DescricaoServico - descricaoServico
     */
    public DescricaoServico getDescricaoServico () {
        return this.descricaoServico;
    }

    /** Método para inserir o dado no atributo statusAtual.
     *
     * @param statusAtual String - statusAtual
     */
    public void setStatusAtual (String statusAtual) {
        this.statusAtual = statusAtual;
    }

    /** Método que retorna statusAtual.
     *
     * @return String - statusAtual
     */
    public String getStatusAtual () {
        return this.statusAtual;
    }

    /** Método para inserir o dado no atributo formaPagamento.
     *
     * @param formaPagamento String - formaPagamento
     */
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /** Método que retorna formaPagamento.
     *
     * @return String - formaPagamento
     */
    public String getFormaPagamento() {
        return this.formaPagamento;
    }

    /** Método para inserir o dado no atributo satisfacaoCliente.
     *
     * @param satisfacaoCliente String - satisfacaoCliente
     */
    public void setSatisfacaoCliente (String satisfacaoCliente) {
        this.satisfacaoCliente = satisfacaoCliente;
    }

    /** Método que retorna satisfacaoCliente.
     *
     * @return String - satisfacaoCliente
     */
    public String getSatisfacaoCliente () {
        return this.satisfacaoCliente;
    }

    /** Método para inserir o dado no atributo satisfacaoCliente.
     *
     * @param valorTotalFatura double - valorTotalFatura
     */
    public void setValorTotalFatura(double valorTotalFatura) {
        this.valorTotalFatura = valorTotalFatura;
    }

    /** Método que retorna valorTotalFatura.
     *
     * @return double - valorTotalFatura
     */
    public double getValorTotalFatura() {
        return this.valorTotalFatura;
    }

    /** Método que retorna o tempo gasto para realizar o serviço.
     *
     * @return "long - tempo de duração
     */
    public long calcularTempoDeServico () {

        return ChronoUnit.SECONDS.between(this.data.getDataInicio(), this.data.getDataFim());
    }

    /** Método que calcula o valor total do serviço prestado..
     *
     * @param mapPeca map - mapPeca
     * @return double - valorPecas
     */
    public double calcularValorServico (Map<String, Peca> mapPeca) {

        Map<String, Integer> mapPecaServico = this.descricaoServico.getMapItens();

        double valorPecas = 0.0;

        if (this.descricaoServico.getTipoDeServico().equals("Montagem/Instalação")) {

            for (String nomePeca : mapPecaServico.keySet()) {

                valorPecas += mapPeca.get(nomePeca).getValor() * mapPecaServico.get(nomePeca);
            }

        }
        else if (this.descricaoServico.getTipoDeServico().equals("Sistema operacional")) {

            valorPecas = 50;
        }
        else if (this.descricaoServico.getTipoDeServico().equals("Programas")) {

            valorPecas = 10;
        }
        else if (this.descricaoServico.getTipoDeServico().equals("Limpeza")) {

            valorPecas = 70;
        }

        return valorPecas;
    }

}
