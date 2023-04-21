package com.pbl.gerenciamentomicrocomputadores.dao.peca;

import com.pbl.gerenciamentomicrocomputadores.dao.CRUD;
import com.pbl.gerenciamentomicrocomputadores.model.Peca;

import java.util.List;
import java.util.Map;

/** Interface para as implementações de armazenamento dos objetos da classe Peca. É uma
 * extensão da interface CRUD.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */

public interface PecaDAO extends CRUD<Peca> {

    /** Método para remover determinada quantidade de uma peça específica. A peça é identificada
     * pelo nome.
     *
     * @param nome String - nome da peça.
     * @param quantidade int - quantidade que deve ser retirada da peça.*/

    public void removerQuantidade(String nome, int quantidade);

    /** Método para adicionar determinada quantidade de uma peça específica. A peça é identificada
     * pelo nome.
     *
     * @param nome String - nome da peça.
     * @param quantidade int - quantidade que deve ser adicionada da peça.*/

    public void adicionarQuantidade(String nome, int quantidade);

    /** Método de retorno de uma peça através da busca por nome.
     *
     * @param nome String - nome da peça.
     * @return Peca - peça encontrada após a busca por nome.*/

    public Peca encontrarPorNome(String nome);

    /** Método para checar se uma peça está armazenada no sistema através da busca por nome.
     *
     * @param nome String - nome da peça.
     * @return boolean - resultado da busca pela peça. Se foi achada ou não.*/

    public boolean checarPorNome(String nome);

    /** Método para checar se um tipo de peça tem quantidade suficiente para realizar
     * determinada ordem de serviço de montagem.
     *
     * @param nome String - nome da peça.
     * @param quantidade int - quantidade que o serviço precisa utilizar da peça.
     * @return boolean - resultado da checagem de quantidade. Se a peça possui a quantidade suficiente
     * ou não.*/

    public boolean checarQuantidade(String nome, int quantidade);

    /** Método para encontrar as peças do sistema que estão com uma baixa quantidade. Com o objetivo
     * de alertar o usuário.
     *
     * @return List - lista de peças com baixa quantidade.*/

    public List<Peca> alertaDeQuantidade();

    /** Método para devolver a quantidade retirada das peças para a realização de uma
     * ordem de serviço. Utilizado quando uma ordem de serviço é cancelada e as peças precisam ser
     * adicionadas novamente. Caso algumas peças não possam ser retornadas ao sistema, uma estrutura
     * listando elas é retornada pelo método.
     *
     * @param mapItens Map - estrutura com o nome das peças e suas quantidades
     *                 que devem ser devolvidas.
     * @return Map - estrutura com os nomes as peças que não puderam ser devolvidas
     * e suas quantidade.*/

    public Map<String, Integer> devolverQuantidade(Map<String, Integer> mapItens);

    /** Método para remover uma peça do armazenamento. A peça é encontrada através do nome.
     *
     * @param nome String - nome da peça que deve ser removida.*/

    public void removerPeca(String nome);

}
