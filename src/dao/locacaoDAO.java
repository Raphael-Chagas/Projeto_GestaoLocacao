package dao;

import connection.conexao;
import model.Carro;
import model.Cliente;
import model.Funcionario;
import model.Locacao;
import model.Reserva;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class LocacaoDAO {

    public void inserir(Locacao locacao) {
        String sql = "INSERT INTO locacao (reserva_id, cliente_id, funcionario_id, veiculo_id, "
                   + "data_retirada, data_devolucao_prevista, data_devolucao_real, valor_caucao, "
                   + "valor_total, vistoria_devolucao, status, data_criacao) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // reserva_id pode ser nulo (locação direta sem reserva prévia)
            if (locacao.getReserva() != null && locacao.getReserva().getId() > 0) {
                stmt.setInt(1, locacao.getReserva().getId());
            } else {
                stmt.setNull(1, Types.INTEGER);
            }

            stmt.setInt(2, locacao.getCliente().getId());
            stmt.setInt(3, locacao.getFuncionario().getId());
            stmt.setInt(4, locacao.getCarro().getId());
            stmt.setTimestamp(5, Timestamp.valueOf(locacao.getDataRetirada()));
            stmt.setTimestamp(6, Timestamp.valueOf(locacao.getDataDevolucaoPrevista()));

            if (locacao.getDataDevolucaoReal() != null) {
                stmt.setTimestamp(7, Timestamp.valueOf(locacao.getDataDevolucaoReal()));
            } else {
                stmt.setNull(7, Types.TIMESTAMP);
            }

            stmt.setBigDecimal(8, locacao.getValorCaucao());

            if (locacao.getValorTotal() != null) {
                stmt.setBigDecimal(9, locacao.getValorTotal());
            } else {
                stmt.setNull(9, Types.DECIMAL);
            }

            stmt.setString(10, locacao.getVistoriaDevolucao());
            stmt.setString(11, locacao.getStatus());
            stmt.setTimestamp(12, Timestamp.valueOf(locacao.getDataCriacao()));

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    locacao.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar locação: " + e.getMessage(), e);
        }
    }

    public Locacao buscarPorId(int id) {
        String sql = "SELECT l.*, "
                   + "c.nome AS cliente_nome, c.cpf AS cliente_cpf, "
                   + "f.nome AS funcionario_nome, "
                   + "v.marca AS veiculo_marca, v.modelo AS veiculo_modelo, v.placa AS veiculo_placa, "
                   + "r.token AS reserva_token "
                   + "FROM locacao l "
                   + "INNER JOIN cliente c ON l.cliente_id = c.id "
                   + "INNER JOIN funcionario f ON l.funcionario_id = f.id "
                   + "INNER JOIN veiculo v ON l.veiculo_id = v.id "
                   + "LEFT JOIN reserva r ON l.reserva_id = r.id "
                   + "WHERE l.id = ?";

        Locacao locacao = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    locacao = extrairLocacaoComRelacionamentos(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar locação por ID: " + e.getMessage(), e);
        }

        return locacao;
    }

    public List<Locacao> listarTodas() {
        String sql = "SELECT l.*, "
                   + "c.nome AS cliente_nome, c.cpf AS cliente_cpf, "
                   + "f.nome AS funcionario_nome, "
                   + "v.marca AS veiculo_marca, v.modelo AS veiculo_modelo, v.placa AS veiculo_placa, "
                   + "r.token AS reserva_token "
                   + "FROM locacao l "
                   + "INNER JOIN cliente c ON l.cliente_id = c.id "
                   + "INNER JOIN funcionario f ON l.funcionario_id = f.id "
                   + "INNER JOIN veiculo v ON l.veiculo_id = v.id "
                   + "LEFT JOIN reserva r ON l.reserva_id = r.id "
                   + "ORDER BY l.data_retirada DESC";

        List<Locacao> locacoes = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                locacoes.add(extrairLocacaoComRelacionamentos(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar locações: " + e.getMessage(), e);
        }

        return locacoes;
    }

    public void finalizarLocacao(Locacao locacao) {
        String sql = "UPDATE locacao SET data_devolucao_real = ?, valor_total = ?, "
                   + "vistoria_devolucao = ?, status = 'FINALIZADA' WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(locacao.getDataDevolucaoReal() != null ? 
                    locacao.getDataDevolucaoReal() : LocalDateTime.now()));
            stmt.setBigDecimal(2, locacao.getValorTotal());
            stmt.setString(3, locacao.getVistoriaDevolucao());
            stmt.setInt(4, locacao.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao finalizar locação: " + e.getMessage(), e);
        }
    }

    private Locacao extrairLocacaoComRelacionamentos(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("cliente_id"));
        cliente.setNome(rs.getString("cliente_nome"));
        cliente.setCpf(rs.getString("cliente_cpf"));

        Funcionario funcionario = new Funcionario();
        funcionario.setId(rs.getInt("funcionario_id"));
        funcionario.setNome(rs.getString("funcionario_nome"));

        Carro carro = new Carro();
        carro.setId(rs.getInt("veiculo_id"));
        carro.setMarca(rs.getString("veiculo_marca"));
        carro.setModelo(rs.getString("veiculo_modelo"));
        carro.setPlaca(rs.getString("veiculo_placa"));

        Reserva reserva = null;
        int reservaId = rs.getInt("reserva_id");
        if (!rs.wasNull()) {
            reserva = new Reserva();
            reserva.setId(reservaId);
            reserva.setToken(rs.getString("reserva_token"));
        }

        Locacao locacao = new Locacao();
        locacao.setId(rs.getInt("id"));
        locacao.setReserva(reserva);
        locacao.setCliente(cliente);
        locacao.setFuncionario(funcionario);
        locacao.setCarro(carro);
        locacao.setDataRetirada(rs.getTimestamp("data_retirada").toLocalDateTime());
        locacao.setddp(rs.getTimestamp("data_devolucao_prevista").toLocalDateTime());

        Timestamp ddr = rs.getTimestamp("data_devolucao_real");
        if (ddr != null) {
            locacao.setddr(ddr.toLocalDateTime());
        }

        locacao.setValorCaucao(rs.getBigDecimal("valor_caucao"));
        locacao.setValorTotal(rs.getBigDecimal("valor_total"));
        locacao.setVistoriaDevolucao(rs.getString("vistoria_devolucao"));
        locacao.setStatus(rs.getString("status"));

        Timestamp dataCriacao = rs.getTimestamp("data_criacao");
        if (dataCriacao != null) {
            locacao.setDataCriacao(dataCriacao.toLocalDateTime());
        }

        return locacao;
    }
}