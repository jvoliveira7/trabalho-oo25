package controllers;

import io.javalin.http.Context;
import models.Aluno;
import repository.AlunoRepository;

public class AlunoController {
    private AlunoRepository alunoRepository;


    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void listarTodos(Context ctx) {
        ctx.json(alunoRepository.listarTodos());
    }

    public void buscarPorMatricula(Context ctx) {
        int matricula = Integer.parseInt(ctx.pathParam("matricula"));
        var aluno = alunoRepository.buscarPorMatricula(matricula);
        if (aluno != null) {
            ctx.json(aluno);
        } else {
            ctx.status(404).result("Aluno não encontrado");
        }
    }

    public void salvar(Context ctx) {
        try {
            Aluno aluno = ctx.bodyAsClass(Aluno.class); 
            alunoRepository.salvar(aluno); 
            ctx.status(201).result("Aluno criado com sucesso"); 
        } catch (Exception e) {
            ctx.status(500).result("Erro ao cadastrar aluno: " + e.getMessage()); 
        }
    }
    public void excluir(Context ctx) {
        int matricula = Integer.parseInt(ctx.pathParam("matricula"));
        alunoRepository.excluir(matricula);
        ctx.status(204).result("Aluno excluído com sucesso");
    }
}