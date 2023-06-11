package com.pbl.gerenciamentomicrocomputadores.dao.peca;

import com.pbl.gerenciamentomicrocomputadores.model.Peca;
import com.pbl.gerenciamentomicrocomputadores.utils.ArmazenamentoDeDados;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** É responsável por armazenar todos as peças do sistema em um arquivo binário com extensão ".dat", e estruturar
 * os métodos necessários para inserir, consultar, alterar ou remover. Implementa a interface PecaDAO.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 3.0.
 */

public class PecaArquivoImpl implements PecaDAO {

    private List<Peca> lista;
    private String nomeArquivo = "peca.dat";
    private String nomePasta = "Peca";

    /** Resgata uma lista contendo os dados das peças cadastradas no sistema que foram previamente salvos
     * em um arquivo binário. Caso não haja nenhum cadastro, o construtor retorna uma lista vazia e as peças
     * básicas são inseridas. Todos os nomes das peças são colocados na estrutura com letras minúsculas e sem
     * acentos.*/

    public PecaArquivoImpl () {

        this.lista = new ArrayList<Peca>();
        this.lista = ArmazenamentoDeDados.resgatarDados(nomeArquivo,nomePasta);

        if (lista.isEmpty()){
            Peca peca0 = new Peca("ram", 0, 20, 20);
            this.lista.add(peca0);

            Peca peca1 = new Peca("placa mae", 0, 100, 100);
            this.lista.add(peca1);

            Peca peca2 = new Peca("fonte", 0, 30, 30);
            this.lista.add(peca2);

            Peca peca3 = new Peca("placa de video", 0, 100, 100);
            this.lista.add(peca3);

            Peca peca4 = new Peca("hd", 0, 30, 30);
            this.lista.add(peca4);

            Peca peca5 = new Peca("ssd", 0, 30, 30);
            this.lista.add(peca5);
        }

    }

    /** Método para adicionar um objeto do tipo Peca na lista de armazenamento e em seguida é salvo em um arquivo
     * binário.
     *
     * @param peca Peca - peça que deve ser inserida.*/

