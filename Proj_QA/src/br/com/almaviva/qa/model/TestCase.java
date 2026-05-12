package br.com.almaviva.qa.model;

import br.com.almaviva.qa.model.enums.Priority;
import br.com.almaviva.qa.model.enums.TestResult;
import br.com.almaviva.qa.model.enums.TestType;

import java.util.ArrayList;
import java.util.List;

public class TestCase {

    private final String id;
    private String title;
    private final TestType type;
    private Priority priority;
    private TestResult result;
    private String userStoryId;
    private String precondition;
    private final List<String> steps;
    private String expectedResult;
    private String given;
    private String when;
    private String then;
    private String executionNotes;

    public TestCase(String id, String title, Priority priority,
                    String precondition, List<String> steps,
                    String expectedResult, String userStoryId) {
        this.id             = id;
        this.title          = title;
        this.type           = TestType.STEP_BY_STEP;
        this.priority       = priority;
        this.precondition   = precondition;
        this.steps          = new ArrayList<>(steps);
        this.expectedResult = expectedResult;
        this.userStoryId    = userStoryId;
        this.result         = TestResult.NAO_EXECUTADO;
        this.given = this.when = this.then = "";
    }

    public TestCase(String id, String title, Priority priority,
                    String given, String when, String then,
                    String userStoryId, boolean bdd) {
        this.id          = id;
        this.title       = title;
        this.type        = TestType.BDD;
        this.priority    = priority;
        this.given       = given;
        this.when        = when;
        this.then        = then;
        this.userStoryId = userStoryId;
        this.result      = TestResult.NAO_EXECUTADO;
        this.steps       = new ArrayList<>();
        this.precondition = this.expectedResult = "";
    }

    public void execute(TestResult result, String notes) {
        this.result         = result;
        this.executionNotes = notes;
    }

    public boolean isPassed()    { return result == TestResult.PASSOU; }
    public boolean isFailed()    { return result == TestResult.FALHOU; }
    public boolean isNotRun()    { return result == TestResult.NAO_EXECUTADO; }
    public String     getId()               { return id; }
    public String     getTitle()            { return title; }
    public void       setTitle(String t)    { this.title = t; }
    public TestType   getType()             { return type; }
    public Priority   getPriority()         { return priority; }
    public void       setPriority(Priority p){ this.priority = p; }
    public TestResult getResult()           { return result; }
    public String     getUserStoryId()      { return userStoryId; }
    public String     getPrecondition()     { return precondition; }
    public List<String> getSteps()          { return steps; }
    public String     getExpectedResult()   { return expectedResult; }
    public String     getGiven()            { return given; }
    public String     getWhen()             { return when; }
    public String     getThen()             { return then; }
    public String     getExecutionNotes()   { return executionNotes; }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("─────────────────────────────────────────────────────\n");
        sb.append(String.format(" Caso de Teste [%s] – %s\n", id, title));
        sb.append(String.format(" Tipo: %s | Prioridade: %s | Resultado: %s\n",
                type, priority, result));
        sb.append(String.format(" Vinculado à US: %s\n", userStoryId));

        if (type == TestType.STEP_BY_STEP) {
            sb.append(String.format(" Pré-condição: %s\n", precondition));
            sb.append(" Passos:\n");
            for (int i = 0; i < steps.size(); i++) {
                sb.append(String.format("   %d. %s\n", i + 1, steps.get(i)));
            }
            sb.append(String.format(" Resultado Esperado: %s\n", expectedResult));
        } else {
            sb.append(String.format(" Given (Dado que): %s\n", given));
            sb.append(String.format(" When  (Quando):   %s\n", when));
            sb.append(String.format(" Then  (Então):    %s\n", then));
        }

        if (executionNotes != null && !executionNotes.isBlank()) {
            sb.append(String.format(" Notas de Execução: %s\n", executionNotes));
        }
        sb.append("─────────────────────────────────────────────────────");
        return sb.toString();
    }
}
