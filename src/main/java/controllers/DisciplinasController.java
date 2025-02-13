package controllers;

import io.javalin.http.Context;
import repository.DisciplinaRepository;
import models.Disciplina;

public class DisciplinasController {
    private DisciplinaRepository disciplinaRepository;

    public DisciplinasController(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public void cadastrar(Context ctx) {
        try {
            Disciplina disciplina = ctx.bodyAsClass(Disciplina.class);
            disciplinaRepository.salvar(disciplina);
            ctx.status(201).json(disciplina);
        } catch (Exception e) {
            ctx.status(500).json("Erro ao cadastrar disciplina: " + e.getMessage());
        }
    }

    public void listarTodos(Context ctx) {
        try {
            ctx.json(disciplinaRepository.listarTodos());
        } catch (Exception e) {
            ctx.status(500).json("Erro ao listar disciplinas: " + e.getMessage());
        }
    }

    public void buscarPorCodigo(Context ctx) {
        try {
            String codigo = ctx.pathParam("codigo");
            Disciplina disciplina = disciplinaRepository.buscarPorCodigo(codigo);

            if (disciplina != null) {
                ctx.json(disciplina);
            } else {
                ctx.status(404).json("Disciplina não encontrada.");
            }
        } catch (Exception e) {
            ctx.status(500).json("Erro ao buscar disciplina: " + e.getMessage());
        }
    }

    public void editar(Context ctx) {
        try {
            String codigo = ctx.pathParam("codigo");
            Disciplina disciplinaAtualizada = ctx.bodyAsClass(Disciplina.class);
            
            Disciplina disciplinaExistente = disciplinaRepository.buscarPorCodigo(codigo);
            if (disciplinaExistente != null) {
                disciplinaRepository.editar(codigo, disciplinaAtualizada);
                ctx.status(200).json(disciplinaAtualizada);
            } else {
                ctx.status(404).json("Disciplina não encontrada.");
            }
        } catch (Exception e) {
            ctx.status(500).json("Erro ao editar disciplina: " + e.getMessage());
        }
    }

    public void excluir(Context ctx) {
        try {
            String codigo = ctx.pathParam("codigo");
            Disciplina disciplina = disciplinaRepository.buscarPorCodigo(codigo);
            
            if (disciplina != null) {
                disciplinaRepository.excluir(codigo);
                ctx.status(204);
            } else {
                ctx.status(404).json("Disciplina não encontrada.");
            }
        } catch (Exception e) {
            ctx.status(500).json("Erro ao excluir disciplina: " + e.getMessage());
        }
    }
}
