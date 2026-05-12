package br.com.almaviva.qa.model.enums;

public enum WorkflowStatus {
    TO_DO("To Do"),
    IN_PROGRESS("In Progress"),
    BLOCKED("Blocked"),
    READY_FOR_QA("Ready for QA"),
    DONE("Done"),
    REOPENED("Reopened");

    private final String label;

    WorkflowStatus(String label) { this.label = label; }

    public String getLabel() { return label; }

    @Override
    public String toString() { return label; }
}
