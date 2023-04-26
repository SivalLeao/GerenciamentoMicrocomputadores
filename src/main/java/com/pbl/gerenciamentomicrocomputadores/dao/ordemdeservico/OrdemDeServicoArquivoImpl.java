package com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico;

import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;
import com.pbl.gerenciamentomicrocomputadores.utils.ArmazenamentoDeDados;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrdemDeServicoArquivoImpl implements OrdemDeServicoDAO {

    private List<OrdemDeServico> lista;
    private String nomeArquivo = "ordemdeservico.dat";
    private String nomePasta = "Ordem De Servico";

    public OrdemDeServicoArquivoImpl () {

        this.lista =  ArmazenamentoDeDados.resgatarDados(nomeArquivo,nomePasta);
    }

    @Override
    public void criar(OrdemDeServico ordemDeServico) {

        int id = 1113 + (lista.size() * 10);
        ordemDeServico.setIdOrdem(id);
        ordemDeServico.setStatusAtual("Em espera");

        this.lista.add(ordemDeServico);

        ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);
    }

    @Override
    public OrdemDeServico encontrarPorId(int idOrdem) {

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdOrdem() == idOrdem) {

                return ordemDeServico;
            }
        }

        return null;
    }

    @Override
    public void atualizar(OrdemDeServico ordemDeServico) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getIdOrdem() == ordemDeServico.getIdOrdem()) {

                this.lista.set(i, ordemDeServico);

                ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);

                return;
            }
        }
    }

    public Map<String, Integer> atualizarStatus(int idOrdem, String status) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getIdOrdem() == idOrdem) {

                this.lista.get(i).setStatusAtual(status);

                ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);

                if (status.equals("Finalizado")) {

                    this.lista.get(i).getData().setDataFim(LocalDateTime.now());

                    ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);

                    return null;
                }
                else if (status.equals("Cancelado")) {

                    OrdemDeServico ordemDeServico = encontrarPorId(idOrdem);

                    return ordemDeServico.getDescricaoServico().getMapItens();
                }
            }
        }

        return null;
    }

    @Override
    public void remover(int idOrdem) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getIdOrdem() == idOrdem) {

                this.lista.remove(i);

                ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);

                return;
            }
        }
    }

    @Override
    public List<OrdemDeServico> encontrarTodos() {

        List<OrdemDeServico> listOrdemDeServico = new ArrayList<OrdemDeServico>();

        for (OrdemDeServico ordemDeServico: this.lista) {

            listOrdemDeServico.add(ordemDeServico);
        }

        return listOrdemDeServico;
    }

    @Override
    public List<OrdemDeServico> encontrarPorIdTecnico(int idTecnico) {

        List<OrdemDeServico> listOrdemDeServico = new ArrayList<OrdemDeServico>();

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdTecnico() == idTecnico) {

                listOrdemDeServico.add(ordemDeServico);
            }
        }

        return listOrdemDeServico;
    }

    @Override
    public List<OrdemDeServico> listaEmAbertoTecnico(int idTecnico) {

        List<OrdemDeServico> listOrdemDeServico = new ArrayList<OrdemDeServico>();

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdTecnico() == idTecnico) {

                if (ordemDeServico.getStatusAtual().equals("Em andamento") || ordemDeServico.getStatusAtual().equals("Em espera")) {

                    listOrdemDeServico.add(ordemDeServico);
                }
            }
        }

        return listOrdemDeServico;
    }

    @Override
    public boolean checarPorId(int idOrdem) {

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdOrdem() == idOrdem) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checarStatusEmAndamento(int idTecnico) {

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdTecnico() == idTecnico && ordemDeServico.getStatusAtual().equals("Em andamento")) {

                return true;
            }
        }

        return false;
    }

    @Override
    public void removerTodos() {

        this.lista.clear();

        ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);
    }
    public void diretorioTest() {
        this.nomeArquivo = "ordemdeservicoTest.dat";
        this.nomePasta = "Test Ordem De Servico";
    }

    @Override
    public void diretorioPadrao() {
        this.nomeArquivo = "ordemdeservico.dat";
        this.nomePasta = "Ordem De Servico";
    }

}
