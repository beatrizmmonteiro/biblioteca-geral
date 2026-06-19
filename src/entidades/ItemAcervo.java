package BibliotecaJava.src.entidades;

public abstract class ItemAcervo {

    private String titulo;
    private String isbn;
    private boolean disponivel;

    public ItemAcervo(String titulo, String isbn) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título não pode ser vazio.");
        }
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN não pode ser vazio.");
        }
        this.titulo = titulo;
        this.isbn = isbn;
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void marcarComoEmprestado() {
        if (!disponivel) {
            throw new IllegalStateException("Item já está emprestado.");
        }
        this.disponivel = false;
    }

    public void marcarComoDisponivel() {
        this.disponivel = true;
    }

    public abstract double calcularMulta(int diasAtraso);

    public abstract String exibirInformacoes();

    @Override
    public String toString() {
        return exibirInformacoes();
    }
}