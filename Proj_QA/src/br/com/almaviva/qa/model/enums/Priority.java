package br.com.almaviva.qa.model.enums;

public enum Priority {
    CRITICA("Crítica", "🔴"),
    ALTA("Alta", "🟠"),
    MEDIA("Média", "🟡"),
    BAIXA("Baixa", "🟢");

    private final String label;
    private final String icon;

    Priority(String label, String icon) {
        this.label = label;
        this.icon = icon;
    }

    public String getLabel() { return label; }
    public String getIcon()  { return icon; }

    @Override
    public String toString() { return icon + " " + label; }
}
