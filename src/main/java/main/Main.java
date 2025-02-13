package main;

import controllers.AlunoController;
import controllers.DisciplinasController;
import controllers.EscolaController;
import controllers.ProfessorController; // Importando o controlador de Professores

import io.javalin.Javalin;
import repository.AlunoRepository;
import repository.DisciplinaRepository;
import repository.EscolaRepository;
import repository.ProfessorRepository; // Importando o repositório de Professores

public class Main {
    public static void main(String[] args) {
        // Inicialização do Javalin com configuração de arquivos estáticos
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public"); // Define a pasta de arquivos estáticos
        }).start(7000);

        // Configuração dos repositórios e controladores para Alunos
        AlunoRepository alunoRepository = new AlunoRepository();
        AlunoController alunoController = new AlunoController(alunoRepository);

        // Configuração dos repositórios e controladores para Escolas
        EscolaRepository escolaRepository = new EscolaRepository();
        EscolaController escolaController = new EscolaController(escolaRepository);

        // Configuração dos repositórios e controladores para Disciplinas
        DisciplinaRepository disciplinaRepository = new DisciplinaRepository();
        DisciplinasController disciplinasController = new DisciplinasController(disciplinaRepository);

        // Configuração dos repositórios e controladores para Professores
        ProfessorRepository professorRepository = new ProfessorRepository();
        ProfessorController professorController = new ProfessorController(professorRepository);

        // Endpoints para Alunos
        app.get("/alunos", alunoController::listarTodos);
        app.get("/alunos/{matricula}", alunoController::buscarPorMatricula);
        app.post("/alunos", alunoController::salvar);
        app.delete("/alunos/{matricula}", alunoController::excluir);

        // Endpoints para Escolas
        app.post("/escolas", escolaController::cadastrar);
        app.get("/escolas", escolaController::listarTodos);
        app.get("/escolas/{nome}", escolaController::buscarPorNome);
        app.put("/escolas/{nome}", escolaController::editar);
        app.delete("/escolas/{nome}", escolaController::excluir);

        // Endpoints para Disciplinas
        app.post("/disciplinas", disciplinasController::cadastrar);
        app.get("/disciplinas", disciplinasController::listarTodos);
        app.get("/disciplinas/{codigo}", disciplinasController::buscarPorCodigo);
        app.put("/disciplinas/{codigo}", disciplinasController::editar);
        app.delete("/disciplinas/{codigo}", disciplinasController::excluir);

        // Endpoints para Professores
        app.get("/professores", professorController::listarTodos);
        app.get("/professores/{siape}", professorController::buscarPorSiape);
        app.post("/professores", professorController::salvar);
        app.delete("/professores/{siape}", professorController::excluir);
    }
}
