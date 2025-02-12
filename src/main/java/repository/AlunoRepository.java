package repository;

import java.util.ArrayList;
import java.util.List;

import models.Aluno;

public class AlunoRepository {
    private List<Aluno> alunos = new ArrayList<>();

    public void salvar(Aluno aluno) {
        alunos.add(aluno); 
    }

    public List<Aluno> listarTodos() {
        return alunos;
    }

    public Aluno buscarPorMatricula(int matricula) {
        return alunos.stream()
                .filter(aluno -> aluno.getMatricula() == matricula)
                .findFirst()
                .orElse(null); 
    }

    public void excluir(int matricula) {
        alunos.removeIf(aluno -> aluno.getMatricula() == matricula); 
    }
}