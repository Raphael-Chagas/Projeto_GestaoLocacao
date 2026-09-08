package dao;

import connection.conexao;
import model.Carro;

import java.sql.*;
import java.util.*;

public class CarroDAO {

    public void inserir(Carro carro) {
        String sql = "INSERT INTO veiculo (placa, marca, modelo, categoria, quilometragem, status, data_cadastro) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, carro.getPlaca());
            stmt.setString(2, carro.getMarca());
            stmt.setString(3, carro.getModelo());
            stmt.setString(4, carro.getCategoria());
            stmt.setString(5, carro.getQuilometragem());
            stmt.setString(6, carro.getStatus());
            stmt.setTimestamp(7, Timestamp.valueOf(carro.getDataCadastro()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    carro.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar veículo: " + e.getMessage(), e);
        }
    }

    public Carro buscarPorId(int id) {
        String sql = "SELECT * FROM veiculo WHERE id = ?";
        Carro carro = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    carro = extrairCarro(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar veículo por ID: " + e.getMessage(), e);
        }

        return carro;
    }

    public List<Carro> listarTodos() {
        String sql = "SELECT * FROM veiculo ORDER BY marca, modelo ASC";
        List<Carro> carros = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                carros.add(extrairCarro(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veículos: " + e.getMessage(), e);
        }

        return carros;
    }

    public void atualizar(Carro carro) {
        String sql = "UPDATE veiculo SET placa = ?, marca = ?, modelo = ?, categoria = ?, "
                   + "quilometragem = ?, status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carro.getPlaca());
            stmt.setString(2, carro.getMarca());
            stmt.setString(3, carro.getModelo());
            stmt.setString(4, carro.getCategoria());
            stmt.setString(5, carro.getQuilometragem());
            stmt.setString(6, carro.getStatus());
            stmt.setInt(7, carro.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar veículo: " + e.getMessage(), e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM veiculo WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir veículo: " + e.getMessage(), e);
        }
    }

    private Carro extrairCarro(ResultSet rs) throws SQLException {
        Carro carro = new Carro();
        carro.setId(rs.getInt("id"));
        carro.setPlaca(rs.getString("placa"));
        carro.setMarca(rs.getString("marca"));
        carro.setModelo(rs.getString("modelo"));
        carro.setCategoria(rs.getString("categoria"));
        carro.setQuilometragem(rs.getString("quilometragem"));
        carro.setStatus(rs.getString("status"));

        Timestamp timestamp = rs.getTimestamp("data_cadastro");
        if (timestamp != null) {
            carro.setDataCadastro(timestamp.toLocalDateTime());
        }

        return carro;
    }
}