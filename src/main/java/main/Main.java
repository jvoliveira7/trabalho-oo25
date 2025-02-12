package main;

import controllers.AlunoController;
import io.javalin.Javalin;
import repository.AlunoRepository;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public"); 
        }).start(7000);

    
        AlunoRepository alunoRepository = new AlunoRepository();
        AlunoController alunoController = new AlunoController(alunoRepository); 

  
        app.get("/alunos", alunoController::listarTodos);
        app.get("/alunos/{matricula}", alunoController::buscarPorMatricula);
        app.post("/alunos", alunoController::salvar);
        app.delete("/alunos/{matricula}", alunoController::excluir);
    }
}