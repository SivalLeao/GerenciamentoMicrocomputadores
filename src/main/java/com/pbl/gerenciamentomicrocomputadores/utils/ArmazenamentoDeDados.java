package com.pbl.gerenciamentomicrocomputadores.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArmazenamentoDeDados {

    public static <T> void guardarDados(List<T> lista, String nomeArquivo, String nomePasta) {

        try {

            File diretorio = new File("dados salvos");
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
        catch ( java.io.FileNotFoundException e) {

            throw new RuntimeException("Arquivo não encontrado");
        }
        catch ( java.io.IOException e) {

            throw new RuntimeException("Erro ao acessar dados");
        }

    }

    public static <T> ArrayList<T> resgatarDados(String nomeArquivo, String nomePasta) {

        try {

            File diretorio = new File("dados salvos");
            String localDiretorio = diretorio.getAbsolutePath();
            File pasta = new File(localDiretorio + "/" + nomePasta);
            File arquivo = new File(pasta, nomeArquivo);

            if (!arquivo.exists()) {

                return new ArrayList<T>();
            }

            FileInputStream fis = new FileInputStream(arquivo);
            ObjectInputStream ois = new ObjectInputStream(fis);

            List<T> lista = (ArrayList<T>) ois.readObject();

            fis.close();
            ois.close();

            return (ArrayList<T>) lista;

        }
        catch ( java.io.IOException e) {

            return new ArrayList<T>();
        }
        catch ( java.lang.ClassNotFoundException e) {

            throw new RuntimeException("Classe não encontrada");
        }

    }

}
