package com.pbl.gerenciamentomicrocomputadores.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/** É responsável por criar os diretórios e os arquivos binários que são utilizados para armazenar
 * todos os dados do sistema, evitando a perda de informações.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 3.0.
 */

public class ArmazenamentoDeDados {

    /** Método responsável por guardar as informações do sistema em um arquivo binário.
     *
     * @param lista List - lista de objetos.
     * @param nomeArquivo String - nome do arquivo.
     * @param nomePasta String - nome da pasta.*/

    public static <T> void guardarDados(List<T> lista, String nomeArquivo, String nomePasta) {

        try {

            File diretorio = new File("dados salvos/" + nomePasta);
            diretorio.mkdirs();

            File arquivo = new File(diretorio.getAbsolutePath(), nomeArquivo);

            FileOutputStream fos = new FileOutputStream(arquivo, false);
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

    /** Método responsável por resgatar as informações do sistema em um arquivo binário.
     *
     * @param nomeArquivo String - nome do arquivo.
     * @param nomePasta String - nome da pasta.
     * @return List - lista de objetos.*/

    public static <T> ArrayList<T> resgatarDados(String nomeArquivo, String nomePasta) {

        try {

            File diretorio = new File("dados salvos/" + nomePasta);
            File arquivo = new File(diretorio.getAbsolutePath(), nomeArquivo);

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
