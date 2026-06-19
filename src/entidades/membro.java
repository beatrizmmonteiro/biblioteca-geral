package BibliotecaJava.src.entidades;

public class membro extends pessoa {

    private String matricula;
    private int limiteEmprestimos;
    private int emprestimosAtivos;
    private double saldoMultas;

    public membro(String nome, String cpf, String contato, String matricula, int limiteEmprestimos) {
        super(nome, cpf, contato);
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Matrícula não pode ser vazia.");
        }
        if (limiteEmprestimos <= 0) {
            throw new IllegalArgumentException("Limite de empréstimos deve ser maior que zero.");
        }
        this.matricula = matricula;
        this.limiteEmprestimos = limiteEmprestimos;
        this.emprestimosAtivos = 0;
        this.saldoMultas = 0.0;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public int getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    public double getSaldoMultas() {
        return saldoMultas;
    }

    public boolean podePegarEmprestado() {
        return saldoMultas == 0.0 && emprestimosAtivos < limiteEmprestimos;
    }

    public void registrarEmprestimo() {
        if (!podePegarEmprestado()) {
            throw new IllegalStateException("Membro não está apto a realizar novos empréstimos.");
        }
        emprestimosAtivos++;
    }

    public void registrarDevolucao() {
        if (emprestimosAtivos > 0) {
            emprestimosAtivos--;
        }
    }

    public void adicionarMulta(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("Valor de multa não pode ser negativo.");
        }
        this.saldoMultas += valor;
    }

    public void quitarMultas(double valorPago) {
        if (valorPago < 0) {
            throw new IllegalArgumentException("Valor pago não pode ser negativo.");
        }
        this.saldoMultas = Math.max(0, this.saldoMultas - valorPago);
    }

    @Override
    public String exibirInformacoes() {
        return String.format("Membro: %s | Matrícula: %s | Empréstimos: %d/%d | Multas: R$ %.2f",
                getNome(), matricula, emprestimosAtivos, limiteEmprestimos, saldoMultas);
    }
}