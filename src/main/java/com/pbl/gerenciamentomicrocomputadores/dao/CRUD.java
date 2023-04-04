package com.pbl.gerenciamentomicrocomputadores.dao;

import java.util.List;

public interface CRUD<T>  {

    public void create (T obj);

    public T findById (int id);

    public void update (T obj);

    public void delete (int id);

    public List<T> findMany ();

    public boolean checkById (int id);

    public void deleteMany ();

}
