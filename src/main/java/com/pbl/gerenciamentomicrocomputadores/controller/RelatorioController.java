package com.pbl.gerenciamentomicrocomputadores.controller;

import com.pbl.gerenciamentomicrocomputadores.dao.DAO;
import com.pbl.gerenciamentomicrocomputadores.model.Cliente;
import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import com.pbl.gerenciamentomicrocomputadores.model.Peca;
import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.format.DateTimeFormatter;
import java.util.Map;

public class RelatorioController {

    @FXML private Label relatorioTexto;

    private int idOrdem;

    public void atualizarRelatorio () {

        String texto = "";
        String tipoDeServico = "";
        String statusAtual = "";
        String tempoDeEspera = "";
        String satisfacao = "";
        OrdemDeServico ordemDeServico = DAO.getOrdemDeServico().encontrarPorId(idOrdem);
        Cliente cliente = DAO.getCliente().encontrarPorId(ordemDeServico.getIdCliente());

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (ordemDeServico.getDescricaoServico().getTipoDeServico().equals("Montagem/Instalação")) {

            tipoDeServico = "Montagem/instalação de peças\n Peças solicitadas:\n";

            Map<String,Integer> pecasUsadas = ordemDeServico.getDescricaoServico().getMapItens();
            Map<String,Peca> estoque = DAO.getPeca().encontrarTodosMap();

            for (String nomePeca: pecasUsadas.keySet()) {

                Peca peca = estoque.get(nomePeca);

                tipoDeServico = tipoDeServico.concat("\n    " + nomePeca);
                tipoDeServico = tipoDeServico.concat("\n    Quantidade: " + pecasUsadas.get(nomePeca));
                tipoDeServico = tipoDeServico.concat("\n    Valor: R$ " + peca.getValor());
                tipoDeServico = tipoDeServico.concat("\n    Custo: R$ " + peca.getCusto() + "\n");
            }

        }
        else if (ordemDeServico.getDescricaoServico().getTipoDeServico().equals("Sistema operacional")) {

            tipoDeServico = "Instalação de sistema operacional";
        }
        else if (ordemDeServico.getDescricaoServico().getTipoDeServico().equals("Programas")) {

            tipoDeServico = "Instalação de programas";
        }
        else if (ordemDeServico.getDescricaoServico().getTipoDeServico().equals("Limpeza")) {

            tipoDeServico = "Limpeza de máquina";
        }

        if (ordemDeServico.getStatusAtual().equals("Em espera")) {

            statusAtual = "O serviço se encontra na fila de espera para ser executado";
        }
        else if (ordemDeServico.getStatusAtual().equals("Em andamento")) {

            Tecnico tecnico = DAO.getTecnico().encontrarPorId(ordemDeServico.getIdTecnico());

            statusAtual = "O serviço está sendo realizado por " + tecnico.getNome();
        }
        else if (ordemDeServico.getStatusAtual().equals("Cancelado")) {

            statusAtual = "O serviço foi cancelado";
        }
        else if (ordemDeServico.getStatusAtual().equals("Finalizado")) {

            Tecnico tecnico = DAO.getTecnico().encontrarPorId(ordemDeServico.getIdTecnico());

            statusAtual = "O serviço foi finalizado por " + tecnico.getNome() + " em "
                    + ordemDeServico.getData().getDataFim().format(formatador);

            tempoDeEspera = Long.toString(ordemDeServico.calcularTempoDeServico());
            satisfacao = ordemDeServico.getSatisfacaoCliente();
        }

        texto = "\n Cliente: " + cliente.getNome() + "\n ID serviço: " + idOrdem
                + "\n Tipo de serviço: " + tipoDeServico + "\n Valor total: R$ " +
                ordemDeServico.calcularValorServico(DAO.getPeca().encontrarTodosMap())
                + "\n Data de pedido: " + ordemDeServico.getData().getDataInicio().format(formatador)
                + "\n Status atual: " + statusAtual;

        if (! (tempoDeEspera.equals(""))) {

            texto = texto.concat("\n Tempo de espera: " + tempoDeEspera + " segundos");
        }
        if (! (satisfacao.equals(""))) {

            texto = texto.concat("\n Satisfação do cliente: " + satisfacao);
        }

        relatorioTexto.setText(texto + "\n");

    }

    public void setIdOrdem (int idOrdem) { this.idOrdem = idOrdem; }

}
