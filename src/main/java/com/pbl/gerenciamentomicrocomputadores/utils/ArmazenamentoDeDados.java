package com.pbl.gerenciamentomicrocomputadores.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArmazenamentoDeDados {
    public static <T> void guardarDados (List<T> lista, String nomeArquivo) throws IOException {
        File diretorio = new File("dados salvo");
        diretorio.mkdir();

        File arquivo = new File(diretorio, nomeArquivo);
        FileOutputStream fos = new FileOutputStream(arquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(lista);

        fos.close();
        oos.close();
    }
    public static <T> List resgatarDados(String nomeArquivo) throws IOException, ClassNotFoundException {
        File direttorio = new File("dados salvo");
        File arquivo = new File(direttorio, nomeArquivo);

        if (!arquivo.exists()){
            return new ArrayList<T>();
        }
        FileInputStream fis = new FileInputStream(arquivo);
        ObjectInputStream ois = new ObjectInputStream(fis);

        List<T> lista = (List<T>) ois.readObject();

        fis.close();
        ois.close();

        return lista;
    }

}
