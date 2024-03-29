package com.pbl.gerenciamentomicrocomputadores.model;

import java.io.Serializable;

/**  Classe para objetos do tipo Técnico. É uma extensão da classe abstrata Pessoa, herdando
 *  seus atributos, juntamente com os setters e getters.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */

public class Tecnico extends Pessoa implements Serializable {

    /** Construtor que recebe como parâmetros a maioria dos atributos da classe para inseri-las
     * diretamente. A inserção é feita chamando o construtor da classe pai através da função super().
     *
     * @param nome String - nome do técnico.
     * @param endereco String - endereço do técnico.
     * @param telefone String - telefone do técnico.
     * @param cpf String - CPF do técnico.*/

    public Tecnico (String nome, String endereco, String telefone, String cpf) {

        super( nome, endereco, telefone, cpf);
    }

}
