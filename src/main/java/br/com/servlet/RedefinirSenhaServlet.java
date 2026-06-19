package br.com.servlet;

import br.com.dao.UsuarioDAO;
import br.com.util.ConnectionFactory;
import br.com.util.SecurityUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/redefinir-senha")
public class RedefinirSenhaServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String token = request.getParameter("token");
        String senha = request.getParameter("senha");
        String confirmarSenha = request.getParameter("confirmarSenha");

        if (token == null || token.isBlank() ||
                senha == null || senha.isBlank() ||
                confirmarSenha == null || confirmarSenha.isBlank()) {
            request.setAttribute("msg", "Preencha todos os campos.");
            request.getRequestDispatcher("/redefinir-senha.jsp").forward(request, response);
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            request.setAttribute("msg", "As senhas não conferem.");
            request.getRequestDispatcher("/redefinir-senha.jsp").forward(request, response);
            return;
        }

        try (Connection con = ConnectionFactory.getConnection()) {

            String sql = "SELECT id, usuario_id, token_hash " +
                    "FROM password_reset_token " +
                    "WHERE usado = 0 AND expira_em >= NOW()";

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                Integer tokenIdValido = null;
                Integer usuarioId = null;

                while (rs.next()) {
                    String tokenHash = rs.getString("token_hash");
                    if (SecurityUtil.verifyToken(token, tokenHash)) {
                        tokenIdValido = rs.getInt("id");
                        usuarioId = rs.getInt("usuario_id");
                        break;
                    }
                }

                if (tokenIdValido == null || usuarioId == null) {
                    request.setAttribute("msg", "Token inválido ou expirado.");
                    request.getRequestDispatcher("/redefinir-senha.jsp").forward(request, response);
                    return;
                }

                String novaSenhaHash = SecurityUtil.hashPassword(senha);
                usuarioDAO.atualizarSenhaPorId(usuarioId, novaSenhaHash);
                usuarioDAO.marcarTokenComoUsado(tokenIdValido);

                request.setAttribute("msg", "Senha redefinida com sucesso. Faça login novamente.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException("Erro ao redefinir senha", e);
        }
    }
}