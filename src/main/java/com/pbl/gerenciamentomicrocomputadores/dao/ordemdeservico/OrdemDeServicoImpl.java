package com.pbl.gerenciamentomicrocomputadores.dao.ordemdeservico;

import com.pbl.gerenciamentomicrocomputadores.model.OrdemDeServico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrdemDeServicoImpl implements OrdemDeServicoDAO {

    private List<OrdemDeServico> lista;

    private int id;

    public OrdemDeServicoImpl () {

        this.lista = new ArrayList<OrdemDeServico>();
        this.id = 1113;
    }


    @Override
    public void create (OrdemDeServico ordemDeServico) {

        ordemDeServico.setIdOrdem(this.id);
        ordemDeServico.setStatusAtual("Em espera");
        this.id += 10;
        this.lista.add(ordemDeServico);
    }

    @Override
    public OrdemDeServico findById (int idOrdem) {

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdOrdem() == idOrdem) {

                return ordemDeServico;
            }
        }

        return null;
    }

    @Override
    public void update (OrdemDeServico ordemDeServico) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getIdOrdem() == ordemDeServico.getIdOrdem()) {

                this.lista.set(i, ordemDeServico);
                return;
            }
        }
    }

    public Map<String, Integer> updateStatus (int idOrdem, String status) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getIdOrdem() == idOrdem) {

                this.lista.get(i).setStatusAtual(status);

                if (status.equals("Finalizado")) {

                    this.lista.get(i).getData().setDataFim(LocalDateTime.now());
                    return null;
                }
                else if (status.equals("Cancelado")) {

                    OrdemDeServico ordemDeServico = findById(idOrdem);

                    return ordemDeServico.getDescricaoServico().getMapItens();
                }
            }
        }

        return null;
    }

    @Override
    public void delete (int idOrdem) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getIdOrdem() == idOrdem) {

                this.lista.remove(i);
                return;
            }
        }
    }

    @Override
    public List<OrdemDeServico> findMany () {

        List<OrdemDeServico> listOrdemDeServico = new ArrayList<OrdemDeServico>();

        for (OrdemDeServico ordemDeServico: this.lista) {

            listOrdemDeServico.add(ordemDeServico);
        }

        return listOrdemDeServico;
    }

    @Override
    public List<OrdemDeServico> findByIdTecnico (int idTecnico) {

        List<OrdemDeServico> listOrdemDeServico = new ArrayList<OrdemDeServico>();

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdTecnico() == idTecnico) {

                listOrdemDeServico.add(ordemDeServico);
            }
        }

        return listOrdemDeServico;
    }

    @Override
    public List<OrdemDeServico> openListTecnico (int idTecnico) {

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
    public boolean checkById (int idOrdem) {

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdOrdem() == idOrdem) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkStatus (int idTecnico) {

        for (OrdemDeServico ordemDeServico: this.lista) {

            if (ordemDeServico.getIdTecnico() == idTecnico && ordemDeServico.getStatusAtual().equals("Em andamento")) {

                return true;
            }
        }

        return false;
    }

    @Override
    public void deleteMany () {

        this.lista = new ArrayList<OrdemDeServico>();
        this.id = 1113;
    }

}
