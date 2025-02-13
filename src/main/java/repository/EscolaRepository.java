package repository;

import java.util.ArrayList;
import java.util.List;

import models.Escola;

public class EscolaRepository {
    private List<Escola> escolas = new ArrayList<>();

    public void salvar(Escola escola) {
        escolas.add(escola); // Adiciona a escola à lista
    }

    public List<Escola> listarTodos() {
        return escolas; // Retorna a lista de escolas
    }

    public Escola buscarPorNome(String nome) {
        return escolas.stream()
                .filter(escola -> escola.getNome().equals(nome))
                .findFirst()
                .orElse(null); // Retorna a escola com o nome correspondente
    }

    public void editar(String nome, Escola escolaAtualizada) {
        escolas.removeIf(escola -> escola.getNome().equals(nome)); // Remove a escola existente
        escolas.add(escolaAtualizada); // Adiciona a escola atualizada
    }

    public void excluir(String nome) {
        escolas.removeIf(escola -> escola.getNome().equals(nome)); // Remove a escola com o nome correspondente
    }
}