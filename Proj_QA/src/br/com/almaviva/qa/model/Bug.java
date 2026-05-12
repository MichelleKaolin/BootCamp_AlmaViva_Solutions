package br.com.almaviva.qa.model;

import br.com.almaviva.qa.model.enums.BugStatus;
import br.com.almaviva.qa.model.enums.Priority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Bug {

    private final String id;
    private String title;
    private String description;
    private String stepsToReproduce;
    private String expectedBehavior;
    private String actualBehavior;
    private Priority priority;
    private BugStatus status;
    private final String reportedBy;
    private String assignedTo;
    private final String linkedTestCaseId;
    private final String linkedUserStoryId;
    private final LocalDateTime reportedAt;
    private final List<String> statusHistory;

    public Bug(String id, String title, String description,
               String stepsToReproduce, String expectedBehavior,
               String actualBehavior, Priority priority,
               String reportedBy, String linkedTestCaseId,
               String linkedUserStoryId) {
        this.id                 = id;
        this.title              = title;
        this.description        = description;
        this.stepsToReproduce   = stepsToReproduce;
        this.expectedBehavior   = expectedBehavior;
        this.actualBehavior     = actualBehavior;
        this.priority           = priority;
        this.reportedBy         = reportedBy;
        this.linkedTestCaseId   = linkedTestCaseId;
        this.linkedUserStoryId  = linkedUserStoryId;
        this.status             = BugStatus.NEW;
        this.reportedAt         = LocalDateTime.now();
        this.statusHistory      = new ArrayList<>();
        this.statusHistory.add("NEW – " + reportedAt);
    }


    private void transition(BugStatus next) {
        this.status = next;
        statusHistory.add(next.getLabel() + " – " + LocalDateTime.now());
    }

    public void assign(String developer) {
        this.assignedTo = developer;
        transition(BugStatus.ASSIGNED);
    }

    public void open()         { transition(BugStatus.OPEN); }
    public void markFixed()    { transition(BugStatus.FIXED); }
    public void pendingRetest(){ transition(BugStatus.PENDING_RETEST); }
    public void retest()       { transition(BugStatus.RETEST); }
    public void verify()       { transition(BugStatus.VERIFIED); }
    public void close()        { transition(BugStatus.CLOSED); }
    public void reopen()       { transition(BugStatus.REOPENED); }
    public void markDuplicate(){ transition(BugStatus.DUPLICATE); }
    public void reject()       { transition(BugStatus.REJECTED); }
    public void defer()        { transition(BugStatus.DEFERRED); }
    public void markNotABug()  { transition(BugStatus.NOT_A_BUG); }
   public String         getId()                  { return id; }
    public String         getTitle()               { return title; }
    public void           setTitle(String t)       { this.title = t; }
    public String         getDescription()         { return description; }
    public String         getStepsToReproduce()    { return stepsToReproduce; }
    public String         getExpectedBehavior()    { return expectedBehavior; }
    public String         getActualBehavior()      { return actualBehavior; }
    public Priority       getPriority()            { return priority; }
    public void           setPriority(Priority p)  { this.priority = p; }
    public BugStatus      getStatus()              { return status; }
    public String         getReportedBy()          { return reportedBy; }
    public String         getAssignedTo()          { return assignedTo; }
    public String         getLinkedTestCaseId()    { return linkedTestCaseId; }
    public String         getLinkedUserStoryId()   { return linkedUserStoryId; }
    public LocalDateTime  getReportedAt()          { return reportedAt; }
    public List<String>   getStatusHistory()       { return statusHistory; }

    @Override
    public String toString() {
        return String.format(
            "🐛 Bug [%s] %s | Prioridade: %s | Status: %s | Reportado por: %s | TC: %s",
            id, title, priority, status, reportedBy, linkedTestCaseId
        );
    }

    public String toDetailString() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔═══════════════════════════════════════════════════╗\n");
        sb.append(String.format("  BUG [%s] – %s\n", id, title));
        sb.append("╚═══════════════════════════════════════════════════╝\n");
        sb.append(String.format(" Prioridade    : %s\n", priority));
        sb.append(String.format(" Status        : %s\n", status));
        sb.append(String.format(" Reportado por : %s em %s\n",
                reportedBy, reportedAt.toLocalDate()));
        if (assignedTo != null)
            sb.append(String.format(" Atribuído a   : %s\n", assignedTo));
        sb.append(String.format(" Descrição     : %s\n", description));
        sb.append(String.format(" Passos p/ Repr: %s\n", stepsToReproduce));
        sb.append(String.format(" Comportamento Esperado: %s\n", expectedBehavior));
        sb.append(String.format(" Comportamento Atual   : %s\n", actualBehavior));
        sb.append(String.format(" US vinculada  : %s | TC vinculado: %s\n",
                linkedUserStoryId, linkedTestCaseId));
        sb.append(" Histórico de Status:\n");
        statusHistory.forEach(h -> sb.append("   • ").append(h).append("\n"));
        return sb.toString();
    }
}
