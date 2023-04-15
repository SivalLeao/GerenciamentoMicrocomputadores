package com.pbl.gerenciamentomicrocomputadores.dao.peca;

import com.pbl.gerenciamentomicrocomputadores.model.Peca;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/** É responsável por armazenar todos as peças do sistema, e estruturar os métodos
 * necessários para inserir, consultar, alterar ou remover. Implementa a interface PecaDAO.
 *
 * @author Silvio Oliveira,  Sival Leão.
 * @version 1.0.
 */

public class PecaImpl implements PecaDAO {

    private Map<String, Peca> map;

    /** Construtor que inicializa a estrutura de armazenamento de peças. A peças básicas já são inseridas
     * com 10 de quantidade e seus valores padrões. Todos os nomes das peças são colocados na estrutura com
     * letras minúsculas e sem acentos.*/

    public PecaImpl () {

        this.map = new HashMap<String, Peca>();

        Peca peca0 = new Peca("ram", 10, 20, 20);
        this.map.put(peca0.getNome(), peca0);

        Peca peca1 = new Peca("placa mae", 10, 100, 100);
        this.map.put(peca1.getNome(), peca1);

        Peca peca2 = new Peca("fonte", 10, 30, 30);
        this.map.put(peca2.getNome(), peca2);

        Peca peca3 = new Peca("placa de video", 10, 100, 100);
        this.map.put(peca3.getNome(), peca3);

        Peca peca4 = new Peca("hd", 10, 30, 30);
        this.map.put(peca4.getNome(), peca4);

        Peca peca5 = new Peca("ssd", 10, 30, 30);
        this.map.put(peca5.getNome(), peca5);
    }

    /** Método para inserir um objeto do tipo Peca na estrutura HashMap de armazenamento.
     * O nome da peça é usado como chave e o objeto é o conteúdo.
     *
     * @param peca Peca - peça que deve ser inserida.*/

    @Override
    public void criar(Peca peca) {

        map.put(peca.getNome(), peca);
    }

    /** Método para atualizar as informações de uma peça na estrutura. O nome é utilizado para encontrar
     * o objeto antigo que representava a peça, substituindo-o pelo novo objeto com as informações atualizadas.
     *
     * @param peca Peca - peça que deve ser atualizada.*/

    @Override
    public void atualizar(Peca peca) {

        map.put(peca.getNome(), peca);
    }

    /** Método para remover determinada quantidade de uma peça específica. A peça é identificada
     * pelo nome. Antes de fazer as alterações na quantidade, as letras do nome são convertidas para
     * minúsculo e os acentos são retirados, para que a busca possa ser feita. O método checarQuantidade
     * deve ser usado antes de chamar removeQuantity para conferir se a quantidade pode ser retirada sem
     * ultrapassar o limite.
     *
     * @param nome String - nome da peça.
     * @param quantidade int - quantidade que deve ser retirada da peça.*/

    @Override
    public void removerQuantidade(String nome, int quantidade) {

        int novaQuantidade;

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        novaQuantidade = this.map.get(nomeFormatado).getQuantidade() - quantidade;

        this.map.get(nomeFormatado).setQuantidade(novaQuantidade);
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

        novaQuantidade = this.map.get(nomeFormatado).getQuantidade() + quantidade;

        this.map.get(nomeFormatado).setQuantidade(novaQuantidade);
    }

    /** Método de retorno de todo o armazenamento de peças do sistema. Os dados são compactados em
     * uma estrutura do tipo HashMap, que tem o nome da peça como chave e o objeto do tipo Peca como
     * conteúdo.
     *
     * @return Map<String, Peca> - estrutura com todas as peças do sistema armazenadas.*/

