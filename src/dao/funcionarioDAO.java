package dao;

import connection.conexao;
import model.Funcionario;

import java.sql.*;
import java.util.*;

public class FuncionarioDAO {

    public void inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (nome, cpf, login, senha, cargo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getLogin());
            stmt.setString(4, funcionario.getSenha());
            stmt.setString(5, funcionario.getCargo());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    funcionario.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar funcionário: " + e.getMessage(), e);
        }
    }

    public Funcionario buscarPorId(int id) {
        String sql = "SELECT * FROM funcionario WHERE id = ?";
        Funcionario funcionario = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    funcionario = extrairFuncionario(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar funcionário por ID: " + e.getMessage(), e);
        }

        return funcionario;
    }

    public List<Funcionario> listarTodos() {
        String sql = "SELECT * FROM funcionario ORDER BY nome ASC";
        List<Funcionario> funcionarios = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                funcionarios.add(extrairFuncionario(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar funcionários: " + e.getMessage(), e);
        }

        return funcionarios;
    }

    public void atualizar(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET nome = ?, cpf = ?, login = ?, senha = ?, cargo = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getLogin());
            stmt.setString(4, funcionario.getSenha());
            stmt.setString(5, funcionario.getCargo());
            stmt.setInt(6, funcionario.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar funcionário: " + e.getMessage(), e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM funcionario WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir funcionário: " + e.getMessage(), e);
        }
    }

    public Funcionario autenticar(String login, String senha) {
        String sql = "SELECT * FROM funcionario WHERE login = ? AND senha = ?";
        Funcionario funcionario = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    funcionario = extrairFuncionario(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao autenticar funcionário: " + e.getMessage(), e);
        }

        return funcionario;
    }

    private Funcionario extrairFuncionario(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(rs.getInt("id"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setCpf(rs.getString("cpf"));
        funcionario.setLogin(rs.getString("login"));
        funcionario.setSenha(rs.getString("senha"));
        funcionario.setCargo(rs.getString("cargo"));
        return funcionario;
    }
}