package br.com.almaviva.qa.service;

import br.com.almaviva.qa.model.UserStory;
import br.com.almaviva.qa.model.enums.Priority;
import br.com.almaviva.qa.model.enums.WorkflowStatus;

import java.util.*;
import java.util.stream.Collectors;
public class UserStoryService {

    private final Map<String, UserStory> repository = new LinkedHashMap<>();
    private int sequence = 1;

    public UserStory create(String epic, String title,
                            String actor, String desire, String motivation,
                            Priority priority, int storyPoints) {
        String id = String.format("US-%03d", sequence++);
        UserStory us = new UserStory(id, epic, title,
                actor, desire, motivation, priority, storyPoints);
        repository.put(id, us);
        System.out.printf("✅ User Story criada: [%s] %s%n", id, title);
        return us;
    }

    public Optional<UserStory> findById(String id) {
        return Optional.ofNullable(repository.get(id));
    }

    public List<UserStory> findAll() {
        return new ArrayList<>(repository.values());
    }

    public List<UserStory> findByEpic(String epic) {
        return repository.values().stream()
                .filter(us -> us.getEpic().equalsIgnoreCase(epic))
                .collect(Collectors.toList());
    }

    public List<UserStory> findByStatus(WorkflowStatus status) {
        return repository.values().stream()
                .filter(us -> us.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<UserStory> findByPriority(Priority priority) {
        return repository.values().stream()
                .filter(us -> us.getPriority() == priority)
                .sorted(Comparator.comparingInt(UserStory::getStoryPoints))
                .collect(Collectors.toList());
    }

    public boolean delete(String id) {
        return repository.remove(id) != null;
    }

    public void advanceStatus(String id) {
        findById(id).ifPresentOrElse(
                us -> {
                    WorkflowStatus old = us.getStatus();
                    us.advanceStatus();
                    System.out.printf("🔄 [%s] %s → %s%n", id, old, us.getStatus());
                },
                () -> System.out.println("⚠️ User Story não encontrada: " + id)
        );
    }

    public int totalStoryPoints() {
        return repository.values().stream()
                .mapToInt(UserStory::getStoryPoints)
                .sum();
    }

    public int totalStoryPoints(WorkflowStatus status) {
        return repository.values().stream()
                .filter(us -> us.getStatus() == status)
                .mapToInt(UserStory::getStoryPoints)
                .sum();
    }

    public void printAll() {
        if (repository.isEmpty()) {
            System.out.println("Nenhuma User Story cadastrada.");
            return;
        }
        repository.values().forEach(System.out::println);
    }

    public void printSummary() {
        System.out.println("\n📋 RESUMO DO BACKLOG");
        System.out.println("─".repeat(50));
        System.out.printf(" Total de USs       : %d%n", repository.size());
        System.out.printf(" Total Story Points : %d%n", totalStoryPoints());
        System.out.println(" Por Status:");
        for (WorkflowStatus s : WorkflowStatus.values()) {
            long count = repository.values().stream()
                    .filter(us -> us.getStatus() == s).count();
            if (count > 0)
                System.out.printf("   %-18s: %d (SP: %d)%n",
                        s.getLabel(), count, totalStoryPoints(s));
        }
        System.out.println("─".repeat(50));
    }
}
