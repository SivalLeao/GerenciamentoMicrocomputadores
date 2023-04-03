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

    public void setTelefone(String telefone) {
        String Telefone = telefone.replaceAll("\\s+|\\(+|\\)+|-+", "");
        String telefoneFormatado = "(" + Telefone.substring(0, 2) + ") " + Telefone.substring(2, 7) + "-" + Telefone.substring(7);
        this.telefone = telefoneFormatado;
    }

    public String getTelefone() { return telefone; }

    public void setCpf(String cpf) {
        String Cpf = cpf.replaceAll("\\s+|\\.|-+", "");
        String CpfFormatado = Cpf.substring(0,3) + "." + Cpf.substring(3,6) + "." + Cpf.substring(6,9) + "-" + Cpf.substring(9);
        this.cpf = CpfFormatado;
    }

    public String getCpf() {
        return cpf;
    }

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

        if ( (endereco.replaceAll("\\s+", "").length() >= 3)) {

            return true;
        }

        return false;
    }

    public boolean validarTelefone (String telefone) {

        if ( (telefone.matches("^[0-9() -]+$")) &&
                (telefone.replaceAll("\\s+|\\(+|\\)+|-+", "").length() == 11)) {

            return true;
        }

        return false;
    }

    public boolean validarCpf (String cpf) {

        if ( (cpf.matches("^[0-9 .-]+$")) &&
                (cpf.replaceAll("\\s+|\\.+|-+", "").length() == 11)) {

            return true;
        }

        return false;
    }

}
