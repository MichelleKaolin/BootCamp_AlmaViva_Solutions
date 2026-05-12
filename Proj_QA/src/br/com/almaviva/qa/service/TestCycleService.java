package br.com.almaviva.qa.service;

import br.com.almaviva.qa.model.TestCase;
import br.com.almaviva.qa.model.TestCycle;
import br.com.almaviva.qa.model.enums.TestResult;

import java.time.LocalDate;
import java.util.*;

public class TestCycleService {

    private final Map<String, TestCycle> repository = new LinkedHashMap<>();
    private int sequence = 1;

    public TestCycle create(String name, String sprint,
                            LocalDate startDate, LocalDate endDate) {
        String id = String.format("CY-%03d", sequence++);
        TestCycle cycle = new TestCycle(id, name, sprint, startDate, endDate);
        repository.put(id, cycle);
        System.out.printf("✅ Ciclo de Testes criado: [%s] %s | Sprint: %s%n",
                id, name, sprint);
        return cycle;
    }

    public void addTestCase(String cycleId, TestCase tc) {
        findById(cycleId).ifPresentOrElse(
                cycle -> {
                    cycle.addTestCase(tc);
                    System.out.printf("   ➕ [%s] adicionado ao ciclo [%s]%n",
                            tc.getId(), cycleId);
                },
                () -> System.out.println("⚠️ Ciclo não encontrado: " + cycleId)
        );
    }

    public Optional<TestCycle> findById(String id) {
        return Optional.ofNullable(repository.get(id));
    }

    public List<TestCycle> findAll() {
        return new ArrayList<>(repository.values());
    }

    public void printCycleReport(String cycleId) {
        findById(cycleId).ifPresentOrElse(cycle -> {
            System.out.println("\n📊 RELATÓRIO DO CICLO: " + cycle.getName());
            System.out.println("─".repeat(55));
            System.out.printf(" Sprint      : %s%n", cycle.getSprint());
            System.out.printf(" Período     : %s → %s%n",
                    cycle.getStartDate(), cycle.getEndDate());
            System.out.printf(" Total casos : %d%n", cycle.totalCases());
            System.out.printf(" Executados  : %d%n", cycle.executedCases());
            System.out.printf(" ✅ Passaram  : %d%n",
                    cycle.countByResult(TestResult.PASSOU));
            System.out.printf(" ❌ Falharam  : %d%n",
                    cycle.countByResult(TestResult.FALHOU));
            System.out.printf(" 🚫 Bloqueado : %d%n",
                    cycle.countByResult(TestResult.BLOQUEADO));
            System.out.printf(" 📊 Taxa aprovação: %.1f%%%n", cycle.passRate());
            System.out.println("─".repeat(55));
        }, () -> System.out.println("Ciclo não encontrado."));
    }
}
