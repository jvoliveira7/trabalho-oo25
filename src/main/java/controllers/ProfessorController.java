package controllers;

import io.javalin.http.Context;
import models.Professor;
import repository.ProfessorRepository;
import java.time.LocalDate;
import java.util.Map;

public class ProfessorController {
    private final ProfessorRepository professorRepository;

    public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    // Método para listar todos os professores
    public void listarTodos(Context ctx) {
        ctx.json(professorRepository.listarTodos());
    }

    // Método para buscar professor por SIAPE
    public void buscarPorSiape(Context ctx) {
        String siape = ctx.pathParam("siape");
        professorRepository.buscarPorSiape(siape)
            .ifPresentOrElse(
                professor -> ctx.json(professor),
                () -> ctx.status(404).result("Professor não encontrado")
            );
    }

    // Método para salvar um novo professor
    public void salvar(Context ctx) {
        try {
            Map<String, String> body = ctx.bodyAsClass(Map.class);

            // Criando professor com base no JSON recebido
            Professor professor = new Professor(
                body.get("siape"),
                body.get("nome"),
                body.get("cpf"),
                LocalDate.parse(body.get("dataNascimento")),
                body.get("endereco"),
                body.get("telefone"),
                body.get("email")
            );

            professorRepository.salvar(professor);
            ctx.status(201).json(professor);
        } catch (Exception e) {
            ctx.status(400).result("Erro ao processar requisição: " + e.getMessage());
        }
    }

    // Método para excluir um professor pelo SIAPE
    public void excluir(Context ctx) {
        String siape = ctx.pathParam("siape");
        if (professorRepository.excluir(siape)) {
            ctx.status(204);
        } else {
            ctx.status(404).result("Professor não encontrado");
        }
    }
}
