package br.com.almaviva.qa.service;

import br.com.almaviva.qa.model.TestCase;
import br.com.almaviva.qa.model.enums.Priority;
import br.com.almaviva.qa.model.enums.TestResult;
import br.com.almaviva.qa.model.enums.TestType;

import java.util.*;
import java.util.stream.Collectors;
public class TestCaseService {

    private final Map<String, TestCase> repository = new LinkedHashMap<>();
    private int sequence = 1;

    public TestCase createStepByStep(String title, Priority priority,
                                     String precondition, List<String> steps,
                                     String expectedResult, String userStoryId) {
        String id = String.format("TC-%03d", sequence++);
        TestCase tc = new TestCase(id, title, priority,
                precondition, steps, expectedResult, userStoryId);
        repository.put(id, tc);
        System.out.printf("✅ Caso de Teste Step-by-Step criado: [%s] %s%n", id, title);
        return tc;
    }

    public TestCase createBDD(String title, Priority priority,
                              String given, String when, String then,
                              String userStoryId, boolean bdd) {
        String id = String.format("TC-%03d", sequence++);
        TestCase tc = new TestCase(id, title, priority,
                given, when, then, userStoryId, true);
        repository.put(id, tc);
        System.out.printf("✅ Caso de Teste BDD criado: [%s] %s%n", id, title);
        return tc;
    }

    public Optional<TestCase> findById(String id) {
        return Optional.ofNullable(repository.get(id));
    }

    public List<TestCase> findAll() {
        return new ArrayList<>(repository.values());
    }

    public List<TestCase> findByUserStory(String userStoryId) {
        return repository.values().stream()
                .filter(tc -> tc.getUserStoryId().equals(userStoryId))
                .collect(Collectors.toList());
    }

    public List<TestCase> findByType(TestType type) {
        return repository.values().stream()
                .filter(tc -> tc.getType() == type)
                .collect(Collectors.toList());
    }

    public List<TestCase> findByResult(TestResult result) {
        return repository.values().stream()
                .filter(tc -> tc.getResult() == result)
                .collect(Collectors.toList());
    }

    public void execute(String id, TestResult result, String notes) {
        findById(id).ifPresentOrElse(
                tc -> {
                    tc.execute(result, notes);
                    System.out.printf("🧪 [%s] executado → %s%n", id, result);
                },
                () -> System.out.println("⚠️ Caso de Teste não encontrado: " + id)
        );
    }

    public void printExecutionSummary() {
        int total       = repository.size();
        long passed     = repository.values().stream().filter(TestCase::isPassed).count();
        long failed     = repository.values().stream().filter(TestCase::isFailed).count();
        long notRun     = repository.values().stream().filter(TestCase::isNotRun).count();
        long blocked    = repository.values().stream()
                .filter(tc -> tc.getResult() == TestResult.BLOQUEADO).count();

        double passRate = total == 0 ? 0 : (passed * 100.0 / total);

        System.out.println("\n🧪 SUMÁRIO DE EXECUÇÃO DE TESTES");
        System.out.println("─".repeat(50));
        System.out.printf(" Total de casos   : %d%n", total);
        System.out.printf(" ✅ Passou         : %d%n", passed);
        System.out.printf(" ❌ Falhou         : %d%n", failed);
        System.out.printf(" 🚫 Bloqueado      : %d%n", blocked);
        System.out.printf(" ⬜ Não executado  : %d%n", notRun);
        System.out.printf(" 📊 Taxa de Aprovação: %.1f%%%n", passRate);
        System.out.println("─".repeat(50));
    }

    public void printAll() {
        if (repository.isEmpty()) {
            System.out.println("Nenhum caso de teste cadastrado.");
            return;
        }
        repository.values().forEach(System.out::println);
    }
}
