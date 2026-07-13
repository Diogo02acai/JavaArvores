import java.util.ArrayList;
import java.util.List;

public class Funcionario {
    private String nome;
    private String cargo;
    private List<Funcionario> subordinados;

    public Funcionario(String nome, String cargo) {
        this.nome = nome;
        this.cargo = cargo;
        this.subordinados = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public String getCargo() { return cargo; }
    public List<Funcionario> getSubordinados() { return subordinados; }

    // NOVO: Método para alterar o cargo na promoção/rebaixamento
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void adicionarSubordinado(Funcionario f) { this.subordinados.add(f); }
    public void removerSubordinado(Funcionario f) { this.subordinados.remove(f); }
    
    @Override
    public String toString() { return nome + " (" + cargo + ")"; }
}