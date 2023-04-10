package com.pbl.gerenciamentomicrocomputadores.model;

/** Classe abstrata para as classes Cliente e Tecnico. Inclui a declaração de atributos, métodos de inserção
 * e retorno, e métodos de validação de dados.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */

public abstract class Pessoa {

    private String nome;
    private String endereco;
    private String telefone;
    private String cpf;
    private int id;

    /** Construtor que recebe como parâmetros a maioria dos atributos da classe para inseri-las
     * diretamente.
     *
     * @param nome String - nome do clente.
     * @param endereco String - endereço do cliente.
     * @param telefone String - telefone do cliente.
     * @param cpf String - CPF do cliente.*/

    public Pessoa (String nome, String endereco, String telefone, String cpf) {

        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
    }


    /** Método para inserir o dado no atributo nome.
     *
     * @param nome String - nome da pessoa.*/

    public void setNome (String nome) { this.nome = nome; }

    /** Método de retorno do nome da pessoa.
     *
     * @return String - nome da pessoa.*/

    public String getNome() { return nome; }

    /** Método para inserir o dado no atributo endereco.
     *
     * @param endereco String - endereço da pessoa.*/

    public void setEndereco(String endereco) { this.endereco = endereco; }

    /** Método de retorno do endereço da pessoa.
     *
     * @return String - endereço da pessoa.*/

    public String getEndereco() { return endereco; }

    /** Método para inserir o dado no atributo telefone. A informação é convertida para uma melhor forma de
     * armazenamento antes de ser inserida no atributo.
     *
     * @param telefone String - telefone da pessoa.*/

    public void setTelefone(String telefone) {
        String Telefone = telefone.replaceAll("\\s+|\\(+|\\)+|-+", "");
        String telefoneFormatado = "(" + Telefone.substring(0, 2) + ") " + Telefone.substring(2, 7) + "-" + Telefone.substring(7);
        this.telefone = telefoneFormatado;
    }

    /** Método de retorno do número de telefone da pessoa.
     *
     * @return String - telefone da pessoa.*/

    public String getTelefone() { return telefone; }

    /** Método para inserir o dado no atributo cpf. A informação é convertida para uma melhor forma de
     * armazenamento antes de ser inserida no atributo.
     *
     * @param cpf String - CPF da pessoa.*/

    public void setCpf(String cpf) {
        String Cpf = cpf.replaceAll("\\s+|\\.|-+", "");
        String CpfFormatado = Cpf.substring(0,3) + "." + Cpf.substring(3,6) + "." + Cpf.substring(6,9) + "-" + Cpf.substring(9);
        this.cpf = CpfFormatado;
    }

    /** Método de retorno do CPF da pessoa.
     *
     * @return String - CPF da pessoa.*/

    public String getCpf() {
        return cpf;
    }

    /** Método para inserir o dado no atributo id.
     *
     * @param id int - ID da pessoa.*/

    public void setId (int id) { this.id = id; }

    /** Método de retorno do ID da pessoa.
     *
     * @return int - ID da pessoa.*/

    public int getId() { return id; }

    /** Método para validar o nome inserido pelo usuário. Retorna o boolean true se for um dado válido,
     * e false, caso não seja. Checa se o nome só tem letras e espaços, e possui três ou mais caracteres.
     *
     * @param nome String - nome inserido pelo usuário.
     * @return boolean - resultado da checagem.*/

    public boolean validarNome (String nome) {

        if ( (nome.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$"))
                && (nome.replaceAll("\\s+", "").length() >= 3)) {

            return true;
        }

        return false;
    }

    /** Método para validar o endereço inserido pelo usuário. Retorna o boolean true se for um dado válido,
     * e false, caso não seja. Checa se possui três ou mais caracteres.
     *
     * @param endereco String - endereço inserido pelo usuário.
     * @return boolean - resultado da checagem.*/

    public boolean validarEndereco (String endereco) {

        if ( (endereco.replaceAll("\\s+", "").length() >= 3)) {

            return true;
        }

        return false;
    }

    /** Método para validar o número de telefone inserido pelo usuário. Retorna o boolean true se for um dado válido,
     * e false, caso não seja. Checa se possui 11 números, tirando outros símbolos.
     *
     * @param telefone String - número de telefone inserido pelo usuário.
     * @return boolean - resultado da checagem.*/

    public boolean validarTelefone (String telefone) {

        if ( (telefone.matches("^[0-9() -]+$")) &&
                (telefone.replaceAll("\\s+|\\(+|\\)+|-+", "").length() == 11)) {

            return true;
        }

        return false;
    }

    /** Método para validar o CPF inserido pelo usuário. Retorna o boolean true se for um dado válido,
     * e false, caso não seja. Checa se possui 11 números, tirando outros símbolos.
     *
     * @param cpf String - CPF inserido pelo usuário.
     * @return boolean - resultado da checagem.*/

    public boolean validarCpf (String cpf) {

        if ( (cpf.matches("^[0-9 .-]+$")) &&
                (cpf.replaceAll("\\s+|\\.+|-+", "").length() == 11)) {

            return true;
        }

        return false;
    }

    /** Método de impressão dos dados cadastrais de uma pessoa.
     *
     * @return String - texto formatado contendo dados de cadastro de uma pessoa
     */
    public String imprimirPessoa() {
        return String.format(
                """
                 NOME: %s  
                 CPF: %s           TELEFONE: %s      ID: %d
                 ENDEREÇO: %s       
                """, this.nome, this.cpf, this.telefone, this.id, this.endereco);
    }

}
