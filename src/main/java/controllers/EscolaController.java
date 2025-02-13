package controllers;

import io.javalin.http.Context;
import models.Escola;
import repository.EscolaRepository;

public class EscolaController {
    private EscolaRepository escolaRepository;

    public EscolaController(EscolaRepository escolaRepository) {
        this.escolaRepository = escolaRepository;
    }

    public void cadastrar(Context ctx) {
        try {
            Escola escola = ctx.bodyAsClass(Escola.class); // Converte o JSON em um objeto Escola
            escolaRepository.salvar(escola); // Salva a escola no repositório
            ctx.status(201).json(escola); // Retorna a escola cadastrada em JSON
        } catch (Exception e) {
            ctx.status(500).json("Erro ao cadastrar escola: " + e.getMessage()); // Retorna uma mensagem de erro em JSON
        }
    }

    public void listarTodos(Context ctx) {
        try {
            ctx.json(escolaRepository.listarTodos()); // Retorna a lista de escolas em JSON
        } catch (Exception e) {
            ctx.status(500).json("Erro ao listar escolas: " + e.getMessage()); // Retorna uma mensagem de erro em JSON
        }
    }

    public void buscarPorNome(Context ctx) {
        try {
            String nome = ctx.pathParam("nome"); // Captura o nome da escola da URL
            Escola escola = escolaRepository.buscarPorNome(nome); // Busca a escola no repositório

            if (escola != null) {
                ctx.json(escola); // Retorna a escola encontrada em JSON
            } else {
                ctx.status(404).json("Escola não encontrada."); // Retorna uma mensagem de erro em JSON
            }
        } catch (Exception e) {
            ctx.status(500).json("Erro ao buscar escola: " + e.getMessage()); // Retorna uma mensagem de erro em JSON
        }
    }

    public void editar(Context ctx) {
        try {
            String nome = ctx.pathParam("nome"); // Captura o nome da escola da URL
            Escola escolaAtualizada = ctx.bodyAsClass(Escola.class); // Converte o JSON em um objeto Escola

            Escola escolaExistente = escolaRepository.buscarPorNome(nome); // Busca a escola existente

            if (escolaExistente != null) {
                escolaRepository.editar(nome, escolaAtualizada); // Atualiza a escola no repositório
                ctx.status(200).json(escolaAtualizada); // Retorna a escola atualizada em JSON
            } else {
                ctx.status(404).json("Escola não encontrada."); // Retorna uma mensagem de erro em JSON
            }
        } catch (Exception e) {
            ctx.status(500).json("Erro ao editar escola: " + e.getMessage()); // Retorna uma mensagem de erro em JSON
        }
    }

    public void excluir(Context ctx) {
        try {
            String nome = ctx.pathParam("nome"); // Captura o nome da escola da URL
            escolaRepository.excluir(nome); // Exclui a escola do repositório
            ctx.status(204); // Retorna uma resposta de sucesso sem conteúdo
        } catch (Exception e) {
            ctx.status(500).json("Erro ao excluir escola: " + e.getMessage()); // Retorna uma mensagem de erro em JSON
        }
    }
}