    @Override
    public Map<String, Peca> encontrarTodoMap() {

        Map<String, Peca> mapPeca = new HashMap<String, Peca>();

        for (String nomePeca: this.map.keySet()) {

            mapPeca.put(nomePeca, this.map.get(nomePeca));
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

        return this.map.get(nomeFormatado);
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

        for (String key: this.map.keySet()) {

            if (nomeFormatado.equals(key)){
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

        if (this.map.get(nomeFormatado).getQuantidade() >= quantidade) {

            return true;
        }
        else {

            return false;
        }
    }

    /** Método para encontrar as peças do sistema que estão com uma baixa quantidade. O valor de alerta é 5.
     * Se uma peça tiver quantidade igual ou menor ao valor de alerta, ela é adicionada na lista de retorno.
     *
     * @return List<Peca> - lista de peças com baixa quantidade.*/

    public List<Peca> alertaDeQuantidade() {

        List<Peca> listPeca = new ArrayList<Peca>();

        for (String nomePeca: this.map.keySet()) {

            if (this.map.get(nomePeca).getQuantidade() <= 5) {

                listPeca.add(this.map.get(nomePeca));
            }
        }

        return listPeca;
    }

    /** Método para devolver a quantidade retirada das peças para a realização de uma
     * ordem de serviço. Utilizado quando uma ordem de serviço é cancelada e as peças precisam ser
     * adicionadas novamente. Caso algumas peças não possam ser retornadas ao sistema, uma estrutura
     * listando elas é retornada pelo método.
     *
     * @param mapItens Map<String,Integer> - estrutura com o nome das peças e suas quantidades
     *                 que devem ser devolvidas.
     * @return Map<String,Integer> - estrutura com os nomes as peças que não puderam ser devolvidas
     * e suas quantidade.*/

    public Map<String, Integer> devolverQuantidade(Map<String, Integer> mapItens) {

        int novaQuantidade;

        Map<String, Integer> mapItensNaoDevolvidos = new HashMap<String, Integer>();

        for (String nomePeca: mapItens.keySet()) {

            if (this.map.containsKey(nomePeca)) {

                novaQuantidade = this.map.get(nomePeca).getQuantidade() + mapItens.get(nomePeca);

                this.map.get(nomePeca).setQuantidade(novaQuantidade);
            }
            else {

                mapItensNaoDevolvidos.put(nomePeca, mapItens.get(nomePeca));
            }
        }

        return mapItensNaoDevolvidos;
    }

    /** Método para remover uma peça do armazenamento. A peça é encontrada através do nome. Antes de
     * fazer a procura da localização, as letras do nome são convertidas para minúsculo e os acentos são retirados.
     *
     * @param nome String - nome da peça que deve ser removida.*/

    public void removerPeca(String nome) {

        String nomeFormatado = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();

        this.map.remove(nomeFormatado);
    }

    /** Método para esvaziar todo o armazenamento de peças. A função clear é usada para limpar toda
     * a estrutura HashMap. As peças básicas são adicionadas novamente, porém com suas quantidades
     * zeradas.*/

    @Override
    public void removerTodos() {

        this.map.clear();

        Peca peca0 = new Peca("ram", 0, 20, 20);
        this.map.put(peca0.getNome(), peca0);

        Peca peca1 = new Peca("placa mae", 0, 100, 100);
        this.map.put(peca1.getNome(), peca1);

        Peca peca2 = new Peca("fonte", 0, 30, 30);
        this.map.put(peca2.getNome(), peca2);

        Peca peca3 = new Peca("placa de video", 0, 100, 100);
        this.map.put(peca3.getNome(), peca3);

        Peca peca4 = new Peca("hd", 0, 30, 30);
        this.map.put(peca4.getNome(), peca4);

        Peca peca5 = new Peca("ssd", 0, 30, 30);
        this.map.put(peca5.getNome(), peca5);
    }

    @Override
    public List<Peca> encontrarTodos() { return null;};

    @Override
    public Peca encontrarPorId(int id) { return null;}

    @Override
    public void remover(int id) { }

    @Override
    public boolean checarPorId(int id) { return false; }

}
