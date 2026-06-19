package BibliotecaJava.src.entidades;


public class funcionario extends pessoa {

    private String cargo;
    private String turno;
    private String senha;

    public funcionario(String nome, String cpf, String contato, String cargo, String turno, String senha) {
        super(nome, cpf, contato);
        if (cargo == null || cargo.isBlank()) {
            throw new IllegalArgumentException("Cargo não pode ser vazio.");
        }
        if (senha == null || senha.length() < 4) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 4 caracteres.");
        }
        this.cargo = cargo;
        this.turno = turno;
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public String getTurno() {
        return turno;
    }

    public boolean autenticar(String senhaDigitada) {
        return senha != null && senha.equals(senhaDigitada);
    }

    public void alterarSenha(String senhaAtual, String novaSenha) {
        if (!autenticar(senhaAtual)) {
            throw new IllegalStateException("Senha atual incorreta.");
        }
        if (novaSenha == null || novaSenha.length() < 4) {
            throw new IllegalArgumentException("Nova senha deve ter pelo menos 4 caracteres.");
        }
        this.senha = novaSenha;
    }

    @Override
    public String exibirInformacoes() {
        return String.format("Funcionário: %s | Cargo: %s | Turno: %s", getNome(), cargo, turno);
    }
}