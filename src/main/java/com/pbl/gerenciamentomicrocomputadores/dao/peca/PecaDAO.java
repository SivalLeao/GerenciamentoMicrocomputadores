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

    public void removeQuantity (String nome, int quantidade);

    /** Método para adicionar determinada quantidade de uma peça específica. A peça é identificada
     * pelo nome.
     *
     * @param nome String - nome da peça.
     * @param quantidade int - quantidade que deve ser adicionada da peça.*/

    public void addQuantity (String nome, int quantidade);

    /** Método de retorno de todo o armazenamento de peças do sistema. Os dados são compactados em
     * uma estrutura do tipo HashMap que tem o nome da peça como chave e o objeto do tipo Peca como
     * conteúdo.
     *
     * @return Map<String, Peca> - estrutura com todas as peças do sistema armazenadas.*/

    public Map<String, Peca> findFullMap ();

    /** Método de retorno de uma peça através da busca por nome.
     *
     * @param nome String - nome da peça.
     * @return Peca - peça encontrada após a busca por nome.*/

    public Peca findByName (String nome);

    /** Método para checar se uma peça está armazenada no sistema através da busca por nome.
     *
     * @param nome String - nome da peça.
     * @return boolean - resultado da busca pela peça. Se foi achada ou não.*/

    boolean checkByName (String nome);

    /** Método para checar se um tipo de peça tem quantidade suficiente para realizar
     * determinada ordem de serviço de montagem.
     *
     * @param nome String - nome da peça.
     * @param quantidade int - quantidade que o serviço precisa utilizar da peça.
     * @return boolean - resultado da checagem de quantidade. Se a peça possui a quantidade suficiente
     * ou não.*/

    public boolean checkQuantity (String nome, int quantidade);

    /** Método para encontrar as peças do sistema que estão com uma baixa quantidade. Com o objetivo
     * de alertar o usuário.
     *
     * @return List<Peca> - lista de peças com baixa quantidade.*/

    public List<Peca> quantityAlert ();

    /** Método para devolver a quantidade retirada das peças para a realização de uma
     * ordem de serviço. Utilizado quando uma ordem de serviço é cancelada e as peças precisam ser
     * adicionadas novamente. Caso algumas peças não possam ser retornadas ao sistema, uma estrutura
     * listando elas é retornada pelo método.
     *
     * @param mapItens Map<String,Integer> - estrutura com o nome das peças e suas quantidades
     *                 que devem ser devolvidas.
     * @return Map<String,Integer> - estrutura com os nomes as peças que não puderam ser devolvidas
     * e suas quantidade.*/

    public Map<String, Integer> refundQuantity (Map<String, Integer> mapItens);

    /** Método para remover uma peça do armazenamento. A peça é encontrada através do nome.
     *
     * @param nome String - nome da peça que deve ser removida.*/

    public void removePeca (String nome);

}
