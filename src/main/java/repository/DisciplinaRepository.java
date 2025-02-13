package repository;

import models.Disciplina;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaRepository {
    private List<Disciplina> disciplinas = new ArrayList<>();

    public void salvar(Disciplina disciplina) {
        disciplinas.add(disciplina); // Adiciona a disciplina à lista
    }

    public List<Disciplina> listarTodos() {
        return disciplinas; // Retorna a lista de disciplinas
    }

    public Disciplina buscarPorCodigo(String codigo) {
        return disciplinas.stream()
                .filter(disciplina -> disciplina.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null); // Retorna a disciplina com o código correspondente
    }

    public void editar(String codigo, Disciplina disciplinaAtualizada) {
        disciplinas.removeIf(disciplina -> disciplina.getCodigo().equals(codigo)); // Remove a disciplina existente
        disciplinas.add(disciplinaAtualizada); // Adiciona a disciplina atualizada
    }

    public void excluir(String codigo) {
        disciplinas.removeIf(disciplina -> disciplina.getCodigo().equals(codigo)); // Remove a disciplina com o código correspondente
    }
}