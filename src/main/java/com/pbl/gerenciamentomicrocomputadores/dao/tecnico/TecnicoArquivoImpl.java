package com.pbl.gerenciamentomicrocomputadores.dao.tecnico;

import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;
import com.pbl.gerenciamentomicrocomputadores.utils.ArmazenamentoDeDados;

import java.util.ArrayList;
import java.util.List;

public class TecnicoArquivoImpl implements TecnicoDAO{
    private List<Tecnico> lista;

    public TecnicoArquivoImpl() {

        this.lista = ArmazenamentoDeDados.resgatarDados("tecnico.dat","Tecnico");

    }

    @Override
    public void criar(Tecnico tecnico) {

        int id = 1111 + (lista.size() * 10);
        tecnico.setId(id);

        this.lista.add(tecnico);

        ArmazenamentoDeDados.guardarDados(lista, "tecnico.dat", "Tecnico");

    }

    @Override
    public Tecnico encontrarPorId(int id) {

        for (Tecnico tecnico: this.lista) {

            if (tecnico.getId() == id) {

                return tecnico;
            }
        }

        return null;
    }

    @Override
    public Tecnico encontrarPorCpf(String cpf) {

        for (Tecnico tecnico: this.lista) {

            if ( tecnico.getCpf().equals(cpf)) {

                return tecnico;
            }
        }

        return null;
    }

    @Override
    public void atualizar(Tecnico tecnico) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getId() == tecnico.getId()) {

                this.lista.set(i, tecnico);

                ArmazenamentoDeDados.guardarDados(lista, "tecnico.dat", "Tecnico");

                return;
            }
        }
    }

    @Override
    public void remover(int id) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getId() == id) {

                this.lista.remove(i);

                ArmazenamentoDeDados.guardarDados(lista, "tecnico.dat", "Tecnico");

                return;
            }
        }
    }

    @Override
    public List<Tecnico> encontrarTodos() {

        List<Tecnico> listTecnico = new ArrayList<Tecnico>();

        for ( Tecnico tecnico : this.lista) {

            listTecnico.add(tecnico);
        }

        return listTecnico;
    }

    @Override
    public boolean checarPorId(int id) {

        for (Tecnico tecnico: this.lista) {

            if (tecnico.getId() == id) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checarPorCpf(String cpf) {

        for (Tecnico tecnico: this.lista) {

            if ( tecnico.getCpf().equals(cpf)) {

                return true;
            }
        }

        return false;
    }

    @Override
    public void removerTodos() {

        this.lista.clear();
        ArmazenamentoDeDados.guardarDados(lista, "tecnico.dat", "Tecnico");
    }

}
