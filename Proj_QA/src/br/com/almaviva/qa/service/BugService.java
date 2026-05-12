package br.com.almaviva.qa.service;

import br.com.almaviva.qa.model.Bug;
import br.com.almaviva.qa.model.enums.BugStatus;
import br.com.almaviva.qa.model.enums.Priority;

import java.util.*;
import java.util.stream.Collectors;

public class BugService {

    private final Map<String, Bug> repository = new LinkedHashMap<>();
    private int sequence = 1;

    public Bug report(String title, String description,
                      String stepsToReproduce, String expectedBehavior,
                      String actualBehavior, Priority priority,
                      String reportedBy, String linkedTestCaseId,
                      String linkedUserStoryId) {

        String id = String.format("BUG-%03d", sequence++);
        Bug bug = new Bug(id, title, description, stepsToReproduce,
                expectedBehavior, actualBehavior, priority,
                reportedBy, linkedTestCaseId, linkedUserStoryId);
        repository.put(id, bug);
        System.out.printf("🐛 Bug reportado: [%s] %s | Prioridade: %s%n",
                id, title, priority);
        return bug;
    }

    public void assign(String bugId, String developer) {
        withBug(bugId, bug -> bug.assign(developer));
    }

    public void open(String bugId)          { withBug(bugId, Bug::open); }
    public void fix(String bugId)           { withBug(bugId, Bug::markFixed); }
    public void pendingRetest(String bugId) { withBug(bugId, Bug::pendingRetest); }
    public void retest(String bugId)        { withBug(bugId, Bug::retest); }
    public void verify(String bugId)        { withBug(bugId, Bug::verify); }
    public void close(String bugId)         { withBug(bugId, Bug::close); }
    public void reopen(String bugId)        { withBug(bugId, Bug::reopen); }
    public void markDuplicate(String bugId) { withBug(bugId, Bug::markDuplicate); }
    public void reject(String bugId)        { withBug(bugId, Bug::reject); }
    public void defer(String bugId)         { withBug(bugId, Bug::defer); }
    public void markNotABug(String bugId)   { withBug(bugId, Bug::markNotABug); }

    private void withBug(String id, java.util.function.Consumer<Bug> action) {
        findById(id).ifPresentOrElse(
                bug -> {
                    BugStatus old = bug.getStatus();
                    action.accept(bug);
                    System.out.printf("🔄 Bug [%s]: %s → %s%n",
                            id, old, bug.getStatus());
                },
                () -> System.out.println("⚠️ Bug não encontrado: " + id)
        );
    }

    public Optional<Bug> findById(String id) {
        return Optional.ofNullable(repository.get(id));
    }

    public List<Bug> findAll() {
        return new ArrayList<>(repository.values());
    }

    public List<Bug> findByStatus(BugStatus status) {
        return repository.values().stream()
                .filter(b -> b.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Bug> findByPriority(Priority priority) {
        return repository.values().stream()
                .filter(b -> b.getPriority() == priority)
                .collect(Collectors.toList());
    }

    public List<Bug> findOpenBugs() {
        return repository.values().stream()
                .filter(b -> b.getStatus() != BugStatus.CLOSED
                          && b.getStatus() != BugStatus.DUPLICATE
                          && b.getStatus() != BugStatus.REJECTED
                          && b.getStatus() != BugStatus.NOT_A_BUG)
                .collect(Collectors.toList());
    }

    public void printSummary() {
        System.out.println("\n🐛 RELATÓRIO DE BUGS");
        System.out.println("─".repeat(50));
        System.out.printf(" Total de bugs    : %d%n", repository.size());
        System.out.printf(" Bugs abertos     : %d%n", findOpenBugs().size());
        System.out.printf(" Fechados         : %d%n",
                findByStatus(BugStatus.CLOSED).size());

        System.out.println(" Por Prioridade:");
        for (Priority p : Priority.values()) {
            long count = repository.values().stream()
                    .filter(b -> b.getPriority() == p).count();
            if (count > 0)
                System.out.printf("   %-20s: %d%n", p, count);
        }

        System.out.println(" Por Status:");
        for (BugStatus s : BugStatus.values()) {
            long count = repository.values().stream()
                    .filter(b -> b.getStatus() == s).count();
            if (count > 0)
                System.out.printf("   %-20s: %d%n", s.getLabel(), count);
        }
        System.out.println("─".repeat(50));
    }

    public void printAll() {
        if (repository.isEmpty()) {
            System.out.println("Nenhum bug registrado.");
            return;
        }
        repository.values().forEach(System.out::println);
    }
}
