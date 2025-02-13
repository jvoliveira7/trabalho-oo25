package repository;

import models.Professor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfessorRepository {
    private List<Professor> professores = new ArrayList<>();

    // Método para listar todos os professores
    public List<Professor> listarTodos() {
        return professores;
    }

    // Método para buscar um professor pelo SIAPE
    public Optional<Professor> buscarPorSiape(String siape) {
        return professores.stream()
                .filter(professor -> professor.getSiape().equals(siape))
                .findFirst();
    }

    // Método para salvar um novo professor
    public void salvar(Professor professor) {
        professores.add(professor);
    }

    // Método para excluir um professor pelo SIAPE
    public boolean excluir(String siape) {
        return professores.removeIf(professor -> professor.getSiape().equals(siape));
    }
}
