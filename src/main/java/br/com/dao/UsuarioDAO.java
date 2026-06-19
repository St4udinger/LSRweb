package br.com.dao;

import br.com.model.Usuario;
import br.com.util.ConnectionFactory;

import java.sql.*;

/**
<<<<<<< HEAD
 * DAO de usuários com suporte a cadastro, login por hash e recuperação de senha.
 */
public class UsuarioDAO {

    public int inserir(Usuario u) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
=======
 * DAO responsável pelas operações de banco de dados do usuário.
 *
 * SQL para criar a tabela (execute uma vez no MySQL):
 * -------------------------------------------------------
 * CREATE TABLE IF NOT EXISTS usuarios (
 *     id    INT AUTO_INCREMENT PRIMARY KEY,
 *     nome  VARCHAR(100) NOT NULL,
 *     email VARCHAR(150) NOT NULL UNIQUE,
 *     senha VARCHAR(255) NOT NULL
 * );
 * -------------------------------------------------------
 *
 * AVISO DE SEGURANÇA: em produção, use BCrypt ou similar para
 * armazenar a senha de forma segura. Aqui a senha é comparada
 * em texto plano apenas para simplicidade do exemplo.
 */
public class UsuarioDAO {

    /** Insere um novo usuário e retorna o id gerado. */
    public int inserir(Usuario u) {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getSenha());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
<<<<<<< HEAD
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
=======
                if (rs.next()) return rs.getInt(1);
            }

>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir usuário: " + e.getMessage(), e);
        }
        return -1;
    }

<<<<<<< HEAD
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT id, nome, email, senha FROM usuarios WHERE email = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, normalizarEmail(email));
=======
    /** Retorna um usuário pelo e-mail e senha, ou null se não encontrado. */
    public Usuario buscarPorEmailSenha(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, senha);
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
<<<<<<< HEAD
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar por email: " + e.getMessage(), e);
=======

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário: " + e.getMessage(), e);
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
        }
        return null;
    }

<<<<<<< HEAD
    public boolean emailJaCadastrado(String email) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, normalizarEmail(email));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
=======
    /** Verifica se um e-mail já está cadastrado. */
    public boolean emailJaCadastrado(String email) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }

>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar e-mail: " + e.getMessage(), e);
        }
        return false;
    }

<<<<<<< HEAD
    public void atualizarSenhaPorId(int usuarioId, String senhaHash) {
        String sql = "UPDATE usuarios SET senha = ? WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, senhaHash);
            ps.setInt(2, usuarioId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar senha: " + e.getMessage(), e);
        }
    }

    public void salvarTokenReset(int usuarioId, String tokenHash, Timestamp expiraEm) {
        String sql = "INSERT INTO password_reset_token (usuario_id, token_hash, expira_em, usado) VALUES (?, ?, ?, 0)";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, usuarioId);
            ps.setString(2, tokenHash);
            ps.setTimestamp(3, expiraEm);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar token de reset: " + e.getMessage(), e);
        }
    }

    public void marcarTokenComoUsado(int tokenId) {
        String sql = "UPDATE password_reset_token SET usado = 1 WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, tokenId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao marcar token como usado: " + e.getMessage(), e);
        }
    }

=======
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
    private Usuario mapear(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getInt("id"));
        u.setNome(rs.getString("nome"));
        u.setEmail(rs.getString("email"));
        u.setSenha(rs.getString("senha"));
        return u;
    }
<<<<<<< HEAD

    private String normalizarEmail(String email) {
        return email == null ? null : email.trim().toLowerCase();
    }
=======
>>>>>>> e65405e902a5ba7e9d550f680b7801b150a4effb
}