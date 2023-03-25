package com.pbl.gerenciamentomicrocomputadores.model;

public abstract class Pessoa {

    private String nome;

    private String endereco;

    private String telefone;

    private String cpf;

    private int id;

    public void setNome(String nome) { this.nome = nome; }

    public String getNome() { return nome; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getEndereco() { return endereco; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getTelefone() { return telefone; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCpf() { return cpf; }

    public void setId (int id) { this.id = id; }

    public int getId() { return id; }

    public boolean validarNome (String nome) {

        if ( (nome.matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$"))
                && (nome.replaceAll("\\s+", "").length() >= 3)) {

            return true;
        }

        return false;
    }

    public boolean validarEndereco (String endereco) {

        if ( (endereco.replaceAll("\\s+", "").length() >= 5)) {

            return true;
        }

        return false;
    }

    public boolean validarTelefone (String telefone) {

        if ( (telefone.matches("^[0-9 ]+$")) &&
                (telefone.replaceAll("\\s+", "").length() == 11)) {

            return true;
        }

        return false;
    }

    public boolean validarCpf (String cpf) {

        if ( (cpf.matches("^[0-9 ]+$")) &&
                (cpf.replaceAll("\\s+", "").length() == 11)) {

            return true;
        }

        return false;
    }

}
