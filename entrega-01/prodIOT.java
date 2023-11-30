// package engtelecom.std;

// import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class prodIOT {
    private boolean ligado;
    private int dados;
    private String nome;
    private int id;
    private List<String> funcoes;

    public prodIOT(boolean ligado, int dados, String nome, int id, List<String> funcoes) {
        this.ligado = ligado;
        this.dados = dados;
        this.nome = nome;
        this.id = id;
        this.funcoes = funcoes;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }

    public int getDados() {
        return dados;
    }

    public void setDados(int dados) {
        this.dados = dados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(List<String> funcoes) {
        this.funcoes = funcoes;
    }

    /**
     * Toda vez que um produto for executado com o docker
     * Terá que se conectar no servidor, divulgar seus dados iniciais
     * E aguardar novas solicitações do servidor
     * Ou seja, não deve fechar seção
     * 
     * @param args
     */
    public static void main(String[] args) {
        boolean connect = false;
        boolean server = false;
        boolean stop = false;
        List<String> funcList = new ArrayList<>();

        // código contrutor iot
        prodIOT air = new prodIOT(false, 22, "abobrinha", 1234, funcList);

        while (!stop) {
            // código que conecta ao servidor ...
            if (connect) {
                // envia dados ao servidor ...
                server = true;
            } else {
                server = false;
            }
            do {
                // recebe dados do servidor
                // executa os getters and setters;
                // envia de volta ao servidor
            } while (server);
        }
    }

}