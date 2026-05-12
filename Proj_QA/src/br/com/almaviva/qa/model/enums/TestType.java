package br.com.almaviva.qa.model.enums;

public enum TestType {
    STEP_BY_STEP("Step-by-Step"),
    BDD("BDD / Gherkin");

    private final String label;

    TestType(String label) { this.label = label; }

    public String getLabel() { return label; }

    @Override
    public String toString() { return label; }
}
