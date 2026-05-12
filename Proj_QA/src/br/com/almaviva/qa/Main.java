package br.com.almaviva.qa;

import br.com.almaviva.qa.model.Bug;
import br.com.almaviva.qa.model.TestCase;
import br.com.almaviva.qa.model.TestCycle;
import br.com.almaviva.qa.model.UserStory;
import br.com.almaviva.qa.model.enums.Priority;
import br.com.almaviva.qa.model.enums.TestResult;
import br.com.almaviva.qa.service.*;
import br.com.almaviva.qa.util.ReportGenerator;
import java.time.LocalDate;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        banner();

        UserStoryService  usService    = new UserStoryService();
        TestCaseService   tcService    = new TestCaseService();
        BugService        bugService   = new BugService();
        TestCycleService  cycleService = new TestCycleService();
        section("PARTE 1 – FLUXOS DE TRABALHO");
        ReportGenerator.printWorkflowDiagram();
        ReportGenerator.printBugLifecycle();
        section("PARTE 2 – USER STORIES");

        UserStory usLogin = usService.create(
            "Autenticação",
            "Login de Usuário",
            "cliente cadastrado",
            "realizar login com e-mail e senha",
            "assim posso acessar minha conta e fazer compras com segurança",
            Priority.CRITICA, 3
        );
        usLogin.addAcceptanceCriteria(
            "Dado que o usuário tem conta ativa, " +
            "quando informar credenciais corretas, então deve acessar o painel.");
        usLogin.addAcceptanceCriteria(
            "Quando informar senha incorreta 3 vezes, então conta deve ser bloqueada.");
        usLogin.addAcceptanceCriteria(
            "Campos e-mail e senha não devem aceitar entrada vazia.");

        UserStory usCart = usService.create(
            "Compras",
            "Adicionar Produto ao Carrinho",
            "cliente autenticado",
            "adicionar produtos ao carrinho de compras",
            "posso organizar minha compra antes de finalizar o pedido",
            Priority.ALTA, 5
        );
        usCart.addAcceptanceCriteria(
            "Quando clicar em 'Add to cart', o contador do carrinho deve incrementar.");
        usCart.addAcceptanceCriteria(
            "Deve ser possível remover um item já adicionado ao carrinho.");

        UserStory usCheckout = usService.create(
            "Compras",
            "Finalizar Compra (Checkout)",
            "cliente com itens no carrinho",
            "finalizar a compra informando dados de entrega e pagamento",
            "posso receber os produtos em meu endereço",
            Priority.CRITICA, 8
        );
        usCheckout.addAcceptanceCriteria(
            "Dado que o carrinho tem itens, quando clicar em Checkout, " +
            "deve exibir formulário de entrega.");
        usCheckout.addAcceptanceCriteria(
            "Campos obrigatórios: First Name, Last Name, Zip/Postal Code.");
        usCheckout.addAcceptanceCriteria(
            "Ao confirmar, deve exibir tela de confirmação com número do pedido.");

    
        usService.advanceStatus("US-001");
        usService.advanceStatus("US-001"); 
        usService.advanceStatus("US-002"); 
        usService.printSummary();

        System.out.println("\n--- Detalhe da US-001 ---");
        usService.findById("US-001").ifPresent(System.out::println);

    
        section("PARTE 3 – CASOS DE TESTE");
        TestCase tcLoginValido = tcService.createStepByStep(
            "Login com credenciais válidas",
            Priority.CRITICA,
            "Acessar https://www.saucedemo.com e estar na tela de login",
            List.of(
                "Inserir 'standard_user' no campo Username",
                "Inserir 'secret_sauce' no campo Password",
                "Clicar no botão 'Login'"
            ),
            "Usuário é redirecionado para a página de produtos (inventory.html)",
            "US-001"
        );

        TestCase tcLoginInvalido = tcService.createStepByStep(
            "Login com senha incorreta",
            Priority.ALTA,
            "Acessar https://www.saucedemo.com e estar na tela de login",
            List.of(
                "Inserir 'standard_user' no campo Username",
                "Inserir 'senha_errada' no campo Password",
                "Clicar no botão 'Login'"
            ),
            "Mensagem de erro: 'Username and password do not match any user...'",
            "US-001"
        );


        TestCase tcAddCart = tcService.createBDD(
            "Adicionar produto ao carrinho",
            Priority.ALTA,
            "Dado que o usuário está autenticado na página de produtos",
            "Quando clicar no botão 'Add to cart' do produto 'Sauce Labs Backpack'",
            "Então o contador do carrinho deve exibir '1' e o botão deve mudar para 'Remove'",
            "US-002", true
        );

        TestCase tcCheckout = tcService.createBDD(
            "Finalizar compra com dados válidos",
            Priority.CRITICA,
            "Dado que o usuário tem ao menos 1 produto no carrinho",
            "Quando preencher First Name, Last Name e Zip Code e confirmar o checkout",
            "Então deve exibir a tela 'Checkout: Complete!' com a mensagem de sucesso",
            "US-003", true
        );

        usLogin.addTestCase(tcLoginValido);
        usLogin.addTestCase(tcLoginInvalido);
        usCart.addTestCase(tcAddCart);
        usCheckout.addTestCase(tcCheckout);

        section("PARTE 4 – CICLO DE TESTES");

        TestCycle sprint1Cycle = cycleService.create(
            "Ciclo Sprint 1 – Autenticação e Carrinho",
            "Sprint 1",
            LocalDate.now(),
            LocalDate.now().plusWeeks(2)
        );

        cycleService.addTestCase(sprint1Cycle.getId(), tcLoginValido);
        cycleService.addTestCase(sprint1Cycle.getId(), tcLoginInvalido);
        cycleService.addTestCase(sprint1Cycle.getId(), tcAddCart);
        cycleService.addTestCase(sprint1Cycle.getId(), tcCheckout);
        section("PARTE 5 – EXECUÇÃO DOS TESTES");

        tcService.execute("TC-001", TestResult.PASSOU,
            "Login efetuado com sucesso. Redirecionado para inventory.html.");

        tcService.execute("TC-002", TestResult.PASSOU,
            "Mensagem de erro exibida corretamente. Usuário não logado.");

        tcService.execute("TC-003", TestResult.FALHOU,
            "Contador não atualizou imediatamente – possível bug de UI.");

        tcService.execute("TC-004", TestResult.PASSOU,
            "Checkout concluído. Tela de confirmação exibida corretamente.");

        tcService.printExecutionSummary();
        cycleService.printCycleReport(sprint1Cycle.getId());

        section("PARTE 6 – CICLO DE VIDA DO BUG");

        Bug bugCounter = bugService.report(
            "Contador do carrinho não atualiza em tempo real",
            "Ao adicionar produto, o ícone do carrinho demora ~2s para atualizar.",
            "1. Autenticar\n2. Acessar página de produtos\n3. Clicar em 'Add to cart'",
            "Contador do carrinho deve atualizar imediatamente para '1'",
            "Contador permanece em '0' por cerca de 2 segundos",
            Priority.ALTA,
            "QA – Aluno Bootcamp AlmaViva",
            "TC-003",
            "US-002"
        );

        System.out.println("\n🔄 Simulando ciclo de vida do bug...");
        bugService.assign(bugCounter.getId(), "Dev - Michelle");
        bugService.open(bugCounter.getId());
        bugService.fix(bugCounter.getId());
        bugService.pendingRetest(bugCounter.getId());
        bugService.retest(bugCounter.getId());
        bugService.verify(bugCounter.getId());
        bugService.close(bugCounter.getId());

        System.out.println("\n📋 Histórico do bug:");
        bugService.findById(bugCounter.getId())
                  .ifPresent(b -> System.out.println(b.toDetailString()));

        bugService.printSummary();

        section("RELATÓRIO FINAL DA SPRINT");

        ReportGenerator.printSprintReport(
            "Sprint 1 – SwagLabs Shopping",
            usService.findAll(),
            tcService.findAll(),
            bugService.findAll()
        );

        System.out.println("\n✅ Simulação concluída!");
        System.out.println("   Repositório: https://github.com/MichelleKoalin/qa-bootcamp-almaviva-dio");
        System.out.println();
    }

    private static void banner() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║    SISTEMA DE GERENCIAMENTO DE QA                    ║");
        System.out.println("║    Bootcamp QA – AlmaViva Solutions + DIO            ║");
        System.out.println("║    O dia a dia de um QA: Testes Manuais Funcionais   ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void section(String title) {
        System.out.println();
        System.out.println("━".repeat(56));
        System.out.printf("  %s%n", title);
        System.out.println("━".repeat(56));
    }
}
