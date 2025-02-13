package dao;

import models.Disciplina;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {
    private Connection conexao;

    public DisciplinaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void salvar(Disciplina disciplina) {
        String sql = "INSERT INTO disciplinas (codigo, nome, ementa, carga_horaria, siape_professor) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, disciplina.getCodigo());
            stmt.setString(2, disciplina.getNome());
            stmt.setString(3, disciplina.getEmenta());
            stmt.setInt(4, disciplina.getCargaHoraria());
            stmt.setString(5, disciplina.getSiapeProfessor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Disciplina buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM disciplinas WHERE codigo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Disciplina(
                    rs.getString("codigo"),
                    rs.getString("nome"),
                    rs.getString("ementa"),
                    rs.getInt("carga_horaria"),
                    rs.getString("siape_professor")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Disciplina> listarTodos() {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT * FROM disciplinas";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                disciplinas.add(new Disciplina(
                    rs.getString("codigo"),
                    rs.getString("nome"),
                    rs.getString("ementa"),
                    rs.getInt("carga_horaria"),
                    rs.getString("siape_professor")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplinas;
    }

    public void excluir(String codigo) {
        String sql = "DELETE FROM disciplinas WHERE codigo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}