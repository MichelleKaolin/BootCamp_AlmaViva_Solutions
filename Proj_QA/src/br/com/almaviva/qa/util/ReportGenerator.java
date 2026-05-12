package br.com.almaviva.qa.util;

import br.com.almaviva.qa.model.Bug;
import br.com.almaviva.qa.model.TestCase;
import br.com.almaviva.qa.model.UserStory;
import br.com.almaviva.qa.model.enums.BugStatus;
import br.com.almaviva.qa.model.enums.TestResult;
import br.com.almaviva.qa.model.enums.WorkflowStatus;

import java.time.LocalDate;
import java.util.List;

public class ReportGenerator {

    private ReportGenerator() {}

    public static void printSprintReport(String sprintName,
                                         List<UserStory>  stories,
                                         List<TestCase>   testCases,
                                         List<Bug>        bugs) {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.printf( "║  RELATÓRIO DE SPRINT: %-31s║%n", sprintName);
        System.out.printf( "║  Gerado em: %-40s║%n", LocalDate.now());
        System.out.println("║  Bootcamp QA – AlmaViva Solutions + DIO              ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        System.out.println("\n📋 USER STORIES");
        System.out.println("─".repeat(55));
        long done     = stories.stream().filter(us -> us.getStatus() == WorkflowStatus.DONE).count();
        long inProg   = stories.stream().filter(us -> us.getStatus() == WorkflowStatus.IN_PROGRESS).count();
        long readyQA  = stories.stream().filter(us -> us.getStatus() == WorkflowStatus.READY_FOR_QA).count();
        long toDo     = stories.stream().filter(us -> us.getStatus() == WorkflowStatus.TO_DO).count();
        int  totalSP  = stories.stream().mapToInt(UserStory::getStoryPoints).sum();
        int  doneSP   = stories.stream()
                .filter(us -> us.getStatus() == WorkflowStatus.DONE)
                .mapToInt(UserStory::getStoryPoints).sum();

        System.out.printf(" Total USs       : %d  (SP total: %d)%n", stories.size(), totalSP);
        System.out.printf(" ✅ Done          : %d  (SP entregues: %d)%n", done, doneSP);
        System.out.printf(" 🔄 In Progress   : %d%n", inProg);
        System.out.printf(" 🔍 Ready for QA  : %d%n", readyQA);
        System.out.printf(" ⬜ To Do         : %d%n", toDo);
        double velocity = stories.isEmpty() ? 0 : (doneSP * 100.0 / totalSP);
        System.out.printf(" 📈 Velocity      : %.1f%% dos SPs entregues%n", velocity);
        System.out.println("\n🧪 CASOS DE TESTE");
        System.out.println("─".repeat(55));
        long passed  = testCases.stream().filter(tc -> tc.getResult() == TestResult.PASSOU).count();
        long failed  = testCases.stream().filter(tc -> tc.getResult() == TestResult.FALHOU).count();
        long blocked = testCases.stream().filter(tc -> tc.getResult() == TestResult.BLOQUEADO).count();
        long notRun  = testCases.stream().filter(tc -> tc.getResult() == TestResult.NAO_EXECUTADO).count();
        double pass  = testCases.isEmpty() ? 0 : (passed * 100.0 / testCases.size());

        System.out.printf(" Total casos     : %d%n", testCases.size());
        System.out.printf(" ✅ Passou        : %d%n", passed);
        System.out.printf(" ❌ Falhou        : %d%n", failed);
        System.out.printf(" 🚫 Bloqueado     : %d%n", blocked);
        System.out.printf(" ⬜ Não executado : %d%n", notRun);
        System.out.printf(" 📊 Taxa aprovação: %.1f%%%n", pass);
        System.out.println("\n🐛 BUGS");
        System.out.println("─".repeat(55));
        long openBugs   = bugs.stream()
                .filter(b -> b.getStatus() != BugStatus.CLOSED
                          && b.getStatus() != BugStatus.DUPLICATE
                          && b.getStatus() != BugStatus.REJECTED
                          && b.getStatus() != BugStatus.NOT_A_BUG)
                .count();
        long closedBugs = bugs.stream()
                .filter(b -> b.getStatus() == BugStatus.CLOSED).count();
        long critBugs   = bugs.stream()
                .filter(b -> b.getPriority() ==
                        br.com.almaviva.qa.model.enums.Priority.CRITICA)
                .count();

        System.out.printf(" Total bugs      : %d%n", bugs.size());
        System.out.printf(" 🔴 Críticos     : %d%n", critBugs);
        System.out.printf(" 🟢 Abertos      : %d%n", openBugs);
        System.out.printf(" ✅ Fechados      : %d%n", closedBugs);

        System.out.println();
        System.out.println("═".repeat(55));
        boolean sprintOk = (openBugs == 0 || critBugs == 0) && pass >= 80;
        System.out.printf(" 🏁 STATUS GERAL DA SPRINT: %s%n",
                sprintOk ? "✅ APROVADA" : "⚠️  ATENÇÃO NECESSÁRIA");
        System.out.println("═".repeat(55));
    }

    public static void printBugLifecycle() {
        System.out.println("\n📌 CICLO DE VIDA DO BUG (Bug/Defect LifeCycle)");
        System.out.println("─".repeat(55));
        System.out.println(" NEW → ASSIGNED → OPEN → FIXED → PENDING RETEST");
        System.out.println("                              ↓");
        System.out.println("                           RETEST → VERIFIED → CLOSED");
        System.out.println("                              ↓ (regressão)");
        System.out.println("                           REOPENED → OPEN");
        System.out.println("        OPEN → DUPLICATE | REJECTED | DEFERRED | NOT A BUG");
        System.out.println("─".repeat(55));
    }

    public static void printWorkflowDiagram() {
        System.out.println("\n📌 FLUXO DE TRABALHO SCRUM (JIRA Board)");
        System.out.println("─".repeat(55));
        System.out.println(" TO DO → IN PROGRESS → READY FOR QA → DONE");
        System.out.println("            ↕                               ");
        System.out.println("         BLOCKED                            ");
        System.out.println("         REOPENED → IN PROGRESS             ");
        System.out.println("─".repeat(55));
    }
}
