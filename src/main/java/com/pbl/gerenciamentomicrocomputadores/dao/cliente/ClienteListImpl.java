package com.pbl.gerenciamentomicrocomputadores.dao.cliente;

import java.util.ArrayList;
import java.util.List;

import com.pbl.gerenciamentomicrocomputadores.model.Cliente;


public class ClienteListImpl implements ClienteDAO {

    private List<Cliente> lista;

    private int id;

    public ClienteListImpl() {

        this.lista = new ArrayList<Cliente>();
        this.id = 1112;
    }

    @Override
    public void create (Cliente cliente) {

        cliente.setId(this.id);
        this.id += 10;
        lista.add(cliente);
    }

    @Override
    public Cliente findById (int id) {

        for (Cliente cliente: this.lista) {

            if (cliente.getId() == id) {

                return cliente;
            }
        }

        return null;
    }

    @Override
    public Cliente findByCpf (String cpf) {

        for (Cliente cliente: this.lista) {

            if ( cliente.getCpf().equals(cpf)) {

                return cliente;
            }
        }

        return null;
    }

    @Override
    public void update (Cliente cliente) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (this.lista.get(i).getId() == cliente.getId()) {

                this.lista.set(i, cliente);
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
    public List<Cliente> findMany () {

        List<Cliente> listCliente = new ArrayList<Cliente>();

        for ( Cliente cliente : this.lista) {

            listCliente.add(cliente);
        }

        return listCliente;
    }

    @Override
    public boolean checkId (int id) {

        for (Cliente cliente: this.lista) {

            if (cliente.getId() == id) {

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean checkCpf (String cpf) {

        for (Cliente cliente: this.lista) {

            if ( cliente.getCpf().equals(cpf)) {

                return true;
            }
        }

        return false;
    }

    @Override
    public void deleteMany () {

        this.lista = new ArrayList<>();
        this.id = 1112;
    }

}
