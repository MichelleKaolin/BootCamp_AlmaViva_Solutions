package br.com.almaviva.qa.model.enums;

public enum TestResult {
    NAO_EXECUTADO("Não Executado", "⬜"),
    PASSOU("Passou", "✅"),
    FALHOU("Falhou", "❌"),
    BLOQUEADO("Bloqueado", "🚫"),
    EM_ANDAMENTO("Em Andamento", "🔄");

    private final String label;
    private final String icon;

    TestResult(String label, String icon) {
        this.label = label;
        this.icon  = icon;
    }

    public String getLabel() { return label; }
    public String getIcon()  { return icon; }

    @Override
    public String toString() { return icon + " " + label; }
}
