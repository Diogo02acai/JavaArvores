import java.util.Scanner;

public class TesteOrganograma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Mantemos uma estrutura base para facilitar a apresentação
        Organograma empresa = new Organograma("Alice", "CEO");
        empresa.adicionarFuncionario("Alice", "Beto", "Diretor de TI");
        empresa.adicionarFuncionario("Alice", "Carla", "Diretora de RH");
        empresa.adicionarFuncionario("Beto", "Daniel", "Gerente de Redes");
        empresa.adicionarFuncionario("Beto", "Eduarda", "Gerente de Software");
        empresa.adicionarFuncionario("Eduarda", "Fernando", "Desenvolvedor Senior");
        
        int opcao = -1;

        System.out.println("Bem-vindo ao Sistema de RH!");

        while (opcao != 0) {
            System.out.println("\n================ MENU ================");
            System.out.println("1. Exibir Organograma");
            System.out.println("2. Adicionar Funcionario");
            System.out.println("3. Remover Funcionario");
            System.out.println("4. Buscar Funcionario (Com Destaque)");
            System.out.println("5. Promover/Rebaixar (Mudar de Chefe)");
            System.out.println("6. Comparar Hierarquia (Quem manda mais)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do teclado (MUITO IMPORTANTE)

            switch (opcao) {
                case 1:
                    empresa.exibirOrganograma();
                    break;
                
                case 2:
                    System.out.print("Nome do novo funcionario: ");
                    String nomeNovo = scanner.nextLine();
                    System.out.print("Cargo do novo funcionario: ");
                    String cargo = scanner.nextLine();
                    System.out.print("Nome do chefe direto dele: ");
                    String chefe = scanner.nextLine();
                    
                    if (empresa.adicionarFuncionario(chefe, nomeNovo, cargo)) {
                        System.out.println("✅ Funcionario adicionado com sucesso!");
                    } else {
                        System.out.println("❌ Erro: Chefe nao encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Nome do Funcionario a ser demitido: ");
                    String demitido = scanner.nextLine();
                    if (empresa.removerFuncionario(demitido)) {
                        System.out.println("✅ " + demitido + " removido. Equipe remanejada para o andar de cima.");
                    } else {
                        System.out.println("❌ Erro: Funcionario nao encontrado ou e o CEO.");
                    }
                    break;

                case 4:
                    System.out.print("Digite o nome do funcionario para buscar: ");
                    String busca = scanner.nextLine();
                    System.out.println();
                    empresa.buscarCadeiaHierarquica(busca);
                    break;

                case 5:
                    System.out.print("Nome do funcionario a ser movido: ");
                    String nomeMover = scanner.nextLine();
                    System.out.print("Nome do NOVO chefe dele: ");
                    String novoChefe = scanner.nextLine();
                    System.out.print("Novo Cargo (Deixe em branco para manter o mesmo): ");
                    String novoCargo = scanner.nextLine();
                    
                    if (empresa.moverFuncionario(nomeMover, novoChefe, novoCargo)) {
                        System.out.println("✅ Funcionario realocado com sucesso!");
                    } else {
                        System.out.println("❌ Erro ao realocar funcionario.");
                    }
                    break;

                case 6:
                    System.out.print("Nome do primeiro funcionario: ");
                    String func1 = scanner.nextLine();
                    System.out.print("Nome do segundo funcionario: ");
                    String func2 = scanner.nextLine();
                    empresa.compararHierarquia(func1, func2);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
        }
        scanner.close();
    }
}
