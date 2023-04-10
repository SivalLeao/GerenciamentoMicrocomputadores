package com.pbl.gerenciamentomicrocomputadores.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/** Classe para objetos do tipo OrdemDeServico. É responsável por armazenar as informações de gerenciamento do
 *  pedido de serviço que o cliente solicitar à assistência técnica. Contém informações, como o ID da própria
 *  ordem, o ID do técnico responsável e o ID do cliente, além da descrição e outras informações relevantes.
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
     * para armazenar as informações da descrição do tipo de serviço solicitado pelo cliente. Os atributos da
     * forma de pagamento e da satisfação do cliente são inicializados com textos vazios.*/

    public OrdemDeServico ( int idTecnico, int idCliente) {

        this.data = new Data( LocalDateTime.now());
        this.idTecnico = idTecnico;
        this.idCliente = idCliente;
        this.descricaoServico = new DescricaoServico();
        this.formaPagamento = "";
        this.satisfacaoCliente = "";
    }

    /** Método para inserir o ID da ordem de serviço.
     *
     * @param id int - ID da ordem de serviço*/

    public void setIdOrdem (int id) {
        this.idOrdem = id;
    }

    /** Método para retornar o ID da ordem de serviço.
     *
     * @return int - ID da ordem de serviço*/

    public int getIdOrdem () {
        return this.idOrdem;
    }

    /** Método para inserir o ID do clinte que solicitou a ordem de serviço.
     *
     * @param id int - ID do cliente*/

    public void setIdCliente (int id) {
        this.idCliente = id;
    }

    /** Método que retorna o ID do cliente que solicitou a ordem de serviço.
     *
     * @return int - ID do cliente*/

    public int getIdCliente () {
        return this.idCliente;
    }

    /** Método para inserir o ID do técnico responsável pela ordem de serviço.
     *
     * @param id int - ID do técnico*/

    public void setIdTecnico (int id) {
        this.idTecnico = id;
    }

    /** Método que retorna o ID do técnico responsável pela ordem de serviço.
     *
     * @return int - ID do técnico*/

    public int getIdTecnico () {
        return this.idTecnico;
    }

    /** Método para inserir o objeto do tipo Data, que contém a data inicial e a final da realização do serviço.
     *
     * @param data Data - objeto que contém as datas do serviço*/

    public void setData (Data data) {
        this.data = data;
    }

    /** Método que retorna o objeto do tipo Data.
     *
     * @return Data - objeto que contém as datas do serviço*/

    public Data getData () {
        return this.data;
    }

    /** Método para inserir o objeto do tipo DescricaoServico, que contém os detalhes do
     * tipo de serviço solicitado pelo cliente.
     *
     * @param descricaoServico DescricaoServico - objeto que contém os detalhes do tipo de serviço*/

    public void setDescricaoServico (DescricaoServico descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    /** Método que retorna o objeto do tipo DescricaoServico.
     *
     * @return DescricaoServico - objeto que contém os detalhes do tipo de serviço*/

    public DescricaoServico getDescricaoServico () {
        return this.descricaoServico;
    }

    /** Método para inserir o status atual do serviço.
     *
     * @param statusAtual String - status atual do serviço*/

    public void setStatusAtual (String statusAtual) {
        this.statusAtual = statusAtual;
    }

    /** Método que retorna o status atual do serviço.
     *
     * @return String - status atual do serviço*/

    public String getStatusAtual () {
        return this.statusAtual;
    }

    /** Método para inserir a forma de pagamento do serviço realizado.
     *
     * @param formaPagamento String - forma de pagamento do serviço*/

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /** Método que retorna a forma de pagamento do serviço realizado.
     *
     * @return String - forma de pagamento do serviço*/

    public String getFormaPagamento() {
        return this.formaPagamento;
    }

    /** Método para inserir a satisfação do cliente.
     *
     * @param satisfacaoCliente String - satisfação do cliente*/

    public void setSatisfacaoCliente (String satisfacaoCliente) {
        this.satisfacaoCliente = satisfacaoCliente;
    }

    /** Método que retorna a satisfação do cliente.
     *
     * @return String - satisfação do cliente*/

    public String getSatisfacaoCliente () {
        return this.satisfacaoCliente;
    }

    /** Método para inserir o valor total do serviço.
     *
     * @param valorTotalFatura double - valor total do serviço*/

    public void setValorTotalFatura(double valorTotalFatura) {
        this.valorTotalFatura = valorTotalFatura;
    }

    /** Método que retorna o valor total do serviço.
     *
     * @return double - valor total do serviço*/

    public double getValorTotalFatura() {
        return this.valorTotalFatura;
    }

    /** Método que retorna o tempo de realização do serviço. É contado da data e horário de cadastro da
     * ordem de serviço até o momento que o status é declarado como "Finalizado". A diferença está sendo
     * medida em segundos.
     *
     * @return long - tempo de realização do serviço*/

    public long calcularTempoDeServico () {

        return ChronoUnit.SECONDS.between(this.data.getDataInicio(), this.data.getDataFim());
    }

    /** Método que calcula o valor total da ordem de serviço. Recebe como parâmetro os dados das
     * peças em estoque, para fazer a contagem do preço pelos itens utilizados, caso o tipo de serviço
     * seja uma montagem. Outros serviços possuem valor fixo.
     *
     * @param mapPeca Map - informações das peças em estoque. O preço das peças é a informação procurada.
     * @return double - valor total do serviço*/

    public double calcularValorServico (Map<String, Peca> mapPeca) {

        Map<String, Integer> mapPecaServico = this.descricaoServico.getMapItens();

        double valorServico = 0.0;

        if (this.descricaoServico.getTipoDeServico().equals("Montagem/Instalação")) {

            for (String nomePeca : mapPecaServico.keySet()) {

                valorServico += mapPeca.get(nomePeca).getValor() * mapPecaServico.get(nomePeca);
            }

        }
        else if (this.descricaoServico.getTipoDeServico().equals("Sistema operacional")) {

            valorServico = 50;
        }
        else if (this.descricaoServico.getTipoDeServico().equals("Programas")) {

            valorServico = 10;
        }
        else if (this.descricaoServico.getTipoDeServico().equals("Limpeza")) {

            valorServico = 70;
        }

        return valorServico;
    }

    /** Método de retorno dos dados básicos da ordem de serviço. Incluindo o ID da ordem, ID do cliente,
     * ID do técnico e o status atual.
     *
     * @return String - informações da ordem de serviço.*/

    public String imprimirOrdem () {

        return String.format(
                """
                ID da ordem: %d
                ID do cliente: %d
                ID do técnico: %d
                Status atual: %s
                """,
                this.idOrdem, this.idCliente, this.idTecnico, this.statusAtual);
    }

    /** Método de retorno das informações da fatura. Incluindo o tipo de serviço; as peças utilizadas,
     * juntamente com os seus preços e quantidades; e o valor total do serviço.
     *
     * @param informacoesEstoque Map<String,Peca> - estrutura com as informações das peças armazenadas
     *                           em estoque.
     * @return String - informações da fatura.*/

    public String imprimirFatura (Map<String, Peca> informacoesEstoque) {

        String dadosPecas;

        if ( this.descricaoServico.getTipoDeServico().equals("Montagem/Instalação")) {

            Map<String, Integer> itensUsados = this.descricaoServico.getMapItens();

            dadosPecas = "\nPeças utilizadas:\n";
            String linhaString = "";
            int contador = 0;

            for (String nomePeca: itensUsados.keySet()) {

                contador++;

                linhaString = String.format("\nPeça %d - nome: %s ; preço: %.1f ; quantidade: %d",
                        contador, nomePeca, informacoesEstoque.get(nomePeca).getValor(), itensUsados.get(nomePeca));

                dadosPecas = dadosPecas.concat(linhaString);
            }

            dadosPecas = dadosPecas.concat("\n");
        }
        else {

            dadosPecas = "";
        }

        return String.format(
                """
                Tipo de serviço: %s%s
                Valor total: %.1f
                """,
                this.descricaoServico.getTipoDeServico(), dadosPecas, this.valorTotalFatura);
    }

    /** Método de retorno das informações do relatório. Incluindo tempo médio de espera; as peças utilizadas,
     * juntamente com os seus custos para a assistência técnica e suas quantidades; e a satisfação do cliente.
     *
     * @param informacoesEstoque Map<String,Peca> - estrutura com as informações das peças armazenadas
     *                           em estoque.
     * @return String - informações do relatório.*/

    public String imprimirRelatorio (Map<String, Peca> informacoesEstoque) {

        String dadosPecas;

        if ( this.descricaoServico.getTipoDeServico().equals("Montagem/Instalação")) {

            Map<String, Integer> itensUsados = this.descricaoServico.getMapItens();

            dadosPecas = "\nPeças utilizadas:\n";
            String linhaString = "";
            int contador = 0;

            for (String nomePeca: itensUsados.keySet()) {

                contador++;

                linhaString = String.format("\nPeça %d - nome: %s ; custo: %.1f ; quantidade: %d",
                        contador, nomePeca, informacoesEstoque.get(nomePeca).getCusto(), itensUsados.get(nomePeca));

                dadosPecas = dadosPecas.concat(linhaString);
            }

            dadosPecas = dadosPecas.concat("\n");
        }
        else {

            dadosPecas = "";
        }

        return String.format(
                """
                Tempo médio de espera: %d segundos%s
                Satisfação do cliente: %s
                """,
                calcularTempoDeServico(), dadosPecas, this.satisfacaoCliente);
    }


}
