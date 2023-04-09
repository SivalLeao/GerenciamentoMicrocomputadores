package com.pbl.gerenciamentomicrocomputadores.model;

import java.util.HashMap;
import java.util.Map;
/** Classe para objetos do tipo DescricaoServico. É responsável por armazenar as informações da descrição do
 * serviço, como o tipo e seus itens.
 *
 * @author Silvio Oliveira,  Sival Leão;
 * @version 1.0
 */
public class DescricaoServico {

    private String tipoDeServico;

    private Map<String, Integer> mapItens;

    /** Construtor responsável por inicializa o atributo mapItens com um novo objeto da classe.
     */
    public DescricaoServico() {

        this.mapItens = new HashMap<String, Integer>();

    }

    /** Método para inserir o dado no atributo tipoDeServico.
     *
     * @param tipoDeServico String - Tipo de serviço
     */
    public void setTipoDeServico(String tipoDeServico) {
        this.tipoDeServico = tipoDeServico;
    }

    /** Método que retorna tipoDeServico.
     *
     * @return String - Tipo de serviço
     */
    public String getTipoDeServico() {
        return this.tipoDeServico;
    }

    /** Método usado para adicionar um novo item ao mapa de itens do serviço.
     *
     * @param nomePeca String - Nome da peça
     * @param quantidade int _ Quantidade da peça
     */
    public void setMapItens (String nomePeca, int quantidade) {
        this.mapItens.put(nomePeca, quantidade);
    }

    /** Método que retorna um mapa de itens do serviço.
     *
     * @return Map - Mapa de itens do serviço.
     */
    public Map<String, Integer> getMapItens () {

        Map<String, Integer> mapItens = new HashMap<String, Integer>();

        for (String nomeItem: this.mapItens.keySet()) {

            mapItens.put(nomeItem, this.mapItens.get(nomeItem));
        }

        return mapItens;
    }

}
