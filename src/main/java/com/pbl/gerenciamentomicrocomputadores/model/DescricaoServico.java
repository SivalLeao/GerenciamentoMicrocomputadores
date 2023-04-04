package com.pbl.gerenciamentomicrocomputadores.model;

import java.util.HashMap;
import java.util.Map;

public class DescricaoServico {

    private String tipoDeServico;

    private Map<String, Integer> mapItens;

    public DescricaoServico() {

        this.mapItens = new HashMap<String, Integer>();

    }

    public void setTipoDeServico(String tipoDeServico) { this.tipoDeServico = tipoDeServico; }

    public String getTipoDeServico() { return this.tipoDeServico; }

    public void setMapItens (String nomePeca, int quantidade) { this.mapItens.put(nomePeca, quantidade); }

    public Map<String, Integer> getMapItens () {

        Map<String, Integer> mapItens = new HashMap<String, Integer>();

        for (String nomeItem: this.mapItens.keySet()) {

            mapItens.put(nomeItem, this.mapItens.get(nomeItem));
        }

        return mapItens;
    }

}
