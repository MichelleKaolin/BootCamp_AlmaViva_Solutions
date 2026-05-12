package br.com.almaviva.qa.model;

import br.com.almaviva.qa.model.enums.Priority;
import br.com.almaviva.qa.model.enums.WorkflowStatus;

import java.util.ArrayList;
import java.util.List;


public class UserStory {

    private final String id;
    private final String epic;
    private String title;
    private String actor;          
    private String desire;         
    private String motivation;     
    private Priority priority;
    private WorkflowStatus status;
    private int storyPoints;
    private final List<String> acceptanceCriteria;
    private final List<TestCase> testCases;

    public UserStory(String id, String epic, String title,
                     String actor, String desire, String motivation,
                     Priority priority, int storyPoints) {
        this.id                = id;
        this.epic              = epic;
        this.title             = title;
        this.actor             = actor;
        this.desire            = desire;
        this.motivation        = motivation;
        this.priority          = priority;
        this.storyPoints       = storyPoints;
        this.status            = WorkflowStatus.TO_DO;
        this.acceptanceCriteria = new ArrayList<>();
        this.testCases          = new ArrayList<>();
    }


    public void addAcceptanceCriteria(String criterion) {
        acceptanceCriteria.add(criterion);
    }

    public void addTestCase(TestCase tc) {
        testCases.add(tc);
    }

    public void advanceStatus() {
        switch (status) {
            case TO_DO:
                status = WorkflowStatus.IN_PROGRESS;
                break;
            case IN_PROGRESS:
                status = WorkflowStatus.READY_FOR_QA;
                break;
            case READY_FOR_QA:
                status = WorkflowStatus.DONE;
                break;
            case REOPENED:
                status = WorkflowStatus.IN_PROGRESS;
                break;
            default:
                System.out.println("Status já é final: " + status);
        }
    }

    public void block()   { status = WorkflowStatus.BLOCKED; }
    public void reopen()  { status = WorkflowStatus.REOPENED; }

    public String getNarrative() {
        return String.format("Como %s, desejo %s, pois %s.", actor, desire, motivation);
    }


    public String getId()                   { return id; }
    public String getEpic()                 { return epic; }
    public String getTitle()                { return title; }
    public void   setTitle(String t)        { this.title = t; }
    public String getActor()                { return actor; }
    public void   setActor(String a)        { this.actor = a; }
    public String getDesire()               { return desire; }
    public void   setDesire(String d)       { this.desire = d; }
    public String getMotivation()           { return motivation; }
    public void   setMotivation(String m)   { this.motivation = m; }
    public Priority getPriority()           { return priority; }
    public void   setPriority(Priority p)   { this.priority = p; }
    public WorkflowStatus getStatus()       { return status; }
    public int    getStoryPoints()          { return storyPoints; }
    public void   setStoryPoints(int sp)    { this.storyPoints = sp; }
    public List<String>   getAcceptanceCriteria() { return acceptanceCriteria; }
    public List<TestCase> getTestCases()    { return testCases; }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════════════════\n");
        sb.append(String.format(" [%s] %s\n", id, title));
        sb.append(String.format(" Épico: %s | Prioridade: %s | Story Points: %d\n",
                epic, priority, storyPoints));
        sb.append(String.format(" Status: %s\n", status));
        sb.append(String.format(" Narrativa: %s\n", getNarrative()));

        if (!acceptanceCriteria.isEmpty()) {
            sb.append(" Critérios de Aceite:\n");
            for (int i = 0; i < acceptanceCriteria.size(); i++) {
                sb.append(String.format("   %d. %s\n", i + 1, acceptanceCriteria.get(i)));
            }
        }

        if (!testCases.isEmpty()) {
            sb.append(String.format(" Casos de Teste vinculados: %d\n", testCases.size()));
        }
        sb.append("═══════════════════════════════════════════════════════");
        return sb.toString();
    }
}
