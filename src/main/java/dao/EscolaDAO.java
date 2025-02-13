package dao;

import models.Escola;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscolaDAO {
    private Connection conexao;

    public EscolaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void salvar(Escola escola) {
        String sql = "INSERT INTO escolas (nome, endereco, contato) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, escola.getNome());
            stmt.setString(2, escola.getEndereco());
            stmt.setString(3, escola.getContato());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Escola buscarPorNome(String nome) {
        String sql = "SELECT * FROM escolas WHERE nome = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Escola(
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("contato")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Escola> listarTodos() {
        List<Escola> escolas = new ArrayList<>();
        String sql = "SELECT * FROM escolas";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                escolas.add(new Escola(
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("contato")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return escolas;
    }

    public void editar(String nome, Escola escolaAtualizada) {
        String sql = "UPDATE escolas SET endereco = ?, contato = ? WHERE nome = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, escolaAtualizada.getEndereco());
            stmt.setString(2, escolaAtualizada.getContato());
            stmt.setString(3, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(String nome) {
        String sql = "DELETE FROM escolas WHERE nome = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
