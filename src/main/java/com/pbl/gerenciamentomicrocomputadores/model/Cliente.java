package com.pbl.gerenciamentomicrocomputadores.model;

/**  Classe para objetos do tipo Cliente. É uma extensão da classe abstrata Pessoa, herdando
 *  seus atributos, juntamente com os setters e getters.
 *
 * @author Silvio Oliveira
 * @author Sival Leão
 * @version 1.0.
 */
public class Cliente extends Pessoa {

    /** Construtor que recebe como parâmetros a maioria dos atributos da classe para inseri-las
     * diretamente. A inserção é feita chamando o construtor da classe pai através da função super().
     *
     * @param nome String - nome do clente.
     * @param endereco String - endereço do cliente.
     * @param telefone String - telefone do cliente.
     * @param cpf String - CPF do cliente.*/

    public Cliente (String nome, String endereco, String telefone, String cpf) {

        super( nome, endereco, telefone, cpf);
    }

}
