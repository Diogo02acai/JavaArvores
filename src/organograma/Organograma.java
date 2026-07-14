import java.util.ArrayList;
import java.util.List;

public class Organograma {
    private Funcionario raiz;

    public Organograma(String nomeRaiz, String cargoRaiz) {
        this.raiz = new Funcionario(nomeRaiz, cargoRaiz);
    }

    // 1. INSERÇÃO (Mantém igual)
    public boolean adicionarFuncionario(String nomeChefe, String nomeNovo, String cargoNovo) {
        Funcionario chefe = buscarNo(raiz, nomeChefe);
        if (chefe != null) {
            chefe.adicionarSubordinado(new Funcionario(nomeNovo, cargoNovo));
            return true;
        }
        return false;
    }

    // 2. BUSCA COM DESTAQUE
    public void buscarCadeiaHierarquica(String nomeAlvo) {
        List<String> cadeia = new ArrayList<>();
        if (buscarCaminho(raiz, nomeAlvo, cadeia)) {
            System.out.println("Cadeia Hierarquica para " + nomeAlvo + ":");
            for (int i = cadeia.size() - 1; i >= 0; i--) {
                System.out.println(" -> " + cadeia.get(i));
            }
        } else {
            System.out.println("Funcionario nao encontrado.");
        }
    }

    private boolean buscarCaminho(Funcionario atual, String alvo, List<String> caminho) {
        if (atual == null) return false;

        if (atual.getNome().equalsIgnoreCase(alvo)) {
            // NOVO: Adiciona o destaque visual quando encontra o alvo
            caminho.add("⭐ >>> " + atual.toString().toUpperCase() + " <<< ⭐");
            return true;
        }

        for (Funcionario sub : atual.getSubordinados()) {
            if (buscarCaminho(sub, alvo, caminho)) {
                caminho.add(atual.toString());
                return true;
            }
        }
        return false;
    }

    // 3. EXIBIÇÃO (Mantém igual)
    public void exibirOrganograma() {
        System.out.println("\n=== ORGANOGRAMA DA EMPRESA ===");
        exibirRecursivo(raiz, 0);
        System.out.println("==============================\n");
    }

    private void exibirRecursivo(Funcionario f, int nivel) {
        StringBuilder espaco = new StringBuilder();
        for (int i = 0; i < nivel; i++) espaco.append("    "); 
        System.out.println(espaco.toString() + "|-- " + f.toString());
        for (Funcionario sub : f.getSubordinados()) exibirRecursivo(sub, nivel + 1);
    }

    // 4. REMOÇÃO (Mantém igual)
    public boolean removerFuncionario(String nome) {
        if (raiz.getNome().equalsIgnoreCase(nome)) return false;
        return removerRecursivo(raiz, null, nome);
    }

    private boolean removerRecursivo(Funcionario atual, Funcionario pai, String nome) {
        if (atual.getNome().equalsIgnoreCase(nome)) {
            for (Funcionario sub : atual.getSubordinados()) pai.adicionarSubordinado(sub);
            pai.removerSubordinado(atual);
            return true;
        }
        for (Funcionario sub : atual.getSubordinados()) {
            if (removerRecursivo(sub, atual, nome)) return true;
        }
        return false;
    }

    // NOVO: 5. PROMOVER/REBAIXAR (Mover de Chefe)
    public boolean moverFuncionario(String nome, String novoChefe, String novoCargo) {
        if (raiz.getNome().equalsIgnoreCase(nome)) return false; // Não move a raiz
        
        Funcionario f = buscarNo(raiz, nome);
        Funcionario chefeDestino = buscarNo(raiz, novoChefe);
        Funcionario paiAtual = buscarPai(raiz, nome);

        if (f != null && chefeDestino != null && paiAtual != null) {
            paiAtual.removerSubordinado(f);
            chefeDestino.adicionarSubordinado(f);
            if (!novoCargo.trim().isEmpty()) {
                f.setCargo(novoCargo); // Atualiza o cargo
            }
            return true;
        }
        return false;
    }

    // NOVO: 6. COMPARAR HIERARQUIA
    public void compararHierarquia(String nome1, String nome2) {
        int nivel1 = obterNivel(raiz, nome1, 0);
        int nivel2 = obterNivel(raiz, nome2, 0);

        if (nivel1 == -1 || nivel2 == -1) {
            System.out.println("Um ou ambos os funcionarios nao existem.");
            return;
        }

        System.out.println("\nResultado da Comparação:");
        if (nivel1 < nivel2) {
            System.out.println("✔️ " + nome1 + " possui MAIOR hierarquia (esta acima) de " + nome2);
        } else if (nivel1 > nivel2) {
            System.out.println("✔️ " + nome2 + " possui MAIOR hierarquia (esta acima) de " + nome1);
        } else {
            System.out.println("⚖️ " + nome1 + " e " + nome2 + " estao no MESMO nivel hierarquico.");
        }
    }

    // --- MÉTODOS AUXILIARES ---
    private Funcionario buscarNo(Funcionario atual, String nome) {
        if (atual.getNome().equalsIgnoreCase(nome)) return atual;
        for (Funcionario sub : atual.getSubordinados()) {
            Funcionario encontrado = buscarNo(sub, nome);
            if (encontrado != null) return encontrado;
        }
        return null;
    }

    private Funcionario buscarPai(Funcionario atual, String nomeFilho) {
        for (Funcionario sub : atual.getSubordinados()) {
            if (sub.getNome().equalsIgnoreCase(nomeFilho)) return atual;
            Funcionario encontrado = buscarPai(sub, nomeFilho);
            if (encontrado != null) return encontrado;
        }
        return null;
    }

    private int obterNivel(Funcionario atual, String nome, int nivelAtual) {
        if (atual.getNome().equalsIgnoreCase(nome)) return nivelAtual;
        for (Funcionario sub : atual.getSubordinados()) {
            int nivel = obterNivel(sub, nome, nivelAtual + 1);
            if (nivel != -1) return nivel;
        }
        return -1;
    }
}
