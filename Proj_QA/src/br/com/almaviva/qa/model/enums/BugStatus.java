package br.com.almaviva.qa.model.enums;

public enum BugStatus {
    NEW("New"),
    ASSIGNED("Assigned"),
    OPEN("Open"),
    FIXED("Fixed"),
    PENDING_RETEST("Pending Retest"),
    RETEST("Retest"),
    VERIFIED("Verified"),
    CLOSED("Closed"),
    REOPENED("Reopened"),
    DUPLICATE("Duplicate"),
    REJECTED("Rejected"),
    DEFERRED("Deferred"),
    NOT_A_BUG("Not a Bug");

    private final String label;

    BugStatus(String label) { this.label = label; }

    public String getLabel() { return label; }

    @Override
    public String toString() { return label; }
}
