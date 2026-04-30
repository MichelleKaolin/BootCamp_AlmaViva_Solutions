package modelo_Michelle;

import java.util.HashSet;
import java.util.Set;

public class Dev {

    private String nome;
    private Set<Conteudo> inscritos = new HashSet<>();
    private Set<Conteudo> concluidos = new HashSet<>();

    public void inscreverBootcamp(Bootcamp bootcamp) {
        this.inscritos.addAll(bootcamp.getConteudos());
    }

    public void progredir() {
        if (!inscritos.isEmpty()) {
            Conteudo conteudo = inscritos.iterator().next();
            concluidos.add(conteudo);
            inscritos.remove(conteudo);
        }
    }

    public double calcularXpTotal() {
        return concluidos.stream()
                .mapToDouble(Conteudo::calcularXp)
                .sum();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}