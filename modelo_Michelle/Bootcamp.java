package modelo_Michelle;

import java.util.HashSet;
import java.util.Set;

public class Bootcamp {

    private String nome;
    private Set<Conteudo> conteudos = new HashSet<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudos() {
        return conteudos;
    }
}