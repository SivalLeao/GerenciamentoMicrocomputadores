package com.pbl.gerenciamentomicrocomputadores.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArmazenamentoDeDados {
    public static <T> void guardarDados (List<T> lista, String nomeArquivo, String nomePasta) throws IOException {
        File diretorio = new File("dados salvo");
        diretorio.mkdir();

        String localDiretorio = diretorio.getAbsolutePath();

        File pasta = new File(localDiretorio +"/"+ nomePasta);
        pasta.mkdir();

        File arquivo = new File(pasta, nomeArquivo);
        FileOutputStream fos = new FileOutputStream(arquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(lista);

        fos.close();
        oos.close();
    }
    public static <T> List resgatarDados(String nomeArquivo, String nomePasta) throws IOException, ClassNotFoundException {
        File diretorio = new File("dados salvo");
        String localDiretorio = diretorio.getAbsolutePath();
        File pasta = new File(localDiretorio +"/"+ nomePasta);
        File arquivo = new File(pasta, nomeArquivo);

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
