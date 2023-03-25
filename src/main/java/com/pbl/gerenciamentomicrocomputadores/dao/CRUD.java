package com.pbl.gerenciamentomicrocomputadores.dao;

import java.util.List;

public interface CRUD<T>  {

    public void create (T obj);

    public T findById (int id);

    public T findByCpf (String cpf);

    public void update (T obj);

    public void delete (int id);

    public List<T> findMany ();

    public boolean checkId (int id);

    public boolean checkCpf (String cpf);

    public void deleteMany ();

}
