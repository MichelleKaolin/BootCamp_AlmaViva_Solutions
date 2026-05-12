package br.com.almaviva.qa.model;

import br.com.almaviva.qa.model.enums.TestResult;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestCycle {

    private final String id;
    private String name;
    private String sprint;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final List<TestCase> testCases;

    public TestCycle(String id, String name, String sprint,
                     LocalDate startDate, LocalDate endDate) {
        this.id        = id;
        this.name      = name;
        this.sprint    = sprint;
        this.startDate = startDate;
        this.endDate   = endDate;
        this.testCases = new ArrayList<>();
    }

    public void addTestCase(TestCase tc) {
        testCases.add(tc);
    }

    public long countByResult(TestResult result) {
        return testCases.stream()
                .filter(tc -> tc.getResult() == result)
                .count();
    }

    public int totalCases()     { return testCases.size(); }
    public int executedCases()  {
        return (int) testCases.stream()
                .filter(tc -> tc.getResult() != TestResult.NAO_EXECUTADO)
                .count();
    }

    public double passRate() {
        if (totalCases() == 0) return 0.0;
        return (countByResult(TestResult.PASSOU) * 100.0) / totalCases();
    }

    public String          getId()        { return id; }
    public String          getName()      { return name; }
    public void            setName(String n) { this.name = n; }
    public String          getSprint()    { return sprint; }
    public LocalDate       getStartDate() { return startDate; }
    public LocalDate       getEndDate()   { return endDate; }
    public List<TestCase>  getTestCases() { return testCases; }

    @Override
    public String toString() {
        return String.format(
            "Ciclo [%s] %s | Sprint: %s | %s → %s | Casos: %d | Taxa Aprovação: %.1f%%",
            id, name, sprint, startDate, endDate, totalCases(), passRate()
        );
    }
}
