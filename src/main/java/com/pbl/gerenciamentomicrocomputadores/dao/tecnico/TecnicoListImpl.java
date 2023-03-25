package com.pbl.gerenciamentomicrocomputadores.dao.tecnico;

import java.util.ArrayList;
import java.util.List;

import com.pbl.gerenciamentomicrocomputadores.model.Tecnico;


public class TecnicoListImpl implements TecnicoDAO {

    private List<Tecnico> lista;

    private int id;

    public TecnicoListImpl() {

        this.lista = new ArrayList<Tecnico>();
        this.id = 1111;
    }

    @Override
    public void create (Tecnico tecnico) {

        tecnico.setId(this.id);
        this.id += 10;
        lista.add(tecnico);
    }

    @Override
    public Tecnico findById (int id) {

        for (Tecnico tecnico: this.lista) {

            if (tecnico.getId() == id) {

                return tecnico;
            }
        }

        return null;
    }

    @Override
    public Tecnico findByCpf (String cpf) {

        for (Tecnico tecnico: this.lista) {

            if ( tecnico.getCpf().equals(cpf)) {

                return tecnico;
            }
        }

        return null;
    }

    @Override
    public void update (Tecnico tecnico) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getId() == tecnico.getId()) {

                this.lista.set(i, tecnico);
                return;
            }
        }
    }

    @Override
    public void delete (int id) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getId() == id) {

                this.lista.remove(i);
                return;
            }
        }
    }

    @Override
    public List<Tecnico> findMany () {

        List<Tecnico> listTecnico = new ArrayList<Tecnico>();

        for ( Tecnico tecnico : this.lista) {

            listTecnico.add(tecnico);
        }

        return listTecnico;
    }

    @Override
    public boolean checkId (int id) {

        for (Tecnico tecnico: this.lista) {

            if (tecnico.getId() == id) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkCpf (String cpf) {

        for (Tecnico tecnico: this.lista) {

            if ( tecnico.getCpf().equals(cpf)) {

                return true;
            }
        }

        return false;
    }

    @Override
    public void deleteMany () {

        this.lista = new ArrayList<>();
        this.id = 1111;
    }

}