    @Override
    public void criar(Peca peca) {

        lista.add(peca);

        ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);

    }

    /** Método para atualizar as informações de uma peça na lista e no arquivo. O nome é utilizado para encontrar
     * o objeto antigo que representava a peça, substituindo-o pelo novo objeto com as informações atualizadas.
     *
     * @param peca Peca - peça que deve ser atualizada.*/

    @Override
    public void atualizar(Peca peca) {

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getNome().equals(peca.getNome())) {

                this.lista.set(i, peca);

                ArmazenamentoDeDados.guardarDados(lista, nomeArquivo, nomePasta);

                return;
            }
        }
    }

    /** Método para remover determinada quantidade de uma peça específica. A peça é identificada
     * pelo nome. Antes de fazer as alterações na quantidade, as letras do nome são convertidas para
     * minúsculo e os acentos são retirados, para que a busca possa ser feita. O método checarQuantidade
     * deve ser usado antes de chamar removerQuantidade para conferir se a quantidade pode ser retirada sem
     * ultrapassar o limite.
     *
     * @param nome String - nome da peça.
     * @param quantidade int - quantidade que deve ser retirada da peça.*/

    @Override
    public void removerQuantidade(String nome, int quantidade) {

        int novaQuantidade;

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getNome().equals(nomeFormatado)) {

                novaQuantidade = this.lista.get(i).getQuantidade() - quantidade;
                this.lista.get(i).setQuantidade(novaQuantidade);

                ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);

                return;
            }
        }
    }

    /** Método para adicionar determinada quantidade de uma peça específica. A peça é identificada
     * pelo nome. Antes de fazer as alterações na quantidade,  as letras do nome são convertidas
     * para minúsculo e seus acentos são retirados, para que a busca possa ser feita.
     *
     * @param nome String - nome da peça.
     * @param quantidade int - quantidade que deve ser adicionada da peça.*/

    @Override
    public void adicionarQuantidade(String nome, int quantidade){

        int novaQuantidade;

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getNome().equals(nomeFormatado)) {

                novaQuantidade = this.lista.get(i).getQuantidade() + quantidade;
                this.lista.get(i).setQuantidade(novaQuantidade);

                ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);

                return;
            }
        }
    }

    /** Método de retorno de todas as peças armazenadas no sistema.
     *
     * @return List - lista de peças do sistema*/

    @Override
    public List<Peca> encontrarTodos() {

        List<Peca> listPeca = new ArrayList<Peca>();

        for (Peca peca: this.lista) {

            listPeca.add(peca);
        }

        return listPeca;
    }

    /** Método de retorno das peças do sistema na forma de HashMap. O nome da peça é a chave e o objeto
     * do tipo Peca é o conteúdo.
     * conteúdo.
     *
     * @return Map - estrutura HashMap com todas as peças do sistema armazenadas.*/

    public Map<String,Peca> encontrarTodosMap() {

        Map<String,Peca> mapPeca = new HashMap<String,Peca>();

        for (Peca peca: this.lista) {

            mapPeca.put(peca.getNome(), peca);
        }

        return mapPeca;
    }

    /** Método de retorno de uma peça através da busca por nome. Antes de realizar a busca, as letras do
     * nome são convertidas para minúsculo e seus acentos são retirados.
     *
     * @param nome String - nome da peça.
     * @return Peca - peça encontrada após a busca por nome.*/

    @Override
    public Peca encontrarPorNome(String nome) {

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        for (Peca peca: this.lista) {

            if ( peca.getNome().equals(nomeFormatado)) {

                return peca;
            }
        }

        return null;
    }

    /** Método para checar se uma peça está armazenada no sistema através da busca por nome. Antes de
     * realizar a busca, as letras do nome são convertidas para minúsculo e seus acentos são retirados.
     *
     * @param nome String - nome da peça.
     * @return boolean - resultado da busca pela peça. Se foi achada ou não.*/

    @Override
    public boolean checarPorNome(String nome){

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        for (Peca peca: this.lista) {

            if ( peca.getNome().equals(nomeFormatado)) {

                return true;
            }
        }

        return false;
    }

    /** Método para checar se um tipo de peça tem quantidade suficiente para realizar
     * determinada ordem de serviço de montagem. Antes de fazer a checagem, as letras do nome
     * são convertidas para minúsculo e seus acentos são retirados, para que as informações
     * possam ser encontradas.
     *
     * @param nome String - nome da peça.
     * @param quantidade int - quantidade que o serviço precisa utilizar da peça.
     * @return boolean - resultado da checagem de quantidade. Se a peça possui a quantidade suficiente
     * ou não.*/

    public boolean checarQuantidade(String nome, int quantidade) {

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        for (Peca peca: this.lista) {

            if ( peca.getNome().equals(nomeFormatado)) {

                if ( peca.getQuantidade() >= quantidade) {

                    return true;
                }
                else {
                    return false;
                }
            }
        }

        return false;
    }

    /** Método para encontrar as peças do sistema que estão com uma baixa quantidade. O valor de alerta é 5.
     * Se uma peça tiver quantidade igual ou menor ao valor de alerta, ela é adicionada na lista de retorno.
     *
     * @return List - lista de peças com baixa quantidade.*/

    public List<Peca> alertaDeQuantidade() {

        List<Peca> listPeca = new ArrayList<Peca>();

        for (Peca peca: this.lista) {

            if ( peca.getQuantidade() <= 5) {

                listPeca.add(peca);
            }
        }

        return listPeca;
    }

    /** Método para devolver a quantidade retirada das peças para a realização de uma
     * ordem de serviço. Utilizado quando uma ordem de serviço é cancelada e as peças precisam ser
     * adicionadas novamente. Caso algumas peças não possam ser retornadas ao sistema, uma lista
     * contendo elas é retornada.
     *
     * @param mapItens Map - estrutura com o nome das peças e suas quantidades que devem ser devolvidas.
     * @return Map - estrutura com os nomes as peças que não puderam ser devolvidas e suas quantidades.*/

    public Map<String, Integer> devolverQuantidade(Map<String, Integer> mapItens) {

        int novaQuantidade;

        for (Peca peca: this.lista) {

            if ( mapItens.containsKey(peca.getNome())) {

                novaQuantidade = peca.getQuantidade() + mapItens.get(peca.getNome());
                mapItens.remove(peca.getNome());

                peca.setQuantidade(novaQuantidade);
            }
        }

        ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);

        return new HashMap<>(mapItens);
    }

    /** Método para remover uma peça do armazenamento. A peça é encontrada através do nome. Antes de
     * fazer a procura da localização, as letras do nome são convertidas para minúsculo e os acentos são retirados.
     *
     * @param nome String - nome da peça que deve ser removida.*/

    public void removerPeca(String nome) {

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        for (int i = 0; i < this.lista.size(); i++) {

            if (lista.get(i).getNome().equals(nomeFormatado)) {

                this.lista.remove(i);
            }
        }
        ArmazenamentoDeDados.guardarDados(lista, nomeArquivo, nomePasta);

    }

    /** Método para esvaziar toda a lista de peças. A função clear é usada para limpar toda
     * a lista. As peças básicas são adicionadas novamente, porém com suas quantidades
     * zeradas.*/

    @Override
    public void removerTodos() {

        this.lista.clear();

        Peca peca0 = new Peca("ram", 0, 20, 20);
        this.lista.add(peca0);

        Peca peca1 = new Peca("placa mae", 0, 100, 100);
        this.lista.add(peca1);

        Peca peca2 = new Peca("fonte", 0, 30, 30);
        this.lista.add(peca2);

        Peca peca3 = new Peca("placa de video", 0, 100, 100);
        this.lista.add(peca3);

        Peca peca4 = new Peca("hd", 0, 30, 30);
        this.lista.add(peca4);

        Peca peca5 = new Peca("ssd", 0, 30, 30);
        this.lista.add(peca5);

        ArmazenamentoDeDados.guardarDados(lista, nomeArquivo,nomePasta);

    }

    /** Método responsável por mudar o endereço do arquivo quando for necessário realizar testes de unidade.*/

    @Override
    public void diretorioTest() {
        this.nomeArquivo = "pecatest.dat";
        this.nomePasta = "Teste Peca";
    }

    /** Método responsável por mudar para o endereço padrão do arquivo após a conclusão dos testes de unidade.*/

    @Override
    public void diretorioPadrao() {
        this.nomeArquivo = "peca.dat";
        this.nomePasta = "Peca";
    }

    @Override
    public Peca encontrarPorId(int id) { return null;}

    @Override
    public void remover(int id) { }

    @Override
    public boolean checarPorId(int id) { return false; }

}
