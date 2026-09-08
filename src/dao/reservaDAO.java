package dao;

import connection.conexao;
import model.Cliente;
import model.Reserva;

import java.sql.*;
import java.util.*;

public class ReservaDAO {

    public void inserir(Reserva reserva) {
        String sql = "INSERT INTO reserva (token, cliente_id, categoria_veiculo, "
                   + "data_retirada_prevista, data_devolucao_prevista, local_retirada, "
                   + "opcionais, estimativa_preco, status, data_criacao) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, reserva.getToken());
            stmt.setInt(2, reserva.getCliente().getId()); // Chave estrangeira obtida do objeto Cliente
            stmt.setString(3, reserva.getCategoriaVeiculo());
            stmt.setTimestamp(4, Timestamp.valueOf(reserva.getDataRetiradaPrevista()));
            stmt.setTimestamp(5, Timestamp.valueOf(reserva.getDataDevolucaoPrevista()));
            stmt.setString(6, reserva.getLocalRetirada());
            stmt.setString(7, reserva.getOpcionais());
            stmt.setBigDecimal(8, reserva.getEstimativaPreco());
            stmt.setString(9, reserva.getStatus());
            stmt.setTimestamp(10, Timestamp.valueOf(reserva.getDataCriacao()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    reserva.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar reserva: " + e.getMessage(), e);
        }
    }

    public Reserva buscarPorId(int id) {
        String sql = "SELECT r.*, c.nome AS cliente_nome, c.cpf AS cliente_cpf, c.email AS cliente_email "
                   + "FROM reserva r "
                   + "INNER JOIN cliente c ON r.cliente_id = c.id "
                   + "WHERE r.id = ?";
        Reserva reserva = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reserva = extrairReservaComCliente(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar reserva por ID: " + e.getMessage(), e);
        }

        return reserva;
    }

    public Reserva buscarPorToken(String token) {
        String sql = "SELECT r.*, c.nome AS cliente_nome, c.cpf AS cliente_cpf, c.email AS cliente_email "
                   + "FROM reserva r "
                   + "INNER JOIN cliente c ON r.cliente_id = c.id "
                   + "WHERE r.token = ?";
        Reserva reserva = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, token);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reserva = extrairReservaComCliente(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar reserva por Token: " + e.getMessage(), e);
        }

        return reserva;
    }

    public List<Reserva> listarTodas() {
        String sql = "SELECT r.*, c.nome AS cliente_nome, c.cpf AS cliente_cpf, c.email AS cliente_email "
                   + "FROM reserva r "
                   + "INNER JOIN cliente c ON r.cliente_id = c.id "
                   + "ORDER BY r.data_retirada_prevista ASC";
        List<Reserva> reservas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                reservas.add(extrairReservaComCliente(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar reservas: " + e.getMessage(), e);
        }

        return reservas;
    }

    public void atualizarStatus(int id, String novoStatus) {
        String sql = "UPDATE reserva SET status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status da reserva: " + e.getMessage(), e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM reserva WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir reserva: " + e.getMessage(), e);
        }
    }

    private Reserva extrairReservaComCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("cliente_id"));
        cliente.setNome(rs.getString("cliente_nome"));
        cliente.setCpf(rs.getString("cliente_cpf"));
        cliente.setEmail(rs.getString("cliente_email"));

        Reserva reserva = new Reserva();
        reserva.setId(rs.getInt("id"));
        reserva.setToken(rs.getString("token"));
        reserva.setCliente(cliente);
        reserva.setCategoriaVeiculo(rs.getString("categoria_veiculo"));
        
        Timestamp drp = rs.getTimestamp("data_retirada_prevista");
        if (drp != null) {
            reserva.setDataRetiradaPrevista(drp.toLocalDateTime());
        }

        Timestamp ddp = rs.getTimestamp("data_devolucao_prevista");
        if (ddp != null) {
            reserva.setDataDevolucaoPrevista(ddp.toLocalDateTime());
        }

        reserva.setLocalRetirada(rs.getString("local_retirada"));
        reserva.setOpcionais(rs.getString("opcionais"));
        reserva.setEstimativaPreco(rs.getBigDecimal("estimativa_preco"));
        reserva.setStatus(rs.getString("status"));

        Timestamp dataCriacao = rs.getTimestamp("data_criacao");
        if (dataCriacao != null) {
            reserva.setDataCriacao(dataCriacao.toLocalDateTime());
        }

        return reserva;
    }
}