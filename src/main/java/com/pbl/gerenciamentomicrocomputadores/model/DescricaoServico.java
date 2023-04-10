package com.pbl.gerenciamentomicrocomputadores.model;

import java.util.HashMap;
import java.util.Map;

/** Classe para objetos do tipo DescricaoServico. É responsável por armazenar as informações da descrição do
 * serviço, como o tipo e os itens que devem ser utilizados, caso precise de algum.
 *
 * @author Silvio Oliveira,  Sival Leão;
 * @version 1.0
 */

public class DescricaoServico {

    private String tipoDeServico;

    private Map<String, Integer> mapItens;

    /** Construtor responsável por inicializar o atributo mapItens, que será responsável por
     * armazenar os itens utilizados no serviço e a quantidade de cada um.*/

    public DescricaoServico() {

        this.mapItens = new HashMap<String, Integer>();

    }

    /** Método para inserir o tipo de serviço.
     *
     * @param tipoDeServico String - Tipo de serviço*/

    public void setTipoDeServico(String tipoDeServico) {
        this.tipoDeServico = tipoDeServico;
    }

    /** Método que retorna o tipo de serviço.
     *
     * @return String - Tipo de serviço*/

    public String getTipoDeServico() {
        return this.tipoDeServico;
    }

    /** Método usado para adicionar uma nova peça na estrutura de armazenamento com as peças utilizadas no serviço.
     *
     * @param nomePeca String - Nome da peça
     * @param quantidade int - Quantidade da peça*/

    public void setMapItens (String nomePeca, int quantidade) {
        this.mapItens.put(nomePeca, quantidade);
    }

    /** Método que retorna um map com os itens utilizados no serviço e a quantidade de cada um.
     *
     * @return Map - Mapa de itens do serviço.*/

    public Map<String, Integer> getMapItens () {

        Map<String, Integer> mapItens = new HashMap<String, Integer>();

        for (String nomeItem: this.mapItens.keySet()) {

            mapItens.put(nomeItem, this.mapItens.get(nomeItem));
        }

        return mapItens;
    }

    /** Método de retorno dos dados da descrição do serviço em um único objeto String.
     *
     * @return String - informações da descrição do serviço.*/

    public String imprimirDescricaoServico () {

        String descricao = "";

        if ( this.tipoDeServico.equals("Montagem/Instalação")) {
            descricao = "Montagem/Instalação de peças";
        }
        else if ( this.tipoDeServico.equals("Sistema operacional")) {
            descricao = "Formatação/Instalação de sistema operacional";
        }
        else if ( this.tipoDeServico.equals("Programas")) {
            descricao = "Formatação/Instalação de programas";
        }
        else if ( this.tipoDeServico.equals("Limpeza")) {
            descricao = "Limpeza";
        }

        String dadosPecas;

        if ( this.tipoDeServico.equals("Montagem/Instalação")) {

            dadosPecas = "\nPeças:\n";
            String linhaString = "";
            int contador = 0;

            for (String nomePeca: this.mapItens.keySet()) {

                contador++;

                linhaString = String.format("\nPeça %d - nome: %s ; quantidade: %d",
                        contador, nomePeca, this.mapItens.get(nomePeca));

                dadosPecas = dadosPecas.concat(linhaString);
            }

        }
        else {

            dadosPecas = "";
        }

        return String.format(
                """
                Descrição: %s%s
                """,
                descricao, dadosPecas);
    }

}
