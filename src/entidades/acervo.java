package BibliotecaJava.src.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class acervo {

    private List<ItemAcervo> itens;

    public acervo() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemAcervo item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo.");
        }
        boolean isbnDuplicado = itens.stream()
                .anyMatch(i -> i.getIsbn().equalsIgnoreCase(item.getIsbn()));
        if (isbnDuplicado) {
            throw new IllegalStateException("Já existe um item cadastrado com este ISBN.");
        }
        itens.add(item);
    }

    public boolean removerItem(String isbn) {
        Optional<ItemAcervo> itemEncontrado = buscarPorIsbn(isbn);
        if (itemEncontrado.isEmpty()) {
            return false;
        }
        if (!itemEncontrado.get().isDisponivel()) {
            throw new IllegalStateException("Não é possível remover um item que está emprestado.");
        }
        return itens.remove(itemEncontrado.get());
    }

    public Optional<ItemAcervo> buscarPorIsbn(String isbn) {
        return itens.stream()
                .filter(i -> i.getIsbn().equalsIgnoreCase(isbn))
                .findFirst();
    }

    public List<ItemAcervo> buscarPorTitulo(String titulo) {
        List<ItemAcervo> resultado = new ArrayList<>();
        for (ItemAcervo item : itens) {
            if (item.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(item);
            }
        }
        return resultado;
    }

    public List<ItemAcervo> listarDisponiveis() {
        List<ItemAcervo> disponiveis = new ArrayList<>();
        for (ItemAcervo item : itens) {
            if (item.isDisponivel()) {
                disponiveis.add(item);
            }
        }
        return disponiveis;
    }

    public List<ItemAcervo> listarTodos() {
        return new ArrayList<>(itens);
    }

    public int totalItens() {
        return itens.size();
    }
}