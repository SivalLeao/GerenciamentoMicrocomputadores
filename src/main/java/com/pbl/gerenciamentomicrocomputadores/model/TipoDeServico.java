package com.pbl.gerenciamentomicrocomputadores.model;

import java.util.HashMap;
import java.util.Map;

public class TipoDeServico {

    private String TipoDeTarefa;

    private Map<String, Integer> mapItens;

    public TipoDeServico () {

        this.mapItens = new HashMap<String, Integer>();

    }

    public void setTipoDeTarefa (String tipoDeTarefa) { TipoDeTarefa = tipoDeTarefa; }

    public String getTipoDeTarefa () { return TipoDeTarefa; }

    public void setMapItens (String nomePeca, int quantidade) { this.mapItens.put(nomePeca, quantidade); }

    public Map<String, Integer> getMapItens () {

        Map<String, Integer> mapItens = new HashMap<String, Integer>();

        for (String nomeItem: this.mapItens.keySet()) {

            mapItens.put(nomeItem, this.mapItens.get(nomeItem));
        }

        return mapItens;
    }

}
