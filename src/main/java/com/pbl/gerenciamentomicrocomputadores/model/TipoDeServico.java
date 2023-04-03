package com.pbl.gerenciamentomicrocomputadores.model;

import java.util.ArrayList;
import java.util.List;

public class TipoDeServico {

    private String TipoDeTarefa;

    private List<String> listItens;

    public TipoDeServico () {

        this.listItens = new ArrayList<String>();

    }

    public void setTipoDeTarefa(String tipoDeTarefa) { TipoDeTarefa = tipoDeTarefa; }

    public String getTipoDeTarefa() { return TipoDeTarefa; }

    public void setListItens (String item) { this.listItens.add(item); }

    public List<String> getListItens() {

        List<String> listItens = new ArrayList<String>();

        for (String item: this.listItens) {

            listItens.add(item);
        }

        return listItens;
    }

}
