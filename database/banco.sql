CREATE DATABASE IF NOT EXISTS sistema_locacao;
USE sistema_locacao;

CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    cnh VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    necessidades_especiais TEXT,
    ativo BOOLEAN DEFAULT TRUE,
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE funcionario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    login VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cargo VARCHAR(50) NOT NULL
);

CREATE TABLE veiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(10) UNIQUE NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    quilometragem INT DEFAULT 0,
    status ENUM('DISPONIVEL', 'ALUGADO', 'INDISPONIVEL') DEFAULT 'DISPONIVEL',
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(50) UNIQUE NOT NULL,
    cliente_id INT NOT NULL,
    categoria_veiculo VARCHAR(50) NOT NULL,
    data_retirada_prevista DATETIME NOT NULL,
    data_devolucao_prevista DATETIME NOT NULL,
    local_retirada VARCHAR(100) NOT NULL,
    opcionais TEXT,
    estimativa_preco DECIMAL(10,2),
    status ENUM('ATIVA', 'CANCELADA', 'EFETIVADA') DEFAULT 'ATIVA',
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    -- Chaves estrangeiras:
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

CREATE TABLE locacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reserva_id INT,
    cliente_id INT NOT NULL,
    funcionario_id INT NOT NULL,
    veiculo_id INT NOT NULL,
    data_retirada DATETIME NOT NULL,
    data_devolucao_prevista DATETIME NOT NULL,
    data_devolucao_real DATETIME,
    valor_caucao DECIMAL(10,2) NOT NULL,
    valor_total DECIMAL(10,2),
    vistoria_devolucao TEXT,
    status ENUM('ATIVA', 'FINALIZADA') DEFAULT 'ATIVA',
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    -- Chaves estrangeiras:
    FOREIGN KEY (reserva_id) REFERENCES reservas(id), 
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id),
    FOREIGN KEY (veiculo_id) REFERENCES veiculos(id)
